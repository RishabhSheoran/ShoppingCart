package com.shopping.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public interface OrderService {
    public void createOrder(File f) throws IOException, SQLException, ParseException;
    public void deleteOrder(int id) throws SQLException;
    public void updateOrder(int id, String orderDate, int userId) throws SQLException,ParseException;
    public List<Order> getAllOrders() throws SQLException;
    public Order getOrderById(int id) throws SQLException;
    public BigDecimal totalAmount(int orderId) throws SQLException;
    public void updateOrderAmount(int orderId, BigDecimal amount) throws SQLException;
    public void PrintBill(int orderId) throws SQLException;
}
