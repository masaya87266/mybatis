package com.masaya.mybatis;

import com.masaya.mybatis.mybatis.io.MyResources;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) {

        InputStream inputStream = MyResources.getResourceAsStream("mybatis-config.xml");
        

    }
}
