package org.dreamtech.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Program: BookStore
 * @Description: 事务管理工具
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class ManagerThreadLocal {

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 获取连接对象
     *
     * @return 连接对象
     */
    public static Connection getConnection() {
        try {
            Connection conn = tl.get();
            if (conn == null) {
                conn = C3P0Utils.getConnection();
                tl.set(conn);
            }
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 开启事务
     */
    public static void beginTransaction() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 提交事务
     */
    public static void commitTransaction() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */
    public static void close() {
        try {
            Connection connection = getConnection();
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
