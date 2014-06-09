package decameron;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDBAccess {
	
	private static final String account =  MyDBInfo.MYSQL_USERNAME;
	private static final String password = MyDBInfo.MYSQL_PASSWORD;
	private static final String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	private static final String database = MyDBInfo.MYSQL_DATABASE_NAME;
	
	private static Connection connection;
	private static Statement statement = null;

	public static void main(String[] args){
		Statement stat = MyDBAccess.getStatement();
		try {
            stat.executeQuery("select * from UsersDec;");
		}
		catch (SQLException sqle) {
			System.out.println("SQL error 1 -->" + sqle.getMessage());
		}
		
		try {
            stat.executeQuery("select * from usersNotExist;");
		}
		catch (SQLException sqle) {
			System.out.println("SQL error 2 -->" + sqle.getMessage());
		}
	}
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection
				( "jdbc:mysql://" + server, account ,password);
			Statement stmt = connection.createStatement();
			 System.out.println("Connected to " + server + " with " + account);
			stmt.executeQuery("USE " + database);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	  
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static Statement getStatement() {
		if (statement == null) {
			try {
			    statement = connection.createStatement();
			}
			catch (SQLException sqle) {
				System.out.println("Error connecting to the database. Details:\n" + sqle.getMessage());
				System.exit(-1);
			}
		}
		return statement;
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

