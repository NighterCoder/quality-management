package com.dps.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 结果封装
 * 不加构造器,默认是无参构造器
 * @param <T>
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> Result<T> status(Status status){
        final Result<T> result=new Result<>();
        result.setCode(status.getCode());
        result.setMsg(status.getMsg());
        return result;
    }

    public static <T> Result<T> status(final Status status, final String message) {
        final Result<T> result = new Result<T>();
        result.setCode(status.getCode());
        result.setMsg(message);
        return result;
    }
}
