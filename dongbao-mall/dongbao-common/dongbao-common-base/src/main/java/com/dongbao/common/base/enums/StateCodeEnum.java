package com.dongbao.common.base.enums;

public enum StateCodeEnum {
    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    PARAMS_ERROR(102, "参数校验未通过"),

    // ================== ums ==================
    USER_NO_EXIST(51001, "用户不存在"),
    USER_PASS_ERROR(51002, "密码错误");


    private int code;
    private String msg;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    StateCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
