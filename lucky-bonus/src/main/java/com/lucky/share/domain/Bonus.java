package com.lucky.share.domain;

import java.util.Date;

/**
 * @author Kevin.Chen
 * @date 2019/3/27.
 */
public class Bonus {
    /**
     * id
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 账号剩余积分
     */
    private Integer balance;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
