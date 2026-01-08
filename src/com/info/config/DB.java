package com.info.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

 
    private static final String URL = "jdbc:mysql://localhost:3308/testdb2?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "root";       
    private static final String PASSWORD = "root";  

   
    private static volatile Connection cn;

    private DB() {}

    public static Connection getConnexion() {
        if (cn == null) {
            synchronized (DB.class) {
                if (cn == null) {
                    try {
                    
                        Class.forName("com.mysql.jdbc.Driver");
                        cn = DriverManager.getConnection(URL, USER, PASSWORD);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Driver JDBC MySQL introuvable.", e);
                    } catch (SQLException e) {
                        throw new RuntimeException("Connexion MySQL échouée : " + e.getMessage(), e);
                    }
                }
            }
        }
        return cn;
    }
}
