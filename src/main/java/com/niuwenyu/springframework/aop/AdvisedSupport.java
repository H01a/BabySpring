package com.niuwenyu.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
public class AdvisedSupport {
    // 被代理的目标对象
    private TargetSource targetSource;
    // cglib方法拦截器，由用户自己实现invoke方法（也就是要通过AOP增强的方法）
    private MethodInterceptor methodInterceptor;
    // 检查目标方法是否符合通知条件
    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
