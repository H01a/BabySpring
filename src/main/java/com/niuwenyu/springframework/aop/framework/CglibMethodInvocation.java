package com.niuwenyu.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

public class CglibMethodInvocation implements MethodInvocation {

    private final Object target;
    private final Method method;
    private final Object[] arguments;
    private final MethodProxy methodProxy;
    private final Object proxy;

    public CglibMethodInvocation(
            Object target,
            Method method,
            Object[] arguments,
            MethodProxy methodProxy,
            Object proxy) {

        this.target = target;
        this.method = method;
        this.arguments = arguments;
        this.methodProxy = methodProxy;
        this.proxy = proxy;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return methodProxy.invokeSuper(proxy, arguments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}