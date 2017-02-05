package com.shopping.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.shopping.dao.OrderDao;
import com.shopping.dao.OrderDaoImpl;
import com.shopping.dao.OrderDetailDao;
import com.shopping.dao.OrderDetailDaoImpl;
import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    static OrderDetailDao orderDetailDao;
    static OrderDao orderDao;
    static OrderService orderService;
    static {
        orderDao = new OrderDaoImpl();
        orderDetailDao=new OrderDetailDaoImpl();
        orderService=new OrderServiceImpl();
    }
    @Override
    public void createOrderDetail(File f1) throws IOException, SQLException, ParseException {
        File f = f1;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            sb.append(s).append("\n");
           // System.out.println(s);
        }
        s = sb.toString();
       // System.out.println(s);
        String[] str = s.split("\n");
        orderDetailDao.createOrderDetail(str);
        BigDecimal orderAmount;
        //0 :orderId, 1: productId, 2: quantity
        for (String st : str
                ) {
            String[] entry = st.split(",");
           orderAmount= orderService.totalAmount(Integer.parseInt(entry[0]));
           orderService.updateOrderAmount(Integer.parseInt(entry[0]), orderAmount);
        }

    }

    @Override
    public void deleteOrderDetail(int id) throws SQLException {
        //BigDecimal orderAmount=orderDetailDao.getOrderDetailById(id).getAmount();
        int orderId =orderDetailDao.getOrderDetailById(id).getOrderId();
        BigDecimal orderAmountToSubtract = orderDetailDao.getOrderDetailById(id).getAmount();
        orderDetailDao.deleteOrderDetail(id);
        BigDecimal orderAmountOld = orderService.getOrderById(orderId).getAmount();
        BigDecimal orderAmountNew = orderAmountOld.subtract(orderAmountToSubtract);
       // BigDecimal orderAmount = orderService.totalAmount(orderId);
        orderService.updateOrderAmount(orderId,orderAmountNew);
    }

    @Override
    public void updateOrderDetail(int id, int quantity) throws SQLException {
        orderDetailDao.updateOrderDetail(id,quantity);
        int orderId = orderDetailDao.getOrderDetailById(id).getOrderId();
        BigDecimal total=orderService.totalAmount(orderId);
        orderService.updateOrderAmount(orderId,total);
    }

    @Override
    public List<OrderDetail> getAllOrderDetail() throws SQLException {
        return orderDetailDao.getAllOrderDetail();
    }

    @Override
    public OrderDetail getOrderDetailById(int id) throws SQLException {
        return orderDetailDao.getOrderDetailById(id);
    }
    @Override
    public List<OrderDetail> getOrderDetailsByOrderId(int orderId) throws SQLException {

        return orderDetailDao.getOrderDetailsByOrderId(orderId);
    }
}
