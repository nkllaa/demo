package com.example.demo.pojo;

import java.io.Serializable;

public class Wx3re_Session implements Serializable {
    private String openId;
    private String session_key;
    private String SessionId;
    private long userId;
    private String phoneNum;
    private String session_3rd;
    private String userName;

    public Wx3re_Session() {
    }

    public Wx3re_Session(String openId, String session_key, String sessionId, long userId, String phonenum, String session_3rd, String userName) {
        this.openId = openId;
        this.session_key = session_key;
        SessionId = sessionId;
        this.userId = userId;
        this.phoneNum = phonenum;
        this.session_3rd = session_3rd;
        this.userName=userName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getSessionId() {
        return SessionId;
    }

    public void setSessionId(String sessionId) {
        SessionId = sessionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phonenum) {
        this.phoneNum = phonenum;
    }

    public String getSession_3rd() {
        return session_3rd;
    }

    public void setSession_3rd(String session_3rd) {
        this.session_3rd = session_3rd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
