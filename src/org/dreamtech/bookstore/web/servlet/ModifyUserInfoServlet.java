package org.dreamtech.bookstore.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
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
 * @Description: 修改用户信息
 * @Author: YiQing Xu
 * @Create: 2019-03-04 13:34
 */
@WebServlet("/modifyUserInfo")
public class ModifyUserInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            UserService us = new UserService();
            us.modifyUserInfo(user);
            response.sendRedirect(request.getContextPath() + "/modifyUserInfoSuccess.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(e.getMessage());
        }
    }
}
