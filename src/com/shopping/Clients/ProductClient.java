package com.shopping.Clients;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import com.shopping.service.ProductService;
import com.shopping.service.ProductServiceImpl;

/**
 * Created by rishabhsheoran on 2/6/17.
 */
public class ProductClient {
    static ProductService productService;
    static {
        productService = new ProductServiceImpl();
    }
    public static void main(String[] args) throws SQLException, IOException {
        ProductClient c =new ProductClient();
        File fp = new File("/Users/rishabhsheoran/projects/Assignments/ShoppingCart/src/com/shopping/ProductList.txt");
        c.productService.createProduct(fp);
        c.productService.updateProduct(5,"updatedProd5","#a5", BigDecimal.valueOf(400),30);
        c.productService.deleteProduct(6);
    }
}
