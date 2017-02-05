package com.shopping.service;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.OrderDao;
import com.shopping.dao.OrderDaoImpl;
import com.shopping.dao.OrderDetailDao;
import com.shopping.dao.OrderDetailDaoImpl;
import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;
import com.shopping.domain.Product;
import com.shopping.domain.User;

/**
 * Created by rishabhsheoran on 2/5/17.
 */
public class PrintBillCsv {
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
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String SPACE_SEPARATOR = "\t";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "OrderId,UserId,FirstName,LastName,ProductName,ProductCode,Quantity,Price,Amount";

    public static void writeCsvFile(String fileName, int orderId) throws SQLException, IOException {
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(fileName);

        //Write the CSV file header
        fileWriter.append(FILE_HEADER.toString());

        //Add a new line separator after the header
        fileWriter.append(NEW_LINE_SEPARATOR);

        Order order = orderService.getOrderById(orderId);
        fileWriter.append(String.valueOf(order.getId()));
        fileWriter.append(COMMA_DELIMITER);

       // System.out.println("Order no.: "+order.getId() + "   Order Date: "+order.getOrderDate().toString());
        int userId = order.getUserId();
        User user = userService.getUserById(userId);
        fileWriter.append(String.valueOf(user.getId()));
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getFirstName());
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(user.getLastName());
        fileWriter.append(COMMA_DELIMITER);
        /*System.out.println("First name: "+user.getFirstName() + "   Last name: "+user.getLastName() +
                "   Username:"+user.getUserName());*/
        List<OrderDetail> orderDetailList = orderDetailService.getOrderDetailsByOrderId(orderId);
        int productId;

        for (OrderDetail od:orderDetailList
                ) {
            productId=od.getProductId();
            Product product = productService.getProductById(productId);
           // System.out.println(product.getId()+"    "+product.getName()+"  "+product.getCode());
            fileWriter.append(product.getName());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(product.getCode());
            /*System.out.println("orderDetailId: "+od.getId()+" quantity: "+od.getQuantity()+" price: "+od.getPrice()
                    +" amount: "+od.getAmount());*/
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(od.getQuantity()));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(String.valueOf(od.getPrice())));
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(String.valueOf(od.getAmount()));
            fileWriter.append(NEW_LINE_SEPARATOR);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(COMMA_DELIMITER);
        }
        fileWriter.append(NEW_LINE_SEPARATOR);
        fileWriter.append("Total Amount:");
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(COMMA_DELIMITER);
        fileWriter.append(String.valueOf(order.getAmount()));
        fileWriter.append(NEW_LINE_SEPARATOR);
        fileWriter.append(NEW_LINE_SEPARATOR);
        //System.out.println("Total amount: "+order.getAmount());
        fileWriter.flush();
        fileWriter.close();


    }
}
