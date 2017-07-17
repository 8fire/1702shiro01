package com.qianfeng.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;


public class DakeRecord implements Serializable {
    private int id;
    @JSONField(name="dakatime",format ="yyyy-MM-dd HH:mm:ss")
    private String dakatime;
    private int flag;
    private String dakastate;
    private int uid;
    private Users users;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDakatime() {
        return dakatime;
    }

    public void setDakatime(String dakatime) {
        this.dakatime = dakatime;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDakastate() {
        return dakastate;
    }

    public void setDakastate(String dakastate) {
        this.dakastate = dakastate;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
