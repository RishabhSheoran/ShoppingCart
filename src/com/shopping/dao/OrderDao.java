package com.shopping.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.shopping.domain.Order;

/**
 * Created by rishabhsheoran on 1/31/17.
 */
public interface OrderDao {
    public void createOrder(Order order) throws IOException, SQLException, ParseException;
    public void deleteOrder(int id) throws SQLException;
    public void updateOrder(int id, String orderDate,int userId) throws SQLException, ParseException;
    public void updateOrderAmount(int id) throws SQLException;
    public Order getOrderById(int id) throws SQLException;
    public List<Order> getAllOrders() throws SQLException;
}
