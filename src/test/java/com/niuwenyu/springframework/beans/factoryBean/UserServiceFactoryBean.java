package com.niuwenyu.springframework.beans.factoryBean;

import com.niuwenyu.springframework.beans.UserDao;
import com.niuwenyu.springframework.beans.UserService;
import com.niuwenyu.springframework.beans.factory.FactoryBean;

public class UserServiceFactoryBean implements FactoryBean<UserService> {
    @Override
    public UserService getObject() {
        UserDao userDao = new UserDao();
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        return userService;
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
