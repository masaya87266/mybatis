package com.masaya.mybatis.mybatis.io;

import java.io.InputStream;

public class MyResources {

    public static InputStream getResourceAsStream(String resource) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }
}
