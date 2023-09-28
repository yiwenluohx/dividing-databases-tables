package com.study.shardingsphereboot.model;

import java.io.Serializable;

/**
 * @author luohx
 * @version 1.0.0
 * @date: 2023/9/28 下午10:59
 * @menu
 */
public class Course implements Serializable {
    private Long cid;

    private String cname;

    private Long userId;

    private String cstatus;

    /**
     * Gets the value of cid.
     *
     * @return the value of cid
     */
    public Long getCid() {
        return cid;
    }

    /**
     * Sets the cid. *
     * <p>You can use getCid() to get the value of cid</p>
     * * @param cid cid
     */
    public void setCid(Long cid) {
        this.cid = cid;
    }

    /**
     * Gets the value of cname.
     *
     * @return the value of cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * Sets the cname. *
     * <p>You can use getCname() to get the value of cname</p>
     * * @param cname cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * Gets the value of userId.
     *
     * @return the value of userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the userId. *
     * <p>You can use getUserId() to get the value of userId</p>
     * * @param userId userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * Gets the value of cstatus.
     *
     * @return the value of cstatus
     */
    public String getCstatus() {
        return cstatus;
    }

    /**
     * Sets the cstatus. *
     * <p>You can use getCstatus() to get the value of cstatus</p>
     * * @param cstatus cstatus
     */
    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }
}
