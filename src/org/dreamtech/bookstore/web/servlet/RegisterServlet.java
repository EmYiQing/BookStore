package org.dreamtech.bookstore.web.servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.dreamtech.bookstore.exception.UserException;
import org.dreamtech.bookstore.model.User;
import org.dreamtech.bookstore.service.UserService;
import org.dreamtech.bookstore.utils.CommonUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Program: BookStore
 * @Description: 用户注册
 * @Author: YiQing Xu
 * @Create: 2019-03-09 21:39
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String checkCodeClient = request.getParameter("checkcode");
            String checkCodeSession = (String) request.getSession().getAttribute("checkcode_session");
            if (checkCodeClient != null && checkCodeSession != null && !checkCodeClient.equals(checkCodeSession)) {
                request.setAttribute("checkcode_err", "验证码错误");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }
            if (checkCodeClient == null || checkCodeClient.equals("")) {
                request.setAttribute("checkcode_err", "请输入验证码");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
                return;
            }

            User user = new User();

            String email = request.getParameter("email");
            if (!email.matches("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$")) {
                throw new UserException("请输入正确的邮箱，将用于激活账户");
            }
            String telephone = request.getParameter("telephone");
            if (!telephone.matches("^1([38]\\d|5[0-35-9]|7[3678])\\d{8}$")) {
                throw new UserException("请输入正确的手机号");
            }
            String username = request.getParameter("username");
            if (!username.matches("^\\S{6,12}$")) {
                throw new UserException("用户名不合规");
            }
            String password = request.getParameter("password");
            if (!password.matches("^(?=.*[0-9].*)(?=.*[a-zA-Z].*).{6,20}$")) {
                throw new UserException("密码必须包含数字和字母");
            }
            String repassword = request.getParameter("repassword");
            if (!repassword.equals(password)) {
                throw new UserException("两次密码必须一致");
            }

            BeanUtils.populate(user, request.getParameterMap());

            user.setActiveCode(CommonUtils.getUUID());
            user.setRole("普通用户");
            user.setRegistTime(new Date());

            UserService us = new UserService();
            us.register(user);

            request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);
        } catch (UserException e) {
            request.setAttribute("register_err", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException("参数转模型失败");
        }
    }
}
