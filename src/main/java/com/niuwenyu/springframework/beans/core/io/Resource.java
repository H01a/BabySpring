package com.niuwenyu.springframework.beans.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wenyuniu
 */
public interface Resource {
    public InputStream getInputStream() throws IOException;
}
