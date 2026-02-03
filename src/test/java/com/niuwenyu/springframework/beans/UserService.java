package com.niuwenyu.springframework.beans;

import com.niuwenyu.springframework.beans.factory.support.DisposableBean;

public class UserService implements DisposableBean {
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
        System.out.println("userDao: " + userDao.toString());
        System.out.println("user info: " + userDao.queryUserName(name));
    }

    public void init(){
        System.out.println("我被创建了，执行初始化方法");
    }

    @Override
    public void destory() {
        System.out.println("Bean Destroy Method: UserService销毁方法");
    }
}
