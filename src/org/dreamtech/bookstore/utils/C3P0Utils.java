package org.dreamtech.bookstore.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @Program: BookStore
 * @Description: C3P0连接池工具类
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class C3P0Utils {

    private static DataSource ds = new ComboPooledDataSource();

    /**
     * 返回数据源[连接池]
     *
     * @return 数据源
     */
    public static DataSource getDataSource() {
        return ds;
    }

    /**
     * 返回连接池中的一个连接
     *
     * @return 连接
     */
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("服务器错误");
        }
    }

    /**
     * 关闭资源
     *
     * @param conn      连接
     * @param statement Statement
     * @param resultSet ResultSet
     */
    public static void closeAll(Connection conn, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resultSet = null;
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            statement = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                conn = null;
            }
        }
    }
}