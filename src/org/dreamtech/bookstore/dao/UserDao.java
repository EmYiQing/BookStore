package org.dreamtech.bookstore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.utils.C3P0Utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 用户DAO
 * @Author: YiQing Xu
 * @Create: 2019-03-02 17:46
 */
public class UserDao {

    /**
     * 添加一个用户
     *
     * @param user 用户
     */
    public void addUser(User user) throws Exception {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "insert into user";
        sql += " (username,PASSWORD,gender,email,telephone,introduce,activeCode,state,role,registTime)";
        sql += " values(?,?,?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(user.getUsername());
        params.add(user.getPassword());
        params.add(user.getGender());
        params.add(user.getEmail());
        params.add(user.getTelephone());
        params.add(user.getIntroduce());
        params.add(user.getActiveCode());
        params.add(user.getState());
        params.add(user.getRole());
        params.add(user.getRegistTime());
        qr.update(sql, params.toArray());
    }

    /**
     * 根据激活码查找用户
     *
     * @param activeCode 激活码
     * @return 用户
     * @throws SQLException 异常
     */
    public User findUserByActiveCode(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from user where activeCode=?";
        return qr.query(sql, new BeanHandler<User>(User.class) {
        }, activeCode);
    }

    /**
     * 更新用户激活状态
     *
     * @param activeCode 激活码
     * @throws SQLException 异常
     */
    public void updateState(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update user set state=1 where activeCode=?";
        qr.update(sql, activeCode);
    }

    /**
     * 根据用户名和密码查找用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     * @throws SQLException 异常
     */
    public User findUserByUsernameAndPassword(String username, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from user where username=? and PASSWORD=?";
        return qr.query(sql, new BeanHandler<>(User.class), username, password);
    }

    /**
     * 根据用户ID查找用户
     *
     * @param id 用户ID
     * @return 用户
     * @throws SQLException 异常
     */
    public User findUserById(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from user where id=?";
        return qr.query(sql, new BeanHandler<>(User.class), id);
    }

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @throws SQLException 异常
     */
    public void updateUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update user set PASSWORD=?,gender=?,telephone=? where id=?";
        qr.update(sql, user.getPassword(), user.getGender(), user.getTelephone(), user.getId());
    }

}
