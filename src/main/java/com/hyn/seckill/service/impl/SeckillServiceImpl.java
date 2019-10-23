package com.hyn.seckill.service.impl;

import com.hyn.seckill.dto.Exposer;
import com.hyn.seckill.dto.SeckillExecution;
import com.hyn.seckill.entity.Seckill;
import com.hyn.seckill.entity.SeckillOrder;
import com.hyn.seckill.enums.SeckillStateEnum;
import com.hyn.seckill.exception.RepeatKillException;
import com.hyn.seckill.exception.SeckillCloseException;
import com.hyn.seckill.exception.SeckillException;
import com.hyn.seckill.mapper.SeckillMapper;
import com.hyn.seckill.mapper.SeckillOrderMapper;
import com.hyn.seckill.redis.RedisDao;
import com.hyn.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: HYN
 * @Description: 业务层实现类
 * @Modified By:
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    /**随机字符串，用于给MD5字符串加*/
    private final String salt = "wojiushisuibianqiaoleyigezifuchuan$%^%*&&(*zhende";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SeckillMapper seckillMapper;
    private final SeckillOrderMapper seckillOrderMapper;
    private final RedisDao redisDao;

    public SeckillServiceImpl(SeckillMapper seckillMapper, SeckillOrderMapper seckillOrderMapper,
                              RedisDao redisDao) {
        this.seckillMapper = seckillMapper;
        this.seckillOrderMapper = seckillOrderMapper;
        this.redisDao = redisDao;
    }

    @Override
    public List<Seckill> getSeckillList() {
        return seckillMapper.findAll();
    }

    @Override
    public Seckill getById(long sekillId) {
        return seckillMapper.findById(sekillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        //缓存优化，先用Redis查询
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillMapper.findById(seckillId);
            if (seckill == null) {
                //项目不存在，返回结果
                return new Exposer(false, seckillId);
            } else {
                //将结果放入redis
                redisDao.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now = new Date();
        //秒杀项目未开始或者已经结束
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, now.getTime(), startTime.getTime(), endTime.getTime());
        }
        //秒杀项目正在进行
        String md5 = generateMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
     * 生成MD5加密字符串
     * @param seckillId
     * @return
     */
    private String generateMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5) throws SeckillCloseException, RepeatKillException, SeckillException {
        if (md5 == null || !md5.equals(generateMd5(seckillId))) {
            throw new SeckillException("Seckill data rewritten!");
        }
        Date now = new Date();
        try {
            //先记录购买明细，若不成功，说明重复秒杀
            int insertCount = seckillOrderMapper.insertOrder(seckillId, money, userPhone);
            if (insertCount <= 0) {
                throw new RepeatKillException("Seckill repeated!");
            } else {
                //减库存
                int updateCount = seckillMapper.reduceStock(seckillId, now);
                if (updateCount <= 0) {
                    //没有秒杀记录，说明秒杀已经结束
                    throw new SeckillCloseException("Seckill closed!");
                } else {
                    SeckillOrder order = seckillOrderMapper.findById(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, order);
                }
            }
        }
        //先抛出小范围异常，方便定位问题
        catch (RepeatKillException | SeckillCloseException e1) {
            throw e1;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //所有编译期异常都转化为运行期异常
            throw new SeckillException("Seckill inner error " + e.getMessage());
        }
    }
}
