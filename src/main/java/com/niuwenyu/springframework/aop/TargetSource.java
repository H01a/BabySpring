package com.niuwenyu.springframework.aop;

public class TargetSource {
    private Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?> getTargetClass() {
        return target.getClass();
    }

    public Object getTarget() {
        return target;
    }
}
