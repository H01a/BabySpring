package com.niuwenyu.springframework.beans.core.io;

public interface ResourceLoader {
    static final String CLASSPATH_URL_PREFIX = "classpath:";
    public Resource getResource(String location);
}
