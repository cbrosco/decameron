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
	
	
	/*
	 * Constructor based on user entering information, does not put in DB yet
	 * Still to be done: add locations for story
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
	 * Constructor that gets story from DB
	 */
	public Story(int id){
		this.id= id;
		isMultiple= false;
		//get from DB dont forget locations (can call add Location)
	}
	
	/*
	 * Constructor that gets story from DB by giorno and teller
	 * either can be the option "all"(for giorno, -1 means all)
	 */
	public Story(int giorno, String teller){
		this.storyteller= teller;
		this.giorno= giorno;
		isMultiple= false;
		if(giorno == -1 || teller.equals("all")) isMultiple= true;
		//get from DB dont forget locations (can call add Location)
	}
	
	public boolean isMulipleStoriesCombined(){
		return isMultiple;
	}
	
	public void addLocation(double lat, double lon, String name){
		Location l= new Location(name, lon, lat);
		coords.add(l);
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
}
