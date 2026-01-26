package com.niuwenyu.springframework.beans;

public class UserService {
    private String uid;
    private UserDao userDao;

    public String getUid() {
        return uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public UserService() {
    }

    public void queryUserInfo(String name){
        System.out.println("user info: " + userDao.queryUserName(name));
    }
}
