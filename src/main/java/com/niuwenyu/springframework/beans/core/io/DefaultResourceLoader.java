package com.niuwenyu.springframework.beans.core.io;

import java.net.MalformedURLException;

public class DefaultResourceLoader implements ResourceLoader{
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }
        try{
            return new UrlResource(location);
        }catch (MalformedURLException e){
            return new FileSystemResource(location);
        }
    }
}
