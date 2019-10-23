package com.hyn.seckill.dto;

import com.hyn.seckill.entity.SeckillOrder;
import com.hyn.seckill.enums.SeckillStateEnum;

/**
 * @Author: HYN
 * @Description: 秒杀执行类
 * @Modified By:
 */
public class SeckillExecution {
    private long seckillId;
    /**秒杀状态*/
    private int state;
    /**秒杀状态信息*/
    private String stateInfo;
    /**秒杀订单*/
    private SeckillOrder seckillOrder;

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SeckillOrder order) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.seckillOrder = order;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillOrder getSeckillOrder() {
        return seckillOrder;
    }

    public void setSeckillOrder(SeckillOrder seckillOrder) {
        this.seckillOrder = seckillOrder;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillOrder=" + seckillOrder +
                '}';
    }
}
