package com.niuwenyu.springframework.aop.framework;

public interface AopProxy {
    // 用于获取代理类，有JDK方式和cglib方式
    Object getProxy();
}
