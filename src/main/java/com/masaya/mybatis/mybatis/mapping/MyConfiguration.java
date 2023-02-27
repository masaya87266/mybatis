package com.masaya.mybatis.mybatis.mapping;

import java.util.Map;

public class MyConfiguration {

    private MyEnvironment myEnvironment;

    private Map<String, MyMapperStatement> myMapperStatementMap;

    public MyEnvironment getMyEnvironment() {
        return myEnvironment;
    }

    public void setMyEnvironment(MyEnvironment myEnvironment) {
        this.myEnvironment = myEnvironment;
    }

    public Map<String, MyMapperStatement> getMyMapperStatementMap() {
        return myMapperStatementMap;
    }

    public void setMyMapperStatementMap(Map<String, MyMapperStatement> myMapperStatementMap) {
        this.myMapperStatementMap = myMapperStatementMap;
    }
}
