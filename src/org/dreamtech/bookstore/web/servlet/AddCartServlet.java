package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.Product;
import org.dreamtech.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: BookStore
 * @Description: 添加到购物车
 * @Author: YiQing Xu
 * @Create: 2019-03-06 16:31
 */
@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductService ps = new ProductService();
        Product p = ps.findBook(id);
        @SuppressWarnings("unchecked")
        Map<Product, Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            cart.put(p, 1);
        } else {
            if (cart.containsKey(p)) {
                cart.put(p, cart.get(p) + 1);
            } else {
                cart.put(p, 1);
            }
        }
        request.getSession().setAttribute("cart", cart);
        request.getRequestDispatcher("/cart.jsp").forward(request,response);
    }
}
