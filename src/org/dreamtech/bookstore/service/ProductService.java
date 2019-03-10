package org.dreamtech.bookstore.service;

import org.dreamtech.bookstore.dao.ProductDao;
import org.dreamtech.bookstore.model.PageResult;
import org.dreamtech.bookstore.model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 商品服务层
 * @Author: YiQing Xu
 * @Create: 2019-03-04 15:57
 */
public class ProductService {

    private ProductDao productDao = new ProductDao();

    /**
     * 分页查找
     *
     * @param page 页数
     * @return 分页对象
     */
    public PageResult<Product> findBooks(int page) {
        try {
            PageResult<Product> pr = new PageResult<>();
            long totalCount = productDao.count();
            pr.setTotalCount(totalCount);
            pr.setPageSize(4);
            int totalPage = (int) Math.ceil(totalCount * 1.0 / pr.getPageSize());
            pr.setTotalPage(totalPage);
            pr.setCurrentPage(page);
            List<Product> list = productDao.findBooks(page, pr.getPageSize());
            pr.setList(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 分页查找
     *
     * @param category 分类
     * @param page     页数
     * @return 分页对象
     */
    public PageResult<Product> findBooks(String category, int page) {
        try {
            PageResult<Product> pr = new PageResult<>();
            long totalCount = productDao.count(category);
            pr.setTotalCount(totalCount);
            pr.setPageSize(4);
            int totalPage = (int) Math.ceil(totalCount * 1.0 / pr.getPageSize());
            pr.setTotalPage(totalPage);
            pr.setCurrentPage(page);
            List<Product> list = productDao.findBooks(category, page, pr.getPageSize());
            pr.setList(list);
            return pr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据ID查找商品
     *
     * @param id 商品ID
     * @return 商品对象
     */
    public Product findBook(String id) {
        try {
            return productDao.findBook(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
