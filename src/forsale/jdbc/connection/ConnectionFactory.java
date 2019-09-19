package forsale.jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConnectionFactory {	
	public static String URL = "jdbc:mysql://localhost:3307/teste?useTimezone=true&serverTimezone=UTC";
	public static String USER = "root";
	public static String PASSWORD = "12345";
	
	public static Connection getConnection()
    {
      try {
          DriverManager.registerDriver(new Driver());
          return DriverManager.getConnection(URL, USER, PASSWORD);
      } catch (SQLException e) {
          e.printStackTrace();
    	  throw new RuntimeException("Erro ao conectar com o banco de dados", e);
      }
    }
	
	/*
	 * Teste do DAO
	 */
	public static void main(String[] args) {
		Connection connection = ConnectionFactory.getConnection();
		System.out.println("Conexão estabelecida...");
	}
}
