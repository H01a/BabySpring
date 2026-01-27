package com.niuwenyu.springframework.beans.core.io;

import java.io.InputStream;

public class ClassPathResource implements Resource{
    private final String path;
    private final ClassLoader classLoader;


    public ClassPathResource(String path) {
        this.path = path;
        classLoader = ClassLoader.getSystemClassLoader();
    }

    @Override
    public InputStream getInputStream() {
        return classLoader.getResourceAsStream(path);
    }
}
