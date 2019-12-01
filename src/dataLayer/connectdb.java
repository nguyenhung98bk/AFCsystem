package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectdb {
	public static Connection getPostgresqlConnection() throws SQLException,ClassNotFoundException {
		String url = "jdbc:postgresql://localhost/AFCsystem";
	    String user = "postgres";
	    String password = "nguyenhung6256";
	    Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
}
