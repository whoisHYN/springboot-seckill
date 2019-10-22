package com.hyn.seckill.mapper;

import com.hyn.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author: HYN
 * @Description:
 * @Modified By:
 */
@Mapper
@Repository  //这个加不加都行，不加的话自动注入这个bean会提示错误，但能正常运行
public interface SeckillMapper {


    /**
     * 查询全部秒杀项目
     * @return 秒杀产品列表
     */
    List<Seckill> findAll();

    /**
     * 根据ID查询秒杀项目
     * @param id
     * @return
     */
    Seckill findById(long id);

    /**
     *减库存
     * 注意：如果Mapper映射接口方法的参数大于1，则应该使用@Param注解，否则MyBatis无法判断字段对应关系
     * @param seckillId 秒杀商品ID
     * @param killTime 秒杀时间
     * @return 返回此SQL更新的记录数，若结果大于等于1，说明SQL执行成功
     */
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
