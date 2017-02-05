package com.shopping.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;
import com.shopping.domain.User;
import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 1/31/17.
 */
public class OrderDaoImpl implements OrderDao {
    static OrderDetailDao orderDetailDao;



    static {
        orderDetailDao = new OrderDetailDaoImpl();
    }

    @Override
    public void createOrder(String[] str) throws IOException, SQLException, ParseException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        //List<OrderDetail> orderDetailList = orderDetailDao.getOrderDetailsByOrderId();
        for (String st:str
                ) {
           // System.out.println(st);
            String[] entry = st.split(",");
            ps = con.prepareStatement("Insert into orders (orderDate,userId) VALUES (?,?)");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(entry[0]);
            ps.setDate(1,new java.sql.Date(date.getTime()) );
           // System.out.println(entry[0]);
          //  ps.setBigDecimal(2, new BigDecimal(entry[1]));
            ps.setInt(2, Integer.parseInt(entry[1]) );
            ps.executeUpdate();
        }

        // con.commit();

        con.close();
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ps=con.prepareStatement("delete from orders where id = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public void updateOrder(int id, String orderDate,int userId) throws SQLException, ParseException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ps=con.prepareStatement("update orders SET orderDate=? , userId=?  where id=?;");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(orderDate);
        ps.setDate(1,new java.sql.Date(date.getTime()) );
        ps.setInt(2, userId);
        ps.setInt(3,id);
        ps.executeUpdate();
        con.close();
    }
    @Override
    public void updateOrderAmount(int productId) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ps=con.prepareStatement("update orders SET amount=? where id=?");

    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        ps= con.prepareStatement("Select * from orders");
        rs=ps.executeQuery();
        while(rs.next()) {
            Order order = new Order();
            int id1 = rs.getInt("id");
            Date date = rs.getDate("orderDate");
            BigDecimal amount = rs.getBigDecimal("amount");
            int userId = rs.getInt("userId");
            order.setId(id1);
            order.setOrderDate(date);
            order.setAmount(amount);
            order.setUserId(userId);
            order.setOrderDetails(null);
            orderList.add(order);
        }
        return orderList;
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        Order order = new Order();
        Connection con =DBConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        ps=con.prepareStatement("Select * from orders where id=?");
        ps.setInt(1,id);
        rs=ps.executeQuery();
        rs.next();
        int id1 = rs.getInt("id");
        Date date = rs.getDate("orderDate");
        BigDecimal amount = rs.getBigDecimal("amount");
        int userId = rs.getInt("userId");
        order.setId(id1);
        order.setOrderDate(date);
        order.setAmount(amount);
        order.setUserId(userId);
        order.setOrderDetails(null);
        con.close();
        return order;
    }
}
