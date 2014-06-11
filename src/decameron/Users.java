package decameron;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Users {
		
	String name;
	String password;
	int userId;
	boolean isAdmin;
	
	
	/**
	 * Get user object from DB by unique id
	 * @param userId
	 */
	public Users(int userId) {
		this.userId= userId;
		String query= "Select * from UsersDec where userID=" + userId + ";";
		Statement st= MyDBAccess.getStatement();
		try{
			ResultSet rs= st.executeQuery(query);
			if(rs.next()){
				this.name= rs.getString("username");
				this.password= rs.getString("password");
				this.isAdmin= rs.getBoolean("isAdmin");
			}
		}catch(SQLException e){
			System.out.println(query);
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * Create new user object and put in DB
	 * @param name username
	 * @param password hashed password
	 * @param isAdmin whether or not the user is an administrator
	 */
	public Users(String name, String password, boolean isAdmin) {
		this.name= name;
		this.password= password; // hash password
		this.isAdmin= isAdmin;
		
		String query= "Insert into UsersDec values (null, \"" + name + "\", \"" + this.password + "\", " + isAdmin + ");"; 
		Statement st= MyDBAccess.getStatement();
		try{
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException e){
			System.out.println(query);
			e.printStackTrace();
			return;
		}
		try {
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()){
				this.userId= rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}

	



	public boolean isAdmin(){
			return isAdmin;
		}

	/**
	 * Check DB to see if user exists. Note--username and password could be empty strings
	 * @param username
	 * @param password
	 * @return userId of user; -1 if user does not exist
	 */
	public static int validateCredentials(String username, String password) {
		if(username.isEmpty() || password.isEmpty()) return -1;
		String hashedPassword= password;
		//hash password
		String query = "Select userID from UsersDec where username= \"" + username + "\" and password =\"" + hashedPassword + "\";"; 
		Statement st= MyDBAccess.getStatement();
		try{
			ResultSet rs= st.executeQuery(query);
			if(rs.next()){
				int userId= rs.getInt(1);
				return userId;
			}else{
				return -1;
			}
		}catch(SQLException e){
			System.out.println(query);
			e.printStackTrace();
			return -1;
		}
	}
		
		
	
}
