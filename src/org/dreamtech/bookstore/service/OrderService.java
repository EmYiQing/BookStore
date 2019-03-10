package org.dreamtech.bookstore.service;

import org.dreamtech.bookstore.dao.OrderDao;
import org.dreamtech.bookstore.dao.OrderItemDao;
import org.dreamtech.bookstore.dao.ProductDao;
import org.dreamtech.bookstore.model.Order;
import org.dreamtech.bookstore.utils.ManagerThreadLocal;

import java.sql.SQLException;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 订单服务层
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:35
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private ProductDao productDao = new ProductDao();

    /**
     * 创建订单
     *
     * @param order 订单对象
     */
    public void createOrder(Order order) {
        try {
            ManagerThreadLocal.beginTransaction();
            orderDao.add(order);
            orderItemDao.addOrderItemsByBatch(order.getItems());
            productDao.updatePNumByBatch(order.getItems());
            ManagerThreadLocal.commitTransaction();
        } catch (SQLException e) {
            e.printStackTrace();
            ManagerThreadLocal.rollbackTransaction();
        }
    }

    /**
     * 根据用户ID查找订单
     *
     * @param userId 用户ID
     * @return 订单列表
     */
    public List<Order> findOrdersByUserId(String userId) {
        try {
            return orderDao.findOrdersByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据订单ID查到订单
     *
     * @param orderId 订单ID
     * @return 订单对象
     */
    public Order findOrderByOrderId(String orderId) {
        try {
            return orderDao.findOrderByOrderId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
