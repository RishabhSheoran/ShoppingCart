package com.shopping.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.shopping.domain.User;

/**
 * Created by rishabhsheoran on 2/2/17.
 */
public interface UserService {
    public void createUser(File f) throws IOException, SQLException;
    public void deleteUser(int id) throws SQLException;
    public void updateUser(int id, String userName, String firstName, String lastName, String address) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public User getUserById(int id) throws SQLException;
}
