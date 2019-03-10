package org.dreamtech.bookstore.service;

import org.dreamtech.bookstore.dao.UserDao;
import org.dreamtech.bookstore.exception.UserException;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.utils.SendMail;

import java.sql.SQLException;

/**
 * @Program: BookStore
 * @Description: 用户服务层
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class UserService {

    private UserDao userDao = new UserDao();

    /**
     * 用户注册失败
     *
     * @param user 用户
     */
    public void register(User user) throws UserException {
        final String IP = "http://localhost:8080";
        final String link = IP + "/active?activeCode=" + user.getActiveCode();
        try {
            userDao.addUser(user);
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    String target = user.getEmail();
                    String title = "网上书城激活";
                    String content = "<a href=\"" + link + "\">欢迎您在网上书城注册账号，点击激活</a><br><p>如果被邮箱安全设置拦截请复制链接再访问</p><br><p>" + link + "</p>";
                    if (target != null && !target.equals("")) {
                        boolean flag = SendMail.send(target, title, content);
                        if (!flag) {
                            throw new RuntimeException("发送激活邮件出错");
                        }
                    }
                }
            }.start();
        } catch (Exception e) {
            throw new UserException("用户名重复");

        }
    }

    /**
     * 激活用户
     *
     * @param activeCode 激活码
     * @throws UserException 异常
     */
    public void activeUser(String activeCode) throws UserException {
        try {
            User user = userDao.findUserByActiveCode(activeCode);
            if (user == null) {
                throw new UserException("用户不存在");
            } else if (user.getState() == 1) {
                throw new UserException("用户已经激活");
            } else {
                userDao.updateState(activeCode);
            }
        } catch (Exception e) {
            throw new UserException("激活失败");
        }

    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户对象
     * @throws UserException 异常
     */
    public User login(String username, String password) throws UserException {
        try {
            User user = userDao.findUserByUsernameAndPassword(username, password);
            if (user == null) {
                throw new UserException("用户名或密码错误");
            }
            if (user.getState() == 0) {
                throw new UserException("用户未激活，请先在邮箱激活");
            }
            return user;
        } catch (SQLException e) {
            throw new UserException("登陆失败");
        }
    }

    /**
     * 根据用户ID查找
     *
     * @param id 用户ID
     * @return 用户对象
     * @throws UserException 异常
     */
    public User findUserById(String id) throws UserException {
        try {
            User user = userDao.findUserById(id);
            if (user == null) {
                throw new UserException("用户不存在");
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("未知错误");
        }
    }

    /**
     * 修改用户
     *
     * @param user 用户对象
     * @throws UserException 异常
     */
    public void modifyUserInfo(User user) throws UserException {
        try {
            userDao.updateUser(user);
        } catch (SQLException e) {
            throw new UserException("未知错误");
        }
    }

}
