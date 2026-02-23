package com.niuwenyu.springframework.aop.framework;

import com.niuwenyu.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), advised.getTargetSource().getTargetClass().getInterfaces(), this);
    }


    // 在JDK代理中，传入的method是接口中的abstract方法
    // 如果直接与表达式中的 com.xx.UserService(..)比较，会返回false
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method specificMethod = AopUtils.getMostSpecificMethod(method, advised.getTargetSource().getTargetClass());
        if(advised.getMethodMatcher().matches(specificMethod, advised.getTargetSource().getTargetClass())){
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
