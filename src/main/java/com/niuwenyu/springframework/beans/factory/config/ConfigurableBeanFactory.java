package com.niuwenyu.springframework.beans.factory.config;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory{
    // 接口定义变量需要初始化，并且不能为private
    String SCOPE_SINGLETON = null;
    String SCOPE_PROTOTYPE = null;
    void destorySingletons();

}
