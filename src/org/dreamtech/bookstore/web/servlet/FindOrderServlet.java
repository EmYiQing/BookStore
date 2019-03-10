package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.Order;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 查找订单
 * @Author: YiQing Xu
 * @Create: 2019-03-10 09:10
 */
@WebServlet("/findOrder")
public class FindOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.getWriter().write("非法访问");
            return;
        }
        OrderService os = new OrderService();
        List<Order> orders = os.findOrdersByUserId(user.getId() + "");
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/orderlist.jsp").forward(request, response);
    }
}
