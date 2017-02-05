package com.shopping;

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
        File fu = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/Users.txt");
       // c.userService.createUser(fu);
       // c.userService.updateUser(2,"bilal92","bilal","shaikh","kondwa");
        // c.userService.deleteUser(3);
        File fp = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/ProductList.txt");
       // c.productService.createProduct(fp);
       // c.productService.updateProduct(5,"updatedProd5","#a5", BigDecimal.valueOf(400),30);
       // c.productService.deleteProduct(6);
        File fo = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/Orders.txt");
       // c.orderService.createOrder(fo);
       // c.orderService.updateOrder(6,"2017-02-22",1);
       // c.orderService.deleteOrder(6);
        File fod = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/OrderDetail.txt");
       // c.orderDetailService.createOrderDetail(fod);
       // c.orderDetailService.deleteOrderDetail(4);
       // c.orderDetailService.updateOrderDetail(1,6);
     //  c.orderService.PrintBill(1);
       c.csvFileWriter.writeCsvFile("bill.csv",3);
    }
}

