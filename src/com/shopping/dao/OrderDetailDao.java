package com.shopping.dao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.shopping.domain.OrderDetail;

/**
 * Created by rishabhsheoran on 1/31/17.
 */
public interface OrderDetailDao {
    public void createOrderDetail(String[] str) throws IOException, SQLException, ParseException;
    public void deleteOrderDetail(int id) throws SQLException;
    public void updateOrderDetail(int id,int quantity) throws SQLException;
    public List<OrderDetail> getAllOrderDetail() throws SQLException;
    public OrderDetail getOrderDetailById(int id) throws SQLException;
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException;
   // public void updateOrderDetailAmountWithProduct(BigDecimal price,int productId) throws SQLException;
}
