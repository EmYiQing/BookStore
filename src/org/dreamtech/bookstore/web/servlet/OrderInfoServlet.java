package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.Order;
import org.dreamtech.bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 订单信息
 * @Author: YiQing Xu
 * @Create: 2019-03-10 10:05
 */
@WebServlet("/findOrderByOrderId")
public class OrderInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");
        OrderService os = new OrderService();
        Order order = os.findOrderByOrderId(orderId);
        request.setAttribute("order", order);
        request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
    }
}
