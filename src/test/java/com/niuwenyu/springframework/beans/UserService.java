package com.niuwenyu.springframework.beans;

public class UserService {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UserService() {
    }

    public UserService(String msg) {
        this.msg = msg;
    }
}
