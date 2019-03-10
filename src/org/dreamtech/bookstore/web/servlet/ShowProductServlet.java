package org.dreamtech.bookstore.web.servlet;

import org.dreamtech.bookstore.model.PageResult;
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
 * @Description: 分页显示商品
 * @Author: YiQing Xu
 * @Create: 2019-03-04 16:14
 */
@WebServlet("/showProductByPage")
public class ShowProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");
        String pageStr = request.getParameter("page");
        int page = 1;
        if (pageStr != null && !"".equals(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        ProductService ps = new ProductService();
        PageResult<Product> pageResult;
        if (category == null || "".equals(category)) {
            pageResult = ps.findBooks(page);
        } else {
            pageResult = ps.findBooks(category, page);
        }
        request.setAttribute("pageResult", pageResult);
        request.setAttribute("category", category);
        request.getRequestDispatcher("/product_list.jsp").forward(request, response);
    }
}
