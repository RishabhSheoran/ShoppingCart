package com.shopping.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.shopping.domain.Product;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public interface ProductService {
    public void createProduct(File f) throws IOException, SQLException;
    public void deleteProduct(int id) throws SQLException;
    public void updateProduct(int id, String name, String code, BigDecimal price, int stock) throws SQLException;
    public List<Product> getAllProducts() throws SQLException;
    public Product getProductById(int id) throws SQLException;
}
