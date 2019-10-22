package com.hyn.seckill.exception;

/**
 * @Author: HYN
 * @Description: 秒杀业务相关异常
 * @Modified By:
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
