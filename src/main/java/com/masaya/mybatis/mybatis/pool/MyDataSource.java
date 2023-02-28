package com.masaya.mybatis.mybatis.pool;

import com.masaya.mybatis.mybatis.executor.MyDataSourceInterface;
import com.masaya.mybatis.mybatis.mapping.MyEnvironment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyDataSource implements MyDataSourceInterface {

    private static MyDataSource instance;

    private MyEnvironment myEnvironment;

    private Connection connection;

    private List<Connection> pool;

    private static final int POOL_SIZE = 30;

    private MyDataSource(MyEnvironment myEnvironment) {
        this.myEnvironment = myEnvironment;
        this.pool = new ArrayList<>(POOL_SIZE);
        this.createConnection();
    }

    public static MyDataSource getInstance(MyEnvironment myEnvironment) {
        if (instance == null) {
            instance = new MyDataSource(myEnvironment);
        }
        return instance;
    }

    public void createConnection() {
        try {
            Class.forName(myEnvironment.getDriver());
            for (int i = 0; i < POOL_SIZE; i++) {
                this.connection = DriverManager.getConnection(myEnvironment.getUrl(), myEnvironment.getUserName(), myEnvironment.getPassword());
                this.pool.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized Connection getConnection(String username, String password) throws SQLException {
        if (pool.size() > 0) {
            Connection connection = pool.get(0);
            pool.remove(connection);
            return connection;
        }
        return null;
    }

    public synchronized void releaseConnection(Connection connection) {
        pool.add(connection);
    }
}
