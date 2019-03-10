package org.dreamtech.bookstore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.dreamtech.bookstore.model.OrderItem;
import org.dreamtech.bookstore.utils.C3P0Utils;
import org.dreamtech.bookstore.utils.ManagerThreadLocal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 订单条目DAO
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:44
 */
public class OrderItemDao {
    /**
     * 添加订单条目
     *
     * @param items 订单条目列表
     * @throws SQLException 异常
     */
    public void addOrderItems(List<OrderItem> items) throws SQLException {
        String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        for (OrderItem item : items) {
            qr.update(sql, item.getOrder().getId(), item.getProduct().getId(), item.getBuynum());
        }
    }

    /**
     * 使用sql批处理提高效率
     *
     * @param items 订单条目列表
     * @throws SQLException 异常
     */
    public void addOrderItemsByBatch(List<OrderItem> items) throws SQLException {
        String sql = "insert into orderitem (order_id,product_id,buynum) values(?,?,?)";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        Object[][] params = new Object[items.size()][];
        for (int i = 0; i < items.size(); i++) {
            OrderItem item = items.get(i);
            params[i] = new Object[]{item.getOrder().getId(), item.getProduct().getId(), item.getBuynum()};
        }
        Connection connection = ManagerThreadLocal.getConnection();
        if (connection != null) {
            qr.batch(connection, sql, params);
        }

    }
}
