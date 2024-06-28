package com.rafiansyahds.dynamicapi.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.rafiansyahds.dynamicapi.models.User;

public interface UserService {
    public void createUser(User data) throws SQLException;
    public List<User> getAllUser();
    public List<User> getUserById(String id);
    public void updateUser(User data) throws SQLException;
    public void deleteUser(String userId) throws SQLException;
    public String getUserByIdPass(String userId, String passwd);
}
