package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.Product;
import org.dreamtech.bookstore.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: BookStore
 * @Description: 商品详情
 * @Author: YiQing Xu
 * @Create: 2019-03-04 17:34
 */
@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductService ps = new ProductService();
        Product product = ps.findBook(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/product_info.jsp").forward(request,response);
    }
}
