package com.hyn.seckill.mapper;

import com.hyn.seckill.entity.SeckillOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;


/**
 * @Author: HYN
 * @Description:
 * @Date: 2019-10-19  Time: 17:04
 * @Modified By:
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SeckillOrderMapperTest {

    @Autowired
    private SeckillOrderMapper mapper;

    @Test
    public void testInsertOrder() {
        int i = mapper.insertOrder(1, BigDecimal.valueOf(459900, 2), 15767574847L);
        System.out.println(i);
    }

    @Test
    public void testFindById() {
        SeckillOrder order = mapper.findById(1, 15767574847L);
        System.out.println(order);
    }
}