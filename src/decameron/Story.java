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
	
	private void getLocationsFromDB(){
		String query= "Select * from Locations where storyID=" + this.id + ";";
		
		
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
		
		return true;
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
/**
 *  Searched the db for stories that match the search term based on the criterion.
 *  NB. If the search criterion is location, stories with multiple location matches only
 *  returned once
 * @param searchTerm user entered word. See if any values in db are like this word
 * @param criterion the field to search. can be "giorno", "teller" or "location"
 * @return an ArrayList of the unique ids for all stories matching the criteria
 */
	public static ArrayList<Integer> getMaps(String searchTerm, String criterion) {
		ArrayList<Integer> result= new ArrayList<Integer>();
		searchTerm= searchTerm.trim();
		if(searchTerm.isEmpty()) return result;
		// TODO Auto-generated method stub
		String query= "";
		if(criterion.equals("giorno")){
			query= findStoriesForDay(searchTerm);
		}
		if(criterion.equals("teller")){
			query= "Select storyID from Stories where storyteller=" + searchTerm + ";";
		}
		if(criterion.equals("location")){
			query= "Select storyID from Locations where locationID in (selecct locationID from Points where name like '%" + searchTerm + "%');";
		}
		if (query == null) return result;
		
		//search db with query
		//return story ids that match search
		return result;
	}
	
	
	/**
	 * Create search query to search DB based on day entered by user
	 * @param searchTerm
	 * @return the query for the db, null if search is not valid
	 */
	private static String findStoriesForDay(String searchTerm) {
		int day= -1;
		try{
			day= Integer.parseInt(searchTerm);
			if(day < 1 || day > 10)return null;
		}catch(NumberFormatException e) {
			searchTerm= searchTerm.toLowerCase();
			if(searchTerm.equals("one") ||searchTerm.equals("first")) day= 1;
			if(searchTerm.equals("two") ||searchTerm.equals("second")) day= 2;
			if(searchTerm.equals("three") ||searchTerm.equals("third"))day= 3; 
			if(searchTerm.equals("four") ||searchTerm.equals("fourth")) day= 4; 
			if(searchTerm.equals("five") ||searchTerm.equals("fifth")) day= 5; 
			if(searchTerm.equals("six") ||searchTerm.equals("sixth")) day= 6;
			if(searchTerm.equals("seventh") ||searchTerm.equals("seven")) day= 7; 
			if(searchTerm.equals("eight") ||searchTerm.equals("eigth")) day= 8;
			if(searchTerm.equals("ninth") ||searchTerm.equals("nine")) day= 9;
			if(searchTerm.equals("ten") ||searchTerm.equals("tenth")) day= 10;
		}
		if(day== -1) return null;
			
		return "Select storyID from Stories where girono=" + day + ";"; 

		}

	/**
	 * Find the location in this story that matches the term
	 * Select location that is best match
	 * @param term
	 * @return
	 */
	public String getSimilarLocation(String term){
		int maxNoCommonCharacters= 0;
		term= term.toLowerCase();
		for(int i=0; i< coords.size(); i++){
			String loc= coords.get(i).getName();
			loc= loc.toLowerCase();
			if (loc.equals(term)) return loc;
			if(loc.length() < maxNoCommonCharacters) continue;
			int charInCommon= 0;
			for(int j=0; j< loc.length(); j++){
				if(loc.charAt(j) == term.charAt(charInCommon)){
					charInCommon ++;
				}else{
					charInCommon = 0;
				}
			}
			
		}
		
		return "";
	}

	public int getID() {

		return this.id;
	}
}
