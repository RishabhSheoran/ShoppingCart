package com.shopping.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by rishabhsheoran on 1/25/17.
 */
public class Order {
    private int id;
    private Date orderDate;
    private BigDecimal amount;
    private int userId;
    private List<OrderDetail> orderDetails;     //Map<String,OrderDetail> map; is preferred but complex

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
