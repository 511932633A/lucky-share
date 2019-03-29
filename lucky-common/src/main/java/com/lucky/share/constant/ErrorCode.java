package com.lucky.share.constant;

/**
 * 为了对于请求端方便，这里统一错误码
 * @author Kevin.Chen
 */
public enum ErrorCode {
    /**
     * 成功
     */
    SUCCESS(0, "Success"),
    /**
     * 登录失败
     */
    LOGIN_FAIL(-1, "Login fail"),

    /**
     * Authorization 头部不存在
     */
    TOKEN_NOTFIND(-2, "Can not find the token."),
    /**
     * TOKEN 无效
     */
    TOKEN_INVALID(-3, "Token invalid"),
    /**
     * 账号不存在
     */
    BONUS_NOTEXIST(-4, "Account does not exist"),
    /**
     * 余额不足
     */
    BONUS_NOTENOUGH(-5, "Balance is not enough"),
    /**
     * 服务不可用
     */
    BONUS_UNAVAILABLE(-6, "The bonus service unavailable"),
    /**
     * 参数错误
     */
    BONUS_PARAM_ERROR(-7, "The param error."),
    /**
     * 不支持
     */
    UNSUPPORTED(-98, "Unsupported"),
    /**
     * 未知错误
     */
    UNKNOWN(-99, "Unknown error");
    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String msg;

    ErrorCode(int code, String message) {
        this.code = code;
        this.msg = message;
    }

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
}