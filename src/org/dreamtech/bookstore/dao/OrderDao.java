package org.dreamtech.bookstore.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.dreamtech.bookstore.model.Order;
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
 * @Description: 订单DAO
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
public class OrderDao {
    /**
     * 新增订单
     *
     * @param order 订单对象
     * @throws SQLException 异常
     */
    public void add(Order order) throws SQLException {
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add(order.getId());
        params.add(order.getMoney());
        params.add(order.getReceiverAddress());
        params.add(order.getReceiverName());
        params.add(order.getReceiverPhone());
        params.add(order.getPaystate());
        params.add(order.getOrdertime());
        params.add(order.getUser().getId());
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        Connection connection = ManagerThreadLocal.getConnection();
        if (connection != null) {
            qr.update(connection, sql, params.toArray());
        }
    }

    /**
     * 通过用户ID订单数据
     *
     * @param userId 用户ID
     * @return 查询到的订单列表
     * @throws SQLException 异常
     */
    public List<Order> findOrdersByUserId(String userId) throws SQLException {
        String sql = "select * from orders where user_id=?";
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        return qr.query(sql, new BeanListHandler<>(Order.class), userId);
    }

    /**
     * 通过订单ID查询订单数据
     *
     * @param orderId 订单ID
     * @return 订单
     */
    public Order findOrderByOrderId(String orderId) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Utils.getDataSource());
        String sql1 = "select * from orders where id=?";
        String sql2 = "select o.*,p.name,p.price from orderitem o,products p where o.product_id=p.id and order_id=?";
        List<OrderItem> Items = qr.query(sql2, resultSet -> {
            List<OrderItem> items = new ArrayList<>();
            while (resultSet.next()) {
                OrderItem item = new OrderItem();
                item.setBuynum(resultSet.getInt("buynum"));
                Product product = new Product();
                product.setId(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                item.setProduct(product);
                items.add(item);
            }
            return items;
        }, orderId);
        Order order = qr.query(sql1, new BeanHandler<>(Order.class), orderId);
        order.setItems(Items);
        return order;
    }
}
