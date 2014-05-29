package decameron;


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
		//get Location from DB
		//latit=
		//longd=
		//name
		this.id= id;
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
