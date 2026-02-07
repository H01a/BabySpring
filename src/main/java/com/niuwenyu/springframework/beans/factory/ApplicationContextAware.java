package com.niuwenyu.springframework.beans.factory;

import com.niuwenyu.springframework.beans.context.ApplicationContext;

public interface ApplicationContextAware extends Aware{
    void setApplicationContext(ApplicationContext applicationContext);
}
