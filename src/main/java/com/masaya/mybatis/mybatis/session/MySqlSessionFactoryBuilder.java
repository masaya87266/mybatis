package com.masaya.mybatis.mybatis.session;

import com.masaya.mybatis.mybatis.parsing.MyXMLConfigBuilder;

import java.io.InputStream;

public class MySqlSessionFactoryBuilder {

    public MySqlSessionFactory build(InputStream inputStream) {
        MyXMLConfigBuilder myXMLConfigBuilder = new MyXMLConfigBuilder(inputStream);
        myXMLConfigBuilder.parse();
        return null;
    }
}
