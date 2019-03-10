package org.dreamtech.bookstore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.dreamtech.bookstore.model.OrderItem;
import org.dreamtech.bookstore.model.Product;
import org.dreamtech.bookstore.utils.C3P0Utils;
import org.dreamtech.bookstore.utils.ManagerThreadLocal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 商品DAO
 * @Author: YiQing Xu
 * @Create: 2019-03-04 15:21
 */
public class ProductDao {
    /**
     * 根据分类查找总数
     *
     * @param category 分类
     * @return 总数
     * @throws SQLException 异常
     */
    public long count(String category) throws SQLException {
        long count;
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select count(*) from products where category=?";
        count = (long) qr.query(sql, new ScalarHandler(), category);
        return count;
    }

    /**
     * 查找总数
     *
     * @return 总数
     * @throws SQLException 异常
     */
    public long count() throws SQLException {
        long count;
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select count(*) from products where 1=1";
        count = (long) qr.query(sql, new ScalarHandler());
        return count;
    }

    /**
     * 用于分页
     *
     * @param page     页数
     * @param pageSize 大小
     * @return 商品列表
     * @throws SQLException 异常
     */
    public List<Product> findBooks(int page, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        List<Object> params = new ArrayList<>();
        String sql = "select * from products where 1=1 limit ?,?";
        int start = (page - 1) * pageSize;
        params.add(start);
        params.add(pageSize);
        return qr.query(sql, new BeanListHandler<>(Product.class), params.toArray());
    }

    /**
     * 用于分页
     *
     * @param category 分类
     * @param page     页数
     * @param pageSize 大小
     * @return 商品列表
     * @throws SQLException 异常
     */
    public List<Product> findBooks(String category, int page, int pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        List<Object> params = new ArrayList<>();
        String sql = "select * from products where category=? limit ?,?";
        int start = (page - 1) * pageSize;
        params.add(category);
        params.add(start);
        params.add(pageSize);
        return qr.query(sql, new BeanListHandler<>(Product.class), params.toArray());
    }

    /**
     * 用于分页
     *
     * @param id 商品ID
     * @return 商品
     * @throws SQLException 异常
     */
    public Product findBook(String id) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from products where id=?";
        return qr.query(sql, new BeanHandler<>(Product.class), id);
    }

    /**
     * 更新库存
     *
     * @param product_id 商品ID
     * @param num        数量
     * @throws SQLException 异常
     */
    public void updatePNum(int product_id, int num) throws SQLException {
        String sql = "update products set pnum=pnum-? where id=?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        qr.update(sql, num, product_id);
    }

    /**
     * 使用批处理提高效率
     *
     * @param items 商品条目
     * @throws SQLException 异常
     */
    public void updatePNumByBatch(List<OrderItem> items) throws SQLException {
        String sql = "update products set pnum=pnum-? where id=?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        Object[][] params = new Object[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            params[i] = new Object[]{item.getBuynum(), item.getProduct().getId()};
        }
        Connection connection = ManagerThreadLocal.getConnection();
        if (connection != null) {
            qr.batch(connection, sql, params);
        }
    }
}