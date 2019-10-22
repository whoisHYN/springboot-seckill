package com.hyn.seckill.exception;


/**
 * @Author: HYN
 * @Description: 秒杀关闭异常，秒杀未开启或关闭时抛出此异常
 * @Modified By:
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
