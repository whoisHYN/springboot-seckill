package com.hyn.seckill.redis;


import com.hyn.seckill.entity.Seckill;
import com.hyn.seckill.mapper.SeckillMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: HYN
 * @Description:
 * @Date: 2019-10-21  Time: 20:27
 * @Modified By:
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisDaoTest {
    private long id = 1;

    @Autowired
    private SeckillMapper seckillMapper;

    @Autowired
    private RedisDao redisDao;

    @Test
    public void testRedisDao() {
        Seckill seckill = redisDao.getSeckill(id);
        if (seckill == null) {
            seckill = seckillMapper.findById(id);
            if (seckill != null) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }
}