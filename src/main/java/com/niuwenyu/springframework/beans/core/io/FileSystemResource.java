package com.niuwenyu.springframework.beans.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileSystemResource implements Resource{
    private final String location;

    public FileSystemResource(String location) {
        this.location = location;
    }

    @Override
    public InputStream getInputStream() {
        File file = new File(location);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
