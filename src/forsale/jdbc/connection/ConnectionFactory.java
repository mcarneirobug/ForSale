package forsale.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConnectionFactory {	
	public static String URL = "jdbc:mysql://localhost:3306/teste";
	public static String USER = "root";
	public static String PASSWORD = "1234";
	
	public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
          throw new RuntimeException("Erro ao conectar com o banco de dados", e);
      }
    }
}