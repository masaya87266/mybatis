package com.masaya.mybatis.mybatis.executor;

import com.masaya.mybatis.mybatis.mapping.MyConfiguration;
import com.masaya.mybatis.mybatis.pool.MyDataSource;

public class MyExecutor {

    private MyConfiguration myConfiguration;

    private MyDataSource myDataSource;

    public MyExecutor(MyConfiguration myConfiguration) {
        myDataSource = MyDataSource.getInstance(myConfiguration.getMyEnvironment());
    }


}
