package com.qianfeng.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;


public class Users implements Serializable {
    private int uid;
    private String account;
    private String pwd;
    /*private Set<DakeRecord> dakeRecord;*/
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

   /* public Set<DakeRecord> getDakeRecord() {
        return dakeRecord;
    }

    public void setDakeRecord(Set<DakeRecord> dakeRecord) {
        this.dakeRecord = dakeRecord;
    }*/
}
