package br.com.forsale.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    
    public Connection getConnection() {
        
        try {
            
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bdvendas?useTimezone=true&serverTimezone=UTC", "usuario", "123");
            //useSSL=true&useTimezone=true&serverTimezone=UTC
            
        } catch(Exception e) {
            throw new RuntimeException(e);
        }   
    }
}