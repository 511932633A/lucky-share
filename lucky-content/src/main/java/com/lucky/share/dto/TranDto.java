package com.lucky.share.dto;

/**
 * @author Kevin.Chen
 * @date 2019/3/28.
 */
public class TranDto {
    /**
     * 交易类型
     * 1.初始化
     * 2.签到
     * 3.兑换书
     */
    private Integer tranType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 交易量
     */
    private Integer volume;

    public Integer getTranType() {
        return tranType;
    }

    public void setTranType(Integer tranType) {
        this.tranType = tranType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
