package com.hyn.seckill.mapper;

import com.hyn.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * @Author: HYN
 * @Description:
 * @Modified By:
 */
@Mapper
@Repository
public interface SeckillOrderMapper {
    /**
     *插入购买订单明细
     * @param seckillId
     * @param money
     * @param userPhone
     * @return
     */
    int insertOrder(@Param("seckillId") long seckillId, @Param("money") BigDecimal money, @Param("userPhone") long userPhone);

    /**
     * 根据秒杀商品ID查询订单明细数据并得到对应秒杀商品的数据，因为SeckillOrder类中包含Seckill字段
     * 数据库中的seckill_id和user_phone是联合主键
     * @param seckillId
     * @param userPhone
     * @return
     */
    SeckillOrder findById(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
