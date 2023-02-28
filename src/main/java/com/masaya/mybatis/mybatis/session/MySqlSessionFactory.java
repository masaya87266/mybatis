package com.masaya.mybatis.mybatis.session;

import com.masaya.mybatis.mybatis.executor.MyExecutor;
import com.masaya.mybatis.mybatis.mapping.MyConfiguration;

public class MySqlSessionFactory {

    private MyConfiguration myConfiguration;

    public MySqlSessionFactory(MyConfiguration myConfiguration) {
        this.myConfiguration = myConfiguration;
    }

    public MySqlSession openMySqlSession() {
        MyExecutor myExecutor = new MyExecutor(myConfiguration);
        return new MySqlSession(myExecutor);
    }
}
