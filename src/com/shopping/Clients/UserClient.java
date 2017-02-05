package com.shopping.Clients;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.shopping.service.UserService;
import com.shopping.service.UserServiceImpl;

/**
 * Created by rishabhsheoran on 2/6/17.
 */
public class UserClient {
    static UserService userService;
    static {
        userService = new UserServiceImpl();
    }
    public static void main(String[] args) throws IOException, SQLException {
        UserClient c = new UserClient();
        File fu = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/Users.txt");
         c.userService.createUser(fu);
         c.userService.updateUser(2,"bilal92","bilal","shaikh","kondwa");
         c.userService.deleteUser(3);
    }
}
