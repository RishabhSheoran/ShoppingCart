package com.shopping.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.domain.User;
import com.shopping.utils.DBConnection;

/**
 * Created by rishabhsheoran on 1/31/17.
 */
public class UserDaoImpl implements UserDao {

    @Override
    public void createUser(User user) throws IOException, SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;


            ps = con.prepareStatement("Insert into user (user_name,first_name,last_name,address) VALUES (?,?,?,?)");
            ps.setString(1, user.getUserName());
            //System.out.println(entry[0]);
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getAddress());
            ps.executeUpdate();


        // con.commit();

        con.close();




    }

    @Override
    public void deleteUser(int id) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps = null;
        ps=con.prepareStatement("delete from user where id = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public void updateUser(int id,String userName, String firstName, String lastName, String address) throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ps=con.prepareStatement("update user SET user_name=? , first_name=? , last_name=? , address=? where id=?;");
        ps.setString(1,userName);
        ps.setString(2,firstName);
        ps.setString(3,lastName);
        ps.setString(4,address);
        ps.setInt(5,id);
        ps.executeUpdate();
        con.close();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        ps= con.prepareStatement("Select * from user");
        rs=ps.executeQuery();
        while(rs.next()){
            User user = new User();
            int id = rs.getInt("id");
            String userName = rs.getString("user_name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String address = rs.getString("address");
            user.setId(id);
            user.setUserName(userName);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setAddress(address);
            userList.add(user);
        }
       /* for (User u:userList
             ) {
            System.out.println(u.getId() + "," +u.getUserName()+ "," +u.getFirstName()+ "," +u.getLastName()+ "," +u.getAddress());
        }*/
        con.close();
        return userList;
    }

    @Override
    public User getUserById(int id) throws SQLException {
        User user = new User();
        Connection con =DBConnection.getConnection();
        PreparedStatement ps=null;
        ResultSet rs=null;
        ps=con.prepareStatement("Select * from user where id=?");
        ps.setInt(1,id);
        rs=ps.executeQuery();
        rs.next();
        int id1 = rs.getInt("id");
        String userName = rs.getString("user_name");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String address = rs.getString("address");
        user.setId(id1);
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(address);
        con.close();
        return user;
    }

   /* public static void main(String[] args) throws IOException, SQLException {
        UserDao u = new UserDaoImpl();
       // u.createUser();
        u.getAllUsers();
       // u.deleteUser(5);
       // u.getAllUsers();
        User v;
        v=u.getUserById(1);
       // System.out.println(v.getId() + "," +v.getUserName()+ "," +v.getFirstName()+ "," +v.getLastName()+ "," +v.getAddress());

    }*/
}
