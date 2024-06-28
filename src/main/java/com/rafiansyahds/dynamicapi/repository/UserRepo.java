package com.rafiansyahds.dynamicapi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.rafiansyahds.dynamicapi.connector.DBConnector;
import com.rafiansyahds.dynamicapi.models.User;



public class UserRepo {
    private Connection conn;

    public void createUser(String query, User data) throws SQLException{
        conn = createConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, data.getId());
            stm.setString(2, data.getUserId());
            stm.setString(3, data.getNama());
            stm.setString(4, data.getIsActive());
            stm.setString(5, data.getPasswd());
            Timestamp time = new Timestamp(System.currentTimeMillis());
            stm.setTimestamp(6, time);
            stm.setTimestamp(7, time);
            stm.executeUpdate();
            closeConnection();
    }

    public List<User> getAllUser(String query){
        conn = createConnection();
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("ID");
                String userId = rs.getString("USER_ID");
                String nama = rs.getString("NM");
                String isActive = rs.getString("IS_ACTIVE");
                String passwd = rs.getString("PASSWD");
                Timestamp createdAt = rs.getTimestamp("CREATED_DT");
                Timestamp updatedAt = rs.getTimestamp("UPDATED_DT");
                User user = new User(id, userId, nama, isActive, passwd);
                user.setCreatedAt(createdAt);
                user.setUpdatedAt(updatedAt);
                users.add(user);
            }
        }catch(SQLException e){
            System.out.println(e);
        }closeConnection();
        return users;
    }

    public List<User> getUserById(String query, String id){
        conn = createConnection();
        List<User> users = new ArrayList<>();
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                String userId = rs.getString("USER_ID");
                String ids = rs.getString("ID");
                String nama = rs.getString("NM");
                String isActive = rs.getString("IS_ACTIVE");
                String passwd = rs.getString("PASSWD");
                Timestamp createdAt = rs.getTimestamp("CREATED_DT");
                Timestamp updatedAt = rs.getTimestamp("UPDATED_DT");
                User user = new User(ids, userId, nama, isActive, passwd);
                user.setCreatedAt(createdAt);
                user.setUpdatedAt(updatedAt);
                users.add(user);
            }
        }catch(SQLException e){
            System.out.println(e);
        }closeConnection();
        return users;
    }


    public String authenticate(String query, String id, String passwd){
        conn = createConnection();
        String state = "";
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, id);
            stm.setString(2, passwd);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                if(rs.getString("IS_ACTIVE").equals("Y")){
                    state = "S";
                }else{
                    state = "I";
                }
            }
        } catch (SQLException e) {
            state = "F";
        }
        closeConnection();
        return state;
    }


    public void updateUser(String query, User data) throws SQLException {
        conn = createConnection();
                PreparedStatement stm = conn.prepareStatement(query);
                stm.setString(1, data.getUserId());
                stm.setString(2, data.getNama());
                stm.setString(3, data.getIsActive());
                stm.setString(4, data.getPasswd());
                stm.setTimestamp(5, data.getCreatedAt());
                Timestamp time = new Timestamp(System.currentTimeMillis());
                stm.setTimestamp(6, time);
                stm.setString(7, data.getId());
                stm.executeUpdate();
                closeConnection();
    }

    public void deleteUser(String query, String userId){
        conn = createConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, userId);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        closeConnection();
    }

    private void closeConnection(){
        try{
            conn.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    private Connection createConnection(){
        Connection c = null;
        try {
            c = new DBConnector().getConnection();
        } catch (SQLException e) {
            System.out.println();
        }
        return c;
    }

}
