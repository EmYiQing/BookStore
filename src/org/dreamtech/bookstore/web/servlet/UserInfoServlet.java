package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.exception.UserException;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 个人中心处理
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebServlet("/findUserById")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userid = request.getParameter("id");
        UserService us = new UserService();
        try {
            User user = us.findUserById(userid);
            request.setAttribute("modify_user", user);
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
        } catch (UserException e) {
            e.printStackTrace();
            response.getWriter().write(e.getMessage());
        }

    }
}
