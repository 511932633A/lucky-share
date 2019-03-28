package com.lucky.share.domain;

import java.util.Date;

/**
 * @author Kevin.Chen
 * @date 2019/3/28.
 */
public class Exchange {
    /**
     * id
     */
    private Integer id;
    /**
     * 用户
     */
    private Integer uid;
    /**
     * 资源、文章id
     */
    private Integer aid;
    /**
     * 积分数
     */
    private Integer volume;
    /**
     * 时间
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

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
