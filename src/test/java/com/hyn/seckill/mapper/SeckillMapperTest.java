package com.hyn.seckill.mapper;

import com.hyn.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @Author: HYN
 * @Description:
 * @Date: 2019-10-19  Time: 15:03
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillMapperTest {
    @Autowired
    private SeckillMapper seckillMapper;

    @Test
    public void testFindAll() {
        List<Seckill> list = seckillMapper.findAll();
        for (Seckill seckill : list) {
            System.out.println(seckill.getTitle());
        }
    }

    @Test
    public void testFindById() {
        Seckill seckill = seckillMapper.findById(1L);
        System.out.println(seckill);
    }

    @Test
    public void testReduceStock() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2019-10-21");
//        java.sql.Date sDate = new java.sql.Date(date.getTime());
        int executed = seckillMapper.reduceStock(1, date);
        System.out.println(executed);
    }

    @Test
    public void testDate() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-10-21 00:00:00");
        java.sql.Date sDate = new java.sql.Date(date.getTime());
        System.out.println(sDate);
    }
}