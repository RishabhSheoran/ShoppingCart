package com.shopping.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.Product;
import com.shopping.domain.User;
import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 1/31/17.
 */
public class ProductDaoImpl implements ProductDao {
    @Override
    public void createProduct(Product prod) throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;

            ps = con.prepareStatement("Insert into product (name,code,price,stock) VALUES (?,?,?,?)");
            ps.setString(1,prod.getName() );
           // System.out.println(entry[0]);
            ps.setString(2, prod.getCode());
            ps.setBigDecimal(3, prod.getPrice());
            ps.setInt(4, prod.getStock());
            ps.executeUpdate();


        // con.commit();

        con.close();
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ps=con.prepareStatement("delete from product where id = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public void updateProduct(int id, String name, String code, BigDecimal price, int stock ) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ps=con.prepareStatement("update product SET name=? , code=? , price=? , stock=? where id=?;");
        ps.setString(1,name);
        ps.setString(2,code);
        ps.setBigDecimal(3, price);
        ps.setInt(4,stock);
        ps.setInt(5,id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        ps= con.prepareStatement("Select * from product");
        rs=ps.executeQuery();
        while(rs.next()){
            Product product = new Product();
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String code = rs.getString("code");
            BigDecimal price = rs.getBigDecimal("price");
            int stock = rs.getInt("stock");
            product.setId(id);
            product.setName(name);
            product.setCode(code);
            product.setPrice(price);
            product.setStock(stock);
            productList.add(product);
        }
       /* for (User u:userList
             ) {
            System.out.println(u.getId() + "," +u.getUserName()+ "," +u.getFirstName()+ "," +u.getLastName()+ "," +u.getAddress());
        }*/
        con.close();
        return productList;
    }

    @Override
    public Product getProductById(int id1) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement("select * from product where id=?");
        ps.setInt(1,id1);
        rs = ps.executeQuery();
        Product product = new Product();
        rs.next();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String code = rs.getString("code");
        BigDecimal price = rs.getBigDecimal("price");
        int stock = rs.getInt("stock");
        product.setId(id);
        product.setName(name);
        product.setCode(code);
        product.setPrice(price);
        product.setStock(stock);
        con.close();
        return product;
    }

}
