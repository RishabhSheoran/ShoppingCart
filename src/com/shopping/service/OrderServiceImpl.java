package com.shopping.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.shopping.dao.OrderDao;
import com.shopping.dao.OrderDaoImpl;
import com.shopping.dao.OrderDetailDao;
import com.shopping.dao.OrderDetailDaoImpl;
import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;
import com.shopping.domain.Product;
import com.shopping.domain.User;
import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public class OrderServiceImpl implements OrderService {
    static OrderDao orederDao;
    static OrderDetailDao orderDetailDao;
    static OrderService orderService;
    static UserService userService;
    static OrderDetailService orderDetailService;
    static ProductService productService;
    static{
        orederDao = new OrderDaoImpl();
        orderDetailDao = new OrderDetailDaoImpl();
        orderService = new OrderServiceImpl();
        userService = new UserServiceImpl();
        orderDetailService = new OrderDetailServiceImpl();
        productService = new ProductServiceImpl();
    }

    @Override
    public void createOrder(File f1) throws IOException, SQLException, ParseException {
        File f=f1;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s).append("\n");
            //  System.out.println(s);
        }
        s=sb.toString();
        //  System.out.println(s);
        String[] str = s.split("\n");
        //orederDao.createOrder(str);
        for (String st:str
                ) {
            Order order = new Order();
            // System.out.println(st);
            String[] entry = st.split(",");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse(entry[0]);
            order.setOrderDate(new java.sql.Date(date.getTime()));
            order.setUserId(Integer.parseInt(entry[1]));
            orederDao.createOrder(order);
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        orederDao.deleteOrder(id);
    }

    @Override
    public void updateOrder(int id, String orderDate, int userId) throws  SQLException, ParseException {
        orederDao.updateOrder(id,orderDate,userId);
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        return orederDao.getOrderById(id);
    }
    @Override
    public void updateOrderAmount(int orderId, BigDecimal amount) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ps=con.prepareStatement("update orders SET amount=? where id=?;");
        ps.setBigDecimal(1,amount);
        ps.setInt(2,orderId);
        ps.executeUpdate();
        con.close();
    }


    @Override
    public BigDecimal totalAmount(int orderId) throws SQLException {
        List<OrderDetail> orderDetailList = orderDetailDao.getOrderDetailsByOrderId(orderId);
        BigDecimal total= BigDecimal.valueOf(0);
        for (OrderDetail od : orderDetailList
                ) {
            total = total.add(od.getAmount());
        }
        return total;
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return orederDao.getAllOrders();
    }

    @Override
    public void PrintBill(int orderId) throws SQLException {
        Order order = orderService.getOrderById(orderId);
        System.out.println("Order no.: "+order.getId() + "   Order Date: "+order.getOrderDate().toString());
        int userId = order.getUserId();
        User user = userService.getUserById(userId);
        System.out.println("First name: "+user.getFirstName() + "   Last name: "+user.getLastName() +
                "   Username:"+user.getUserName());
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailsByOrderId(orderId);
        int productId;
        for (OrderDetail od:orderDetailList
             ) {
            productId=od.getProductId();
            Product product = productService.getProductById(productId);
            System.out.println(product.getId()+"    "+product.getName()+"  "+product.getCode());
            System.out.println("orderDetailId: "+od.getId()+" quantity: "+od.getQuantity()+" price: "+od.getPrice()
            +" amount: "+od.getAmount());
        }
        System.out.println("Total amount: "+order.getAmount());
    }
}
