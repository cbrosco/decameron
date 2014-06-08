package decameron;

import java.util.ArrayList;

public class Story {
	private int id;
	private int number;
	private int giorno;
	private String storyteller;
	private String regina;
	private String info;
	private ArrayList<Location> coords;
	private boolean isMultiple;				//if this represents locations for multiple stories or just one story
	
	public static final int NA = 20;
	public static final int ALL = -1;
	
	/*
	 * Constructor based on user entering information, does not put in DB yet
	 * Still to be done: add locations for story, add story to DB
	 */
	public Story(int storyNumber, int giorno, String storyteller, String info) {
		number= storyNumber;
		this.giorno= giorno;
		this.storyteller= storyteller;
		regina= nameRegina(giorno);
		this.info= info; 
		coords= new ArrayList<Location>();
		isMultiple= false;
	}
	
	/*
	 * Constructor that gets story from DB by unique id
	 */
	public Story(int id){
		this.id= id;
		isMultiple= false;
		coords= new ArrayList<Location>();
		//get from DB dont forget locations (can call add Location)
	}
	
	/*
	 * Constructor that gets story from DB by giorno and teller/number
	 * either can be the option "all"(for int values, -1 means all)
	 * teller or number may be "N/A"
	 */
	public Story(int giorno, String teller, int number){
		this.storyteller= teller;
		this.giorno= giorno;
		this.number= number;
		isMultiple= false;
		String query= "";
		if(teller.equals("all") || teller.equals("N/A")){
			if(number == NA || number == ALL){
				isMultiple= true;
				query= "Select * from Stories where giorno=" + number + ";";
			}	
		}
		if(giorno == -1) {
			if(isMultiple){
				query= "Select * from Stories;";
			}
			isMultiple = true;
			
		}
		coords= new ArrayList<Location>();
		//get from DB dont forget locations (can call add Location)
	}
	
	public boolean isMulipleStoriesCombined(){
		return isMultiple;
	}
	
	public void addLocation(double lat, double lon, String name){
		Location l= new Location(name, lon, lat);
		coords.add(l);
	}
	
	/**
	 * Puts story and locations in DB
	 * @return true if story successfully added, otherwise false
	 */
	public boolean addStoryToDB(){
		//add something to RAHUL db
		
		return false;
	}
	
	public int getNumber(){
		return number;
	}
	
	public int getGiorno(){
		return giorno;
	}
	
	public String getStoryteller(){
		return storyteller;
	}
	
	public String getRegina(){
		return regina;
	}
	
	public String getExtraInfo(){
		return info;
	}
	
	public ArrayList<Location> getLocations(){
		return coords;
	}
	
	public int getNumberLocations(){
		return coords.size();
	}
	
	public void removeLocations(){
		coords= new ArrayList<Location>();
	}
	
	private String nameRegina(int giorno){
		switch(giorno){
			case 1: return "Pampinea";
			case 2: return "Filomena";
			case 3: return "Neifile";
			case 4: return "Filostrato ";
			case 5: return "Fiammetta";
			case 6: return "Elissa";
			case 7: return "Dioneo";
			case 8: return "Lauretta";
			case 9: return "Emilia";
			case 10: return "Panfilo";
		}
		return "";
	}

	public void updateExtraInfo(String updatedVersion) {
		this.info= updatedVersion;
		
	}
}
