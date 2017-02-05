package com.shopping.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;
import com.shopping.domain.Product;
import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 1/31/17.
 */
public class OrderDetailDaoImpl implements OrderDetailDao {
    static OrderDao orderDao;
    static  ProductDao productDao;
    static OrderDetailDao orderDetailDao;



    static {
        orderDao = new OrderDaoImpl();
        productDao = new ProductDaoImpl();
        orderDetailDao = new OrderDetailDaoImpl();
    }

    @Override
    public void createOrderDetail(String[] str) throws IOException, SQLException, ParseException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;


        //0 :orderId, 1: productId, 2: quantity
        Product product = new Product();

        for (String st : str
                ) {
           // System.out.println(st);
            String[] entry = st.split(",");
            ps = con.prepareStatement("Insert into orderdetail (order_id,product_id,quantity,price,amount) VALUES (?,?,?,?,?)");
            ps.setInt(1, Integer.parseInt(entry[0]));
            ps.setInt(2, Integer.parseInt(entry[1]));
            ps.setInt(3, Integer.parseInt(entry[2]));
            product=productDao.getProductById(Integer.parseInt(entry[1]));//getting price by productid
            ps.setBigDecimal(4, product.getPrice());
            BigDecimal amount;
            amount = product.getPrice().multiply(new BigDecimal(entry[2]));
            ps.setBigDecimal(5, amount);
            ps.executeUpdate();
        }

        // con.commit();

        con.close();
    }

    @Override
    public void deleteOrderDetail(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement("delete from orderdetail where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public void updateOrderDetail(int id, int quantity) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ps = con.prepareStatement("update orderdetail set  quantity=?, amount=? where id=?");
       // BigDecimal amount = new BigDecimal(quantity).multiply(price);
        ps.setInt(1, quantity);
        BigDecimal amount = orderDetailDao.getOrderDetailById(id).getPrice().multiply(new BigDecimal(quantity));
        ps.setBigDecimal(2,amount);
        ps.setInt(3, id);
        ps.executeUpdate();
        con.close();
    }

   /* @Override
    public void updateOrderDetailAmountWithProduct(BigDecimal price, int productId) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs=null;
        ps=con.prepareStatement("select id,quantity from orderdetail where product_id=?");
        ps.setInt(1,productId);
        rs=ps.executeQuery();
        while(rs.next()){
            int id = rs.getInt("id");
            int quant = rs.getInt("quantity");
            BigDecimal amount = price.multiply(new BigDecimal(quant));
            ps = con.prepareStatement("update orderdetail set price=?,amount=? where product_id=?");
            ps.setBigDecimal(1,price);
            ps.setBigDecimal(2,amount);
            ps.setInt(3,productId);
            ps.executeUpdate();
        }

        con.close();
    }*/

    @Override
    public List<OrderDetail> getAllOrderDetail() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement("select * from orderdetail");
        rs = ps.executeQuery();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            int id = rs.getInt("id");
            int orderId = rs.getInt("order_id");
            int productId = rs.getInt("product_id");
            int quantity = rs.getInt("quantity");
            BigDecimal price = rs.getBigDecimal("price");
            BigDecimal amount = rs.getBigDecimal("amount");
            orderDetail.setId(id);
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(productId);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(price);
            orderDetail.setAmount(amount);
            orderDetailList.add(orderDetail);
        }
        /*for (OrderDetail od : orderDetailList
                ) {
            System.out.println(od.getId() + "," + od.getOrderId() + "," + od.getProductId() + "," +
                    od.getQuantity() + "," + od.getPrice() + "," + od.getAmount());

        }*/
        con.close();
        return orderDetailList;
    }

    @Override
    public OrderDetail getOrderDetailById(int id1) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement("select * from orderdetail where id=?");
        ps.setInt(1,id1);
        rs = ps.executeQuery();
        rs.next();
        OrderDetail orderDetail = new OrderDetail();
        int id = rs.getInt("id");
        int orderId1 = rs.getInt("order_id");
        int productId = rs.getInt("product_id");
        int quantity = rs.getInt("quantity");
        BigDecimal price = rs.getBigDecimal("price");
        BigDecimal amount = rs.getBigDecimal("amount");
        orderDetail.setId(id);
        orderDetail.setOrderId(orderId1);
        orderDetail.setProductId(productId);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(price);
        orderDetail.setAmount(amount);
        con.close();
        return orderDetail;
    }

    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement("select * from orderdetail where order_id=?");
        ps.setInt(1,orderId);
        rs = ps.executeQuery();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        while (rs.next()) {
            OrderDetail orderDetail = new OrderDetail();
            int id = rs.getInt("id");
            int orderId1 = rs.getInt("order_id");
            int productId = rs.getInt("product_id");
            int quantity = rs.getInt("quantity");
            BigDecimal price = rs.getBigDecimal("price");
            BigDecimal amount = rs.getBigDecimal("amount");
            orderDetail.setId(id);
            orderDetail.setOrderId(orderId1);
            orderDetail.setProductId(productId);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(price);
            orderDetail.setAmount(amount);
            orderDetailList.add(orderDetail);
        }
        con.close();
        return orderDetailList;
    }
}
