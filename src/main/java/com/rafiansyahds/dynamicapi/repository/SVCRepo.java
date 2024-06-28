package com.rafiansyahds.dynamicapi.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.rafiansyahds.dynamicapi.connector.DBConnector;
import com.rafiansyahds.dynamicapi.models.SVCData;
import com.rafiansyahds.dynamicapi.models.SVCRest;

public class SVCRepo {

    private Connection conn;

    public List<SVCRest> getApiPath(String query){
        List<SVCRest> api = new ArrayList<>();
        conn = getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String nama = rs.getString("NM");
                String method = rs.getString("HTTP_METHOD");
                String uri = rs.getString("HTTP_URI");
                String isActive = rs.getString("IS_ACTIVE");
                Timestamp createdAt = rs.getTimestamp("CREATED_DT");
                Timestamp updatedAt = rs.getTimestamp("UPDATED_DT");
                SVCRest data = new SVCRest(id, nama, method, uri, isActive);
                data.setCreatedAt(createdAt);
                data.setUpdateAt(updatedAt);
                api.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        closeConnection();
        return api;
    }

    public List<SVCRest> getApiPath(String query, String path, String httpMethod){
        List<SVCRest> api = new ArrayList<>();
        conn = getConnection();
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, path);
            stm.setString(2, httpMethod);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                String nama = rs.getString("NM");
                String method = rs.getString("HTTP_METHOD");
                String uri = rs.getString("HTTP_URI");
                String isActive = rs.getString("IS_ACTIVE");
                Timestamp createdAt = rs.getTimestamp("CREATED_DT");
                Timestamp updatedAt = rs.getTimestamp("UPDATED_DT");
                SVCRest data = new SVCRest(id, nama, method, uri, isActive);
                data.setCreatedAt(createdAt);
                data.setUpdateAt(updatedAt);
                api.add(data);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        closeConnection();
        return api;
    }
    
    public void createApi(String query, SVCRest data) throws SQLException{
        conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, data.getId());
            stm.setString(2, data.getNama());
            stm.setString(3, data.getHttpMethod());
            stm.setString(4, data.getHttpURI());
            stm.setString(5, data.getIsActive());
            Timestamp time = new Timestamp(System.currentTimeMillis());
            stm.setTimestamp(6, time);
            stm.setTimestamp(7, time);
            stm.executeUpdate();
            closeConnection();
    }

    public void updateApi(String query, SVCRest data) throws SQLException{
        conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(7, data.getId());
            stm.setString(1, data.getNama());
            stm.setString(2, data.getHttpMethod());
            stm.setString(3, data.getHttpURI());
            stm.setString(4, data.getIsActive());
            stm.setTimestamp(5, data.getCreatedAt());
            Timestamp time = new Timestamp(System.currentTimeMillis());
            stm.setTimestamp(6, time);
            stm.executeUpdate();
            closeConnection();
    }

    public void deleteApi(String query, String id) throws SQLException{
        conn = getConnection();
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setString(1, id);
        stm.executeUpdate();
        closeConnection();
    }

    public List<SVCData> getAPIdata(String query){
        conn = getConnection();
        List<SVCData> apiData = new ArrayList<>();
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                String id = rs.getString("ID");
                String data = rs.getString("DATA");
                apiData.add(new SVCData(id, data));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        closeConnection();
        return apiData;
    }

    public void createApiData(String query, SVCData data) throws SQLException{
        conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, data.getId());
            stm.setString(2, data.getData());
            stm.executeUpdate();
            closeConnection();
    }

    public void updateApiData(String query, SVCData data) throws SQLException{
        conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(2, data.getId());
            stm.setString(1, data.getData());
            stm.executeUpdate();
        closeConnection();
    }

    public void deleteApiData(String query, String id) throws  SQLException{
        conn = getConnection();
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, id);
            stm.executeUpdate();
        closeConnection();
    }

    private void closeConnection(){
        try{
            conn.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    

    private Connection getConnection(){
        Connection con = null;
        try {
            con = new DBConnector().getConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }
}
