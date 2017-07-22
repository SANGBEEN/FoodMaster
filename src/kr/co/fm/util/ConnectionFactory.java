package kr.co.fm.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection() {
		
		Connection conn = null;
		
		try {
		
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url      = "jdbc:oracle:thin:@localhost:1521:xe";
			String user     = "FM";
			String password = "FM";
			
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conneted");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
