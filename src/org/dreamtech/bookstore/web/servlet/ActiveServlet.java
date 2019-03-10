package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.exception.UserException;
import org.dreamtech.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 激活邮件
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "text/html;charset=utf-8");
        String activeCode = request.getParameter("activeCode");
        UserService us = new UserService();
        try {
            us.activeUser(activeCode);
            response.getWriter().write("激活成功");
        } catch (UserException e) {
            response.getWriter().write(e.getMessage());
        }
    }
}
