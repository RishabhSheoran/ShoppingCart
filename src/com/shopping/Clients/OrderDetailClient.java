package com.shopping.Clients;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.shopping.service.OrderDetailService;
import com.shopping.service.OrderDetailServiceImpl;

/**
 * Created by rishabhsheoran on 2/6/17.
 */
public class OrderDetailClient {
    static OrderDetailService orderDetailService;
    static {
        orderDetailService = new OrderDetailServiceImpl();
    }

    public static void main(String[] args) throws ParseException, SQLException, IOException {
        OrderDetailClient c = new OrderDetailClient();
        File fod = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/OrderDetail.txt");
         c.orderDetailService.createOrderDetail(fod);
         c.orderDetailService.deleteOrderDetail(4);
         c.orderDetailService.updateOrderDetail(1,6);

    }
}
