package com.hyn.seckill.service;


import com.hyn.seckill.dto.Exposer;
import com.hyn.seckill.dto.SeckillExecution;
import com.hyn.seckill.entity.Seckill;
import com.hyn.seckill.exception.RepeatKillException;
import com.hyn.seckill.exception.SeckillCloseException;
import com.hyn.seckill.exception.SeckillException;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: HYN
 * @Description: 业务接口：站在使用者角度设计接口，
 *                三个方面：方法定义粒度，参数，返回值类型
 * @Modified By:
 */
public interface SeckillService {
    /**
     *     查询所有秒杀项目的列表
     */
    List<Seckill> getSeckillList();

    /**
     * 根据ID查询项目
     * @param sekillId
     * @return
     */
    Seckill getById(long sekillId);

    /**
     * 秒杀已开启则输出秒杀接口地址，否则输出系统时间和秒杀开启时间
     * @param seckillId
     * @return
     */

    /**
     * 执行秒杀操作
     * @param seckillId
     * @return
     */
    Exposer exportSeckillUrl(long seckillId);
    SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5)
            throws SeckillCloseException, RepeatKillException, SeckillException;
}
