package org.dreamtech.bookstore.model;

/**
 * @Program: BookStore
 * @Description: 订单条目模型
 * @Author: YiQing Xu
 * @Create: 2019-03-09 20:51
 */
public class OrderItem {
    //购买数量
    private int buynum;
    //订单
    private Order order;
    //商品
    private Product product;

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "buynum=" + buynum +
                ", order=" + order +
                ", product=" + product +
                '}';
    }
}
