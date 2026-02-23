package com.niuwenyu.springframework.beans;

import com.niuwenyu.springframework.beans.context.ApplicationContext;
import com.niuwenyu.springframework.beans.factory.ApplicationContextAware;
import com.niuwenyu.springframework.beans.factory.support.DisposableBean;

public class UserService implements DisposableBean, ApplicationContextAware, IUserService{
    private String uid;
    private UserDao userDao;

    private ApplicationContext context;

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
//        System.out.println("userDao: " + userDao.toString());
//        System.out.println("user info: " + userDao.queryUserName(name));
        System.out.println("say my name");
    }

    public void init(){
        System.out.println("我被创建了，执行初始化方法");
    }

    @Override
    public void destory() {
        System.out.println("Bean Destroy Method: UserService销毁方法");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public ApplicationContext getContext() {
        return context;
    }
}
