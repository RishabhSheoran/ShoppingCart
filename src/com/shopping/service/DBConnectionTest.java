package com.shopping.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 1/27/17.
 */
public class DBConnectionTest {
    private static final String QUERY = "Select * from user";

    public static void main(String[] args) {
        try(Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY)) {

            while(rs.next()){
                int id = rs.getInt("id");
                String userName = rs.getString("user_name");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String address = rs.getString("address");
                System.out.println(id + "," +userName+ "," +firstName+ "," +lastName+ "," +address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
