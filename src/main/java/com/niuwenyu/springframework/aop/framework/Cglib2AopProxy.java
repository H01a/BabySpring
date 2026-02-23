package com.niuwenyu.springframework.aop.framework;

import com.niuwenyu.springframework.aop.AdvisedSupport;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Cglib2AopProxy implements AopProxy {

    private final AdvisedSupport advised;

    public Cglib2AopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(advised.getTargetSource().getTargetClass());
        enhancer.setInterfaces(advised.getTargetSource().getTargetClass().getInterfaces());
        enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
        return enhancer.create();
    }

    private static class DynamicAdvisedInterceptor implements MethodInterceptor {

        private final AdvisedSupport advised;

        public DynamicAdvisedInterceptor(AdvisedSupport advised) {
            this.advised = advised;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            Object target = advised.getTargetSource().getTarget();

            if (advised.getMethodMatcher().matches(method, target.getClass())) {

                CglibMethodInvocation invocation =
                        new CglibMethodInvocation(
                                target,
                                method,
                                args,
                                proxy,
                                obj
                        );

                return advised.getMethodInterceptor().invoke(invocation);
            }

            return proxy.invokeSuper(obj, args);
        }
    }
}