package org.dreamtech.bookstore.utils;

import java.util.UUID;

/**
 * @Program: BookStore
 * @Description: 通用工具类
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class CommonUtils {
    /**
     * 获得UUID
     *
     * @return 字符串UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
