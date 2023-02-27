package com.masaya.mybatis.mybatis.session;

import com.masaya.mybatis.mybatis.mapping.MyConfiguration;
import com.masaya.mybatis.mybatis.parsing.MyXMLConfigBuilder;
import com.masaya.mybatis.mybatis.parsing.MyXPathParser;

import java.io.InputStream;

public class MySqlSessionFactoryBuilder {

    private MyXPathParser myXPathParser;

    public MySqlSessionFactory build(InputStream inputStream) {
        MyXMLConfigBuilder myXMLConfigBuilder = new MyXMLConfigBuilder(inputStream);
        MyConfiguration myConfiguration = myXMLConfigBuilder.parse();
        
        return new MySqlSessionFactory(myConfiguration);
    }
}
