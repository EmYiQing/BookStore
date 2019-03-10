package org.dreamtech.bookstore.exception;

/**
 * @Program: BookStore
 * @Description: 自定义异常
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class UserException extends Exception {
    /**
     * 构造方法
     *
     * @param message 显示的信息
     */
    public UserException(String message) {
        super(message);
    }
}
