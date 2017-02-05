package com.shopping.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.shopping.dao.OrderDetailDao;
import com.shopping.dao.OrderDetailDaoImpl;
import com.shopping.dao.ProductDao;
import com.shopping.dao.ProductDaoImpl;
import com.shopping.domain.OrderDetail;
import com.shopping.domain.Product;
import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public class ProductServiceImpl implements ProductService{
    static ProductDao productDao;
    static OrderDetailDao orderDetailDao;
    static {
        productDao = new ProductDaoImpl();
        orderDetailDao=new OrderDetailDaoImpl();
    }

    @Override
    public void createProduct(File f1) throws IOException, SQLException {
        File f=f1;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s).append("\n");
            System.out.println(s);
        }
        s=sb.toString();
        System.out.println(s);
        String[] str = s.split("\n");
        productDao.createProduct(str);

    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        productDao.deleteProduct(id);
    }

    @Override
    public void updateProduct(int id, String name, String code, BigDecimal price, int stock) throws SQLException {
        productDao.updateProduct(id, name, code, price, stock);
       // orderDetailDao.updateOrderDetailAmountWithProduct(price,id);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> productList = productDao.getAllProducts();
        return productList;
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        return productDao.getProductById(id);
    }
}
