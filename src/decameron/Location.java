package decameron;

import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.SQLException;
import java.sql.Statement;



public class Location {
	@Override
	public String toString() {
		return "Location [latit=" + latit + ", longd=" + longd + ", name="
				+ name + "]";
	}

	private int id;
	private double latit;
	private double longd;
	private String name;
	
	public Location(String name, double longd, double latit) {
		this.latit= latit;
		this.longd= longd;
		this.name= name;
	}
	
	public Location(int id){
		this.id= id;
		String query2= "Select * from Points where locationID=" + id + ";";
		Statement st= MyDBAccess.getStatement();
		try{
		ResultSet rs= st.executeQuery(query2);
		if(rs.next()){
			this.name= rs.getString("name");
			this.latit= rs.getDouble("latit");
			this.longd= rs.getDouble("longd");
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public double getLat(){
		return latit;
	}
	
	public double getLong(){
		return longd;
	}
	
	public String getName(){
		return name;
	}
	
	public int putInDB(){
		//put in DB
		//id= id assigned in DB
		//return id;
		return 0;
	}
}
