package com.masaya.mybatis;

import com.masaya.mybatis.mybatis.io.MyResources;
import com.masaya.mybatis.mybatis.session.MySqlSession;
import com.masaya.mybatis.mybatis.session.MySqlSessionFactory;
import com.masaya.mybatis.mybatis.session.MySqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) {

        InputStream inputStream = MyResources.getResourceAsStream("mybatis-config.xml");

        MySqlSessionFactory sqlSessionFactory = new MySqlSessionFactoryBuilder().build(inputStream);

        MySqlSession mySqlSession = sqlSessionFactory.openMySqlSession();

        int a = 19;
    }
}
