package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 个人中心
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebServlet("/myaccount")
public class MyAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            response.sendRedirect(request.getContextPath() + "/myAccount.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
