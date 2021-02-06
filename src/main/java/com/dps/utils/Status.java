package com.dps.utils;

public enum Status {

    OK(Integer.valueOf(0),"成功");

    private final Integer code;
    private final String msg;

    private Status(final Integer code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
