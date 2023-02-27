package com.masaya.mybatis.mybatis.session;

import com.masaya.mybatis.mybatis.parsing.MyXMLConfigBuilder;
import com.masaya.mybatis.mybatis.parsing.MyXPathParser;

import java.io.InputStream;

public class MySqlSessionFactoryBuilder {

    private MyXPathParser myXPathParser;

    public MySqlSessionFactory build(InputStream inputStream) {
        MyXMLConfigBuilder myXMLConfigBuilder = new MyXMLConfigBuilder(inputStream);
        myXMLConfigBuilder.parse();
    }
}
