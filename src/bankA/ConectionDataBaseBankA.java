package bankA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDataBaseBankA {
	
	private String dbUrl = "jdbc:mysql://localhost:3306/BANCO_A";
	private String dbUser = "root";
	private String dbPassword = "x1l2r38456";
	private String jdbcName = "com.mysql.cj.jdbc.Driver";

	public Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(jdbcName);
		} catch (Exception e ) {
			System.out.println("¡Error al cargar el controlador!");
		}
		try {
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			System.out.println("Se conecto");
		} catch (SQLException ex) {
			System.out.println("¡Error al conectarse a la base de datos!");
		}
		return conn;
	}	
}
