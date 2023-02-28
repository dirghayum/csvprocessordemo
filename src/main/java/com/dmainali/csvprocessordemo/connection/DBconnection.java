package com.dmainali.csvprocessordemo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static Connection con = null;

    static {
        String url = "jdbc:postgresql://localhost:5432/db_name?currentSchema=schema_name";
        String username = "username";
        String password = "password";
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url,username,password);
        }catch (SQLException | ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return con;
    }
}
