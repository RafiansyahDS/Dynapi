package com.rafiansyahds.dynamicapi.service;

import java.sql.SQLException;
import java.util.List;

import com.rafiansyahds.dynamicapi.models.User;
import com.rafiansyahds.dynamicapi.repository.UserRepo;
import com.rafiansyahds.dynamicapi.service.interfaces.UserService;

public class UserServiceImpl implements UserService{
    private final UserRepo repo;

    public UserServiceImpl(){
        this.repo = new UserRepo();
    }


    @Override
    public void createUser(User data) throws SQLException{
        String query = "INSERT INTO m_user(ID, USER_ID, NM, IS_ACTIVE, PASSWD, CREATED_DT, UPDATED_DT) VALUES(?, ?, ?, ?, ?, ?, ?)";
        repo.createUser(query, data);
    }   

    @Override
    public List<User> getAllUser() {
       String query = "SELECT * FROM m_user";
       return repo.getAllUser(query);
    }

    @Override
    public void updateUser(User data) throws SQLException {
        String query = "UPDATE m_user SET USER_ID=(?), NM=(?), IS_ACTIVE=(?), PASSWD=(?), CREATED_DT=(?), UPDATED_DT=(?) WHERE ID=(?);";
        repo.updateUser(query, data);
    }

    @Override
    public void deleteUser(String userId) throws SQLException {
        String query = "DELETE FROM m_user WHERE USER_ID=(?)";
        repo.deleteUser(query, userId);
    }

    @Override
    public String getUserByIdPass(String userId, String passwd) {
        String query = "SELECT * FROM m_user WHERE USER_ID=(?) AND PASSWD=(?)";
        return repo.authenticate(query, userId, passwd);
    }

    @Override
    public List<User> getUserById(String id) {
        String query = "SELECT * FROM m_user WHERE ID=(?)";
        return repo.getUserById(query, id);
    }

}
