package com.shopping;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.shopping.domain.Order;
import com.shopping.domain.OrderDetail;
import com.shopping.domain.Product;
import com.shopping.domain.User;
import com.shopping.service.OrderDetailService;
import com.shopping.service.OrderDetailServiceImpl;
import com.shopping.service.OrderService;
import com.shopping.service.OrderServiceImpl;
import com.shopping.service.ProductService;
import com.shopping.service.ProductServiceImpl;
import com.shopping.service.UserService;
import com.shopping.service.UserServiceImpl;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public class Client {
    static UserService userService;
    static ProductService productService;
    static OrderService orderService;
    static OrderDetailService orderDetailService;
    static {
        userService = new UserServiceImpl();
        productService = new ProductServiceImpl();
        orderService = new OrderServiceImpl();
        orderDetailService=new OrderDetailServiceImpl();
    }

    public static void main(String[] args) throws IOException, SQLException, ParseException {
        Client c= new Client();
        File fu = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/Users.txt");
       // c.userService.createUser(fu);
       // c.userService.deleteUser(31);
        List<User> userList = userService.getAllUsers();
        for (User u:userList
             ) {
            System.out.println(u.getId() + "," +u.getUserName()+ "," +u.getFirstName()+ "," +u.getLastName()+ "," +u.getAddress());
        }
        User u = userService.getUserById(1);
        //System.out.println(u.getId() + "," +u.getUserName()+ "," +u.getFirstName()+ "," +u.getLastName()+ "," +u.getAddress());
        //userService.updateUser(30);


        File fp = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/ProductList.txt");
        //productService.createProduct(fp);
        //productService.deleteProduct(3);
        productService.updateProduct(2,"updateProd2","#ua2", new BigDecimal(150.50),15);
        List<Product> productList = productService.getAllProducts();
        for (Product prod:productList
             ) {
            System.out.println(prod.getId()+prod.getName()+prod.getCode()+prod.getPrice()+prod.getStock());
        }
        Product p = productService.getProductById(1);
        System.out.println(p.getId() + p.getName());
        File fo = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/Orders.txt");
        //c.orderService.createOrder(fo);
        // c.orderService.deleteOrder(6);
       // c.orderService.updateOrder(7,"2009-07-14",new BigDecimal(500.00),7);
        Order o = c.orderService.getOrderById(1);
        System.out.println(o.getId() + o.getOrderDate().toString() + o.getAmount() + o.getUserId());
        List<Order> orderList= orderService.getAllOrders();
        for (Order ord:orderList
             ) {
            System.out.println(o.getId() + ord.getOrderDate().toString() + ord.getAmount() + ord.getUserId());
        }

        File f = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/OrderDetail.txt");
        //c.orderDetailService.createOrderDetail(f);
        //c.orderDetailService.deleteOrderDetail(4);
        //c.orderDetailService.updateOrderDetail(1,6);
        //c.orderDetailService.deleteOrderDetail(3);
     //  List<OrderDetail> orderDetailList= c.orderDetailService.getOrderDetailsByOrderId(7);
      /* List<OrderDetail> orderDetailList1=c.orderDetailService.getAllOrderDetail();
        System.out.println("orderdetail:");
        for (OrderDetail od : orderDetailList1
                ) {
            System.out.println(od.getId() + "," + od.getOrderId() + "," + od.getProductId() + "," +
                    od.getQuantity() + "," + od.getPrice() + "," + od.getAmount());

        }*/
       /* for (OrderDetail od : orderDetailList
                ) {
            System.out.println(od.getId() + "," + od.getOrderId() + "," + od.getProductId() + "," +
                    od.getQuantity() + "," + od.getPrice() + "," + od.getAmount());
        }*/
    }
}
