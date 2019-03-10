package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.Product;
import org.dreamtech.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Program: BookStore
 * @Description: 改变购物车内商品数量
 * @Author: YiQing Xu
 * @Create: 2019-03-06 17:44
 */
@WebServlet("/changeNum")
public class ChangeCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String num = request.getParameter("num");
        ProductService ps = new ProductService();
        Product p = ps.findBook(id);
        @SuppressWarnings("unchecked")
        Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if (cart.containsKey(p)) {
            if ("0".equals(num)) {
                cart.remove(p);
            } else {
                cart.put(p, Integer.parseInt(num));
            }
        }
        request.getSession().setAttribute("cart", cart);
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}
