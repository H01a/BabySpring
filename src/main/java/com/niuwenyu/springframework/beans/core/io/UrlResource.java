package com.niuwenyu.springframework.beans.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class UrlResource implements Resource{
    private final String path;
    private final URL url;

    public UrlResource(String path) throws MalformedURLException {
        this.path = path;
        url = new URL(path);
    }

    /**
     * TODO: 从URL下载文件（保存在哪里），并返回inputStream
     * @return
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return url.openStream();
    }
}
