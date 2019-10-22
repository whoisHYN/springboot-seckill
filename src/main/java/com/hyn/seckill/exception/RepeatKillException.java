package com.hyn.seckill.exception;

/**
 * @Author: HYN
 * @Description: 重复秒杀时抛出此异常
 * @Modified By:
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
