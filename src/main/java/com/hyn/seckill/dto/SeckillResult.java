package com.hyn.seckill.dto;

/**
 * @Author: HYN
 * @Description: 秒杀结果类
 * @Modified By:
 */
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String error;

    public SeckillResult(boolean success, T date) {
        this.success = success;
        this.data = date;
    }

    /**
     * error是发生错误时的异常信息
     * @param success
     * @param error
     */
    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
