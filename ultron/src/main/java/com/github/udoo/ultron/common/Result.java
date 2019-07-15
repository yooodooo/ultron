package com.github.udoo.ultron.common;

import lombok.Data;

/**
 * @author dong.yang
 * @data 2019/7/15 14:15
 */
@Data
public class Result<T> {
    private boolean success;
    private String msg;
    private T data;

    public static <T> Result success(T data) {
        Result result = new Result();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.setMsg(msg);
        return result;
    }
}
