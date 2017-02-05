package com.shopping.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.shopping.dao.UserDao;
import com.shopping.dao.UserDaoImpl;
import com.shopping.domain.User;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public class UserServiceImpl implements UserService{
    static UserDao userDao;
    static {
        userDao = new UserDaoImpl();
    }

    @Override
    public void createUser(File f1) throws IOException, SQLException {
        File f=f1;
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String s;
        StringBuilder sb = new StringBuilder();
        while((s=br.readLine())!=null){
            sb.append(s).append("\n");
           // System.out.println(s);
        }
        s=sb.toString();
        //System.out.println(s);
        String[] str = s.split("\n");
        userDao.createUser(str);


    }

    @Override
    public void deleteUser(int id) throws SQLException {
        userDao.deleteUser(id);
    }

    @Override
    public void updateUser(int id,String userName, String firstName, String lastName, String address) throws SQLException {
        userDao.updateUser(id,userName,firstName,lastName,address);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();

    }

    @Override
    public User getUserById(int id) throws SQLException {
        User u = userDao.getUserById(id);
        return u;
    }
}
