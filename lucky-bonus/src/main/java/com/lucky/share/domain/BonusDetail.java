package com.lucky.share.domain;

import java.util.Date;

/**
 * @author Kevin.Chen
 * @date 2019/3/27.
 */
public class BonusDetail {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 变动金额
     */
    private Integer volume;

    /**
     * 账号剩余积分
     */
    private Integer balance;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
