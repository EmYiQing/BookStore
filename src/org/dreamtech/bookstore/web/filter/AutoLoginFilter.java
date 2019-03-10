package org.dreamtech.bookstore.web.filter;


import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 自动登陆功能实现
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getServletPath();
        if ("/logout.jsp".equals(path)) {
            chain.doFilter(req, resp);
            return;
        }
        boolean flag = true;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getValue().equals("")) {
                    flag = false;
                }
            }
            if (!flag) {
                chain.doFilter(req, resp);
                return;
            }
        }
        if (request.getSession().getAttribute("user") != null) {
            chain.doFilter(request, response);
            return;
        }
        User loginUser = new User();
        Cookie[] allCookie = request.getCookies();
        Cookie userCookie = null;
        if (allCookie != null) {
            for (Cookie c : allCookie) {
                if ("autoLoginCookie".equals(c.getName())) {
                    userCookie = c;
                    break;
                }
            }
        }

        if (userCookie == null) {
            chain.doFilter(request, response);
            return;
        }

        String[] u = userCookie.getValue().split("@");
        String username = u[0];
        String password = u[1];
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        UserService us = new UserService();
        try {
            loginUser = us.login(username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (loginUser == null) {
            chain.doFilter(request, response);
            return;
        }

        request.getSession().setAttribute("user", loginUser);
        chain.doFilter(request, response);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

