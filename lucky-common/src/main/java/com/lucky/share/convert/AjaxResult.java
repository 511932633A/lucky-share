package com.lucky.share.convert;

import com.lucky.share.constant.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Kevin.Chen
 * 返回数据格式
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AjaxResult<T> {
    private Integer code;
    private String msg;
    private T data = null;

    public AjaxResult<T> success() {
        return create(ErrorCode.SUCCESS, null);
    }

    public AjaxResult<T> create(ErrorCode code, T data) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
        return this;
    }

    public AjaxResult<T> create(ErrorCode code) {
        return create(code, null);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}