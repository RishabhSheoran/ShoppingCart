package com.shopping.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.shopping.dao.OrderDetailDao;
import com.shopping.domain.OrderDetail;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public interface OrderDetailService {

    public void createOrderDetail(File f) throws IOException, SQLException, ParseException;
    public void deleteOrderDetail(int id) throws SQLException;
    public void updateOrderDetail(int id,int quantity) throws SQLException;

    public List<OrderDetail> getAllOrderDetail() throws SQLException;
    public OrderDetail getOrderDetailById(int id) throws SQLException;
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException;
}
