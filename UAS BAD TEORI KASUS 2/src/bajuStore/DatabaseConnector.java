package bajuStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	//"jdbc:mysql://%s:%s/%s", host, port, database name
	private final String CONNECTION_STRING = String.format("jdbc:mysql://%s:%s/%s", "localhost", "3306", "bajustore1");
	private Connection connection;
	private Statement statement;
	
	private static DatabaseConnector connector = null;
	
	public static DatabaseConnector getConnector() {
		if (connector == null) {
			synchronized (DatabaseConnector.class) {
				if (connector == null) {
					connector = new DatabaseConnector();
				}
			}
		}
		return connector;
	}
	
	private DatabaseConnector() {
		try {
			//Untuk load ke MySQL driver
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			//Get Connection driver
			connection = DriverManager.getConnection(CONNECTION_STRING, "root", "");
			
			//Create statement
			statement = connection.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean execute(String query) {
		//query INSERT, UPDATE, DELETE
		try {
			return statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public ResultSet executeQuery(String query) {
		//query SELECT 
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			
		}
	}

}