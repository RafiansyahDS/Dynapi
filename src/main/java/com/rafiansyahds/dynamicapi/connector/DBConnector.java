package com.rafiansyahds.dynamicapi.connector;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {

    private String db_url;
    private String userdb;
    private String passdb;

    public DBConnector(){
        loadDatabaseProperties();
    }

    private void loadDatabaseProperties() {
        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            prop.load(input);
            db_url = prop.getProperty("db_url");
            userdb = prop.getProperty("userdb");
            passdb = prop.getProperty("passdb");
        } catch (IOException ex) {
            throw new RuntimeException("Error loading database configuration", ex);
        }
    }

    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(db_url,userdb,passdb);
    }

    
}
