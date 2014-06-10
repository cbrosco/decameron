package decameron;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	public static final int MULTI_STORY= 34;
	
	
  // CREATING/EDITING STORY OBJECT
	
	
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
	
	public void addLocation(double lat, double lon, String name){
		Location l= new Location(name, lon, lat);
		coords.add(l);
	}
	
	/**
	 * Puts story and locations in DB
	 * @return true if story successfully added, otherwise false
	 */
	public boolean addStoryToDB(){
		String query= "Insert into Stories values(null, " + giorno + ", " + number + ", \"" + storyteller + "\", \"" + regina + "\", \"" + info + "\");";
		Statement st= MyDBAccess.getStatement();
		this.id= -1;
		try{
			st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
		}catch(SQLException sqle){
			return false;
		}
		try {
			ResultSet rs = st.getGeneratedKeys();
			if(rs.next()){
				this.id= rs.getInt(1);
			}else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
		for(int i=0; i < coords.size(); i++){
			Location l= coords.get(i);
			query= "Insert into Points values(null, \"" + l.getName() + "\", "+ l.getLong() + ", " + l.getLat() + ");";
			try{
				st.executeUpdate(query);
				String query2= "Insert into Locations values(" + this.id + ", LAST_INSERT_ID(), " + (i+1) + ");";
				st.executeUpdate(query2);
			}catch(SQLException e){
				return false;
			}
		}	
		return true;	
	}
	
	
	//GETTING STORY FROM DB
	
	
	/*
	 * Constructor that gets story from DB by unique id
	 */
	public Story(int id){
		createSingleStory(id);
	}
	
	private void createSingleStory(int id){
		this.id= id;
		isMultiple= false;
		coords= new ArrayList<Location>();
		String query= "Select * from Stories where storyID=" + id + ";";
		Statement st= MyDBAccess.getStatement();
		try{
			ResultSet rs= st.executeQuery(query);
			if(rs.next()){
				this.giorno= rs.getInt("giorno");
				this.number= rs.getInt("storyNumber");
				this.storyteller= rs.getString("storyteller");
				this.regina= rs.getString("regina");
				this.info= rs.getString("extraInfo");
			}
			
		}catch(SQLException e){
			
		}
	
		getLocationsFromDB(this.id);
	}
	
	/*
	 * Constructor that gets story from DB by giorno and teller/number
	 * either can be the option "all"(for int values, -1 means all)
	 * teller or number may be "N/A"
	 * Sets this.id to error value if story not found
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
				query= "Select storyID from Stories where giorno=" + giorno + ";";
				this.number= ALL; 			//For display, "all" is better than "N/A"
				this.storyteller= "all";	//For display, "all" is better than "N/A"
				if(giorno == ALL) {
					query= "Select * from Stories;";
				}
			}else{
				if(giorno == ALL) {
					isMultiple= true;
					this.storyteller= "all";	//For display, "all" is better than "N/A"
					query= "Select storyID from Stories where storyNumber=" + number + ";";
				}else{
					query= "Select storyID from Stories where giorno=" + giorno + " and storyNumber=" + number + ";";
				}
			}
		} else{
			if(giorno == ALL) {
				isMultiple= true;
				query= "Select storyID from Stories where storyteller=\"" + teller + "\";";
				this.number= ALL;		//Story teller take precedence over number
			}else{
				query= "Select storyID from Stories where giorno=" + giorno + " and storyteller=\"" + teller + "\";";
			}
		}
		if(isMultiple) this.id= MULTI_STORY;
		coords= new ArrayList<Location>();
		Statement st= MyDBAccess.getStatement();
		try{
			ResultSet rs= st.executeQuery(query);
			if(!rs.next()){
				this.id= ErrorTypes.STORY_NOT_YET_CREATED;
				return;
			} else{
				if(isMultiple){
					ArrayList<Integer> storyIDs= new ArrayList<Integer>();
					int idToPass= rs.getInt(1);
					storyIDs.add(idToPass);
					while(rs.next()){
						idToPass= rs.getInt(1);
						storyIDs.add(idToPass);
					}
					for(int i=0; i<storyIDs.size(); i++){
						getLocationsFromDB(storyIDs.get(i));
					}	
				}else{
					int idToPass= rs.getInt(1);
					createSingleStory(idToPass);	
				}
			}
		}catch(SQLException e){
			this.id= ErrorTypes.STORY_NOT_YET_CREATED;
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * Gets locations of story from the DB and add to coords field
	 * 
	 */
	private void getLocationsFromDB(int storyID){
		String query= "Select * from Locations where storyID=" + storyID + " order by indx;";
		Statement st= MyDBAccess.getStatement();
		ArrayList<Integer> locationIds= new ArrayList<Integer>();
		try {
			ResultSet rs= st.executeQuery(query);
			while(rs.next()){
				int locationID= rs.getInt("locationID");
				locationIds.add(locationID);
			}
			for(int i=0; i< locationIds.size(); i++){
				Location l= new Location(locationIds.get(i));
				coords.add(l);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// GETTERS -- FOR OUTSIDERS TO INTERACT WITH SPECIFIC STORY FIELDS
	
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
	
	public boolean isMulipleStoriesCombined(){
		return isMultiple;
	}
	
	public int getID() {
		return this.id;
	}

	
	// STATIC FUNCTIONS FOR SEARCHING DB

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
		String query= "";
		if(criterion.equals("giorno")){
			query= findStoriesForDay(searchTerm);
		}
		if(criterion.equals("teller")){
			query= "Select storyID from Stories where storyteller=\"" + searchTerm + "\" order by giorno;";
		}
		if(criterion.equals("location")){
			query= "Select distinct(storyID) from Locations where locationID in (select locationID from Points where name like '%" + searchTerm + "%');";
		}
		if (query == null) return result;
		Statement st= MyDBAccess.getStatement();
		try{
			ResultSet rs= st.executeQuery(query);
			while(rs.next()){
				result.add(rs.getInt(1));
			}
		}catch (SQLException e){
			return result;
		}
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
			
		return "Select storyID from Stories where giorno=" + day + " order by storyNumber;"; 

		}

	
	//USED FOR SEARCH RESULTS
	
	/**
	 * Find the location in this story that matches the term
	 * Currently just looks for a location name that has term as a substring
	 * @param term the term that the user searched for
	 * @return the location name that contains this term
	 */
	public String getSimilarLocation(String term){
		term= term.toLowerCase();
		int termLen= term.length();
		for(int i=0; i< coords.size(); i++){
			String loc= coords.get(i).getName();
			loc= loc.toLowerCase();
			if (loc.equals(term)) return loc;
			if(loc.length() < termLen) continue;
			for(int j=0; j<= (loc.length()- termLen); j++){
				String temp= loc.substring(j, j+termLen);
				if(temp.equals(term)) return loc;
			}
		}
		return "";
	}

	
}
