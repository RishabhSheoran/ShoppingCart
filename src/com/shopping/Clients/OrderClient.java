package com.shopping.Clients;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.shopping.service.OrderService;
import com.shopping.service.OrderServiceImpl;

/**
 * Created by rishabhsheoran on 2/6/17.
 */
public class OrderClient {
    static OrderService orderService;
    static {
        orderService = new OrderServiceImpl();
    }

    public static void main(String[] args) throws SQLException, IOException, ParseException {
        OrderClient c = new OrderClient();
        File fo = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/Orders.txt");
         c.orderService.createOrder(fo);
         c.orderService.updateOrder(6,"2017-02-22",1);
         c.orderService.deleteOrder(6);

    }
}
