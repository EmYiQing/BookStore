package org.dreamtech.bookstore.model;

import java.util.Date;
import java.util.List;

/**
 * @Program: BookStore
 * @Description: 订单模型
 * @Author: YiQing Xu
 * @Create: 2019-03-09 20:45
 */
public class Order {
    //订单编号
    private String id;
    //订单金额
    private double money;
    //收件人地址
    private String receiverAddress;
    //收件人姓名
    private String receiverName;
    //收件人电话
    private String receiverPhone;
    //支付状态 1：已支付 0：未支付
    private int paystate;
    //订单时间
    private Date ordertime;
    //订单内的所有商品
    private List<OrderItem> items;
    //用户对象
    private User user;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", money=" + money +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", paystate=" + paystate +
                ", ordertime=" + ordertime +
                ", user=" + user +
                '}';
    }
}
