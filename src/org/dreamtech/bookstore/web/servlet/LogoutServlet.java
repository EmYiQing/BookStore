package org.dreamtech.bookstore.web.servlet;

;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 账户注销
 * @Author: YiQing Xu
 * @Create: 2019-03-04 14:11
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            response.addCookie(new Cookie(cookie.getName(), ""));
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
