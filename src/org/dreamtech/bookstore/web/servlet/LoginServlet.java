package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.exception.UserException;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Program: BookStore
 * @Description: 登陆
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rem = request.getParameter("remember");
            String auto = request.getParameter("autologin");
            if (rem != null) {
                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(60 * 60 * 24 * 3);
                usernameCookie.setPath("/");
                response.addCookie(usernameCookie);
            } else {
                Cookie usernameCookie = new Cookie("username", "");
                usernameCookie.setMaxAge(0);
                usernameCookie.setPath("/");
                response.addCookie(usernameCookie);
            }
            if (auto != null) {
                Cookie cookie = new Cookie("autoLoginCookie", username + "@" + password);
                cookie.setMaxAge(7 * 24 * 60 * 60);
                cookie.setPath("/");
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("autoLoginCookie", "");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            UserService us = new UserService();
            User user = us.login(username, password);
            request.getSession().setAttribute("user", user);
            if ("管理员".equals(user.getRole())) {
                response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("login_msg", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
