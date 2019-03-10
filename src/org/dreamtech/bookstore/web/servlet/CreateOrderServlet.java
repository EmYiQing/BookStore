package org.dreamtech.bookstore.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.dreamtech.bookstore.model.Order;
import org.dreamtech.bookstore.model.OrderItem;
import org.dreamtech.bookstore.model.Product;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.service.OrderService;
import org.dreamtech.bookstore.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Program: BookStore
 * @Description: 创建订单
 * @Author: YiQing Xu
 * @Create: 2019-03-09 20:56
 */
@WebServlet("/createOrder")
public class CreateOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.getWriter().write("非法访问");
            return;
        }
        @SuppressWarnings("unchecked")
        Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if (cart == null || cart.size() == 0) {
            response.getWriter().write("购物车没有商品");
            return;
        }
        Order order = new Order();
        try {
            BeanUtils.populate(order, request.getParameterMap());
            order.setId(CommonUtils.getUUID());
            order.setOrdertime(new Date());
            order.setUser(user);
            List<OrderItem> items = new ArrayList<>();
            double totalPrice = 0;
            for (Map.Entry<Product, Integer> entry : cart.entrySet()) {
                OrderItem item = new OrderItem();
                item.setBuynum(entry.getValue());
                item.setProduct(entry.getKey());
                item.setOrder(order);
                totalPrice += entry.getKey().getPrice() * entry.getValue();
                items.add(item);
            }
            order.setMoney(totalPrice);

            order.setItems(items);

            OrderService os = new OrderService();
            os.createOrder(order);

            request.getSession().removeAttribute("cart");

            request.getRequestDispatcher(request.getContextPath() + "/pay.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
