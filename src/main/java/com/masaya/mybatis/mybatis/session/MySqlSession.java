package com.masaya.mybatis.mybatis.session;

import com.masaya.mybatis.mybatis.executor.MyExecutor;

public class MySqlSession {

    private MyExecutor myExecutor;

    public MySqlSession(MyExecutor myExecutor) {
        this.myExecutor = myExecutor;
    }
}
