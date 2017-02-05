package com.shopping.Clients;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import com.shopping.service.PrintBillCsv;
import com.shopping.service.OrderDetailService;
import com.shopping.service.OrderDetailServiceImpl;
import com.shopping.service.OrderService;
import com.shopping.service.OrderServiceImpl;
import com.shopping.service.ProductService;
import com.shopping.service.ProductServiceImpl;
import com.shopping.service.UserService;
import com.shopping.service.UserServiceImpl;

/**
 * Created by rishabhsheoran on 2/5/17.
 */
public class ClientFinal {
    static UserService userService;
    static ProductService productService;
    static OrderService orderService;
    static OrderDetailService orderDetailService;
    static PrintBillCsv csvFileWriter;
    static {
        userService = new UserServiceImpl();
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl();
        orderDetailService = new OrderDetailServiceImpl();
        csvFileWriter = new PrintBillCsv();
    }

    public static void main(String[] args) throws IOException, SQLException, ParseException {
        ClientFinal c = new ClientFinal();
        //  c.orderService.PrintBill(1);
        c.csvFileWriter.writeCsvFile("bill.csv",3);
    }
}

