package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DBConnect {

	static Connection c;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	String sqlQuery;

	// Initialize Scanner for this class
	Scanner dbInput = new Scanner(System.in);

	// Relative path to database file
	static String url="jdbc:sqlite:src/resources/FilmGen.sqlite";

	// Constructor
	public DBConnect(){
	}

	// Only for connectivity testing	
	//	public static void openDB() {
	//
	//		{
	//			try {
	//				Class.forName("org.sqlite.JDBC");
	//				c = DriverManager.getConnection(url);
	//			} catch ( Exception e ) {
	//				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	//				System.exit(0);
	//			}
	//			System.out.println("Opened database successfully");
	//
	//		}
	//	}

	// View or edit stored data in database
	public void databaseView() throws SQLException
	{
		String userChoice;
		System.out.println("");
		System.out.println("    Choose a table:");
		System.out.println("    [1] Words");
		System.out.println("    [2] Verbs");
		System.out.println("    [3] Subjects");
		System.out.println("    [4] Stories");
		System.out.println("    [5] Locations");
		System.out.println("    [6] Hyperbolics");
		System.out.println("    [7] Genres");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");
		userChoice=dbInput.nextLine().toString();
		switch(userChoice) {
		case "1":
			readTable(getWords());
			showEditOptions("words","word");  				// Parameters tableName, columnName
			break;
		case "2":
			readTable(getVerbs());
			showEditOptions("verbs","verbs"); 				// Parameters tableName, columnName
			break;
		case "3":
			readTable(getSubjects());
			showEditOptions("subjects","subjects"); 		// Parameters tableName, columnName
			break;
		case "4":
			readTable(getStories());
			showEditOptions("stories","stories"); 			// Parameters tableName, columnName
			break;
		case "5":
			readTable(getLocations());
			showEditOptions("locations","locations"); 		// Parameters tableName, columnName
			break;
		case "6":
			readTable(getHyperbolics());
			showEditOptions("hyperbolic","hyperbolic"); 	// Parameters tableName, columnName
			break;
		case "7":
			readTable(getCategories());
			showEditOptions("categories","category"); 		// Parameters tableName, columnName
			break;
		default :
			break;
		}
		System.out.println("");
	}

	// Show edit options to the user
	private void showEditOptions(String tableName, String columnName)
	{
		String userChoice;
		System.out.println("    [1] Insert data");
		System.out.println("    [2] Delete data");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");
		userChoice=dbInput.nextLine().toString();
		switch(userChoice)
		{
		case "1":
			// Ask value to insert in db
			askInsertContent(tableName,columnName);		// Parameters tableName columnName
			break;
		case "2":
			// Ask value to delete in db
			askDeleteContent(tableName,columnName);		// Parameters tableName columnName
			break;
		default :
			break;
		}
	}

	// Check if userInput is integer
	private boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	// If the user select a record by number id - Search value by id
	private String getIDbyString(String tableName, int id)
	{
		String str=null; // Store found value to return
		switch(tableName)
		{
		case "words":
			str=searchStringById(getWords(),id);
			break;
		case "verbs":
			str=searchStringById(getVerbs(),id);
			break;
		case "subjects":
			str=searchStringById(getSubjects(),id);
			break;
		case "stories":
			str=searchStringById(getStories(),id);
			break;
		case "locations":
			str=searchStringById(getLocations(),id);
			break;
		case "hyperbolic":
			str=searchStringById(getHyperbolics(),id);
			break;
		case "categories":
			str=searchStringById(getCategories(),id);
			break;
		default :
			break;
		}
		return str;
	}

	//	Called by getIDbyString() method - Search value by id number in ArrayList
	private String searchStringById(ArrayList<String> records, int id)
	{
		String str=null;
		if(id<(records.size()+1) && id!=0)
		{
			str=records.get(id-1); 	// id -1 because array starts from 0
		}
		else
		{
			System.out.println("\n    Invalid id number or number out of range");
		}
		return str;
	}

	// Scan an entry from user to delete a record
	private void askDeleteContent(String tableName, String columnName)
	{
		System.out.print("    Type value or number to delete: ");
		String str = dbInput.nextLine().toString();
		String value=null; // Store value to delete
		if(!str.equals(""))
		{
			// Check if user input is String or number (Id)
			if(isNumeric(str))
			{
				int id = Integer.parseInt(str);
				value=getIDbyString(tableName,id);
			}
			else
			{
				value=str;
			}
			// Delete record
			boolean succes=deleteQuery(tableName,columnName,value);
			if(succes)
			{
				System.out.println("\n    Successfully deleted");
				Film.pressKeyToContinue();
			}
			else
			{
				System.out.println("    Value not deleted\n");
				Film.pressKeyToContinue();
			}
		}
	}

	// Delete a record determined by user
	private boolean deleteQuery(String tableName, String columnName, String str)
	{
		sqlQuery="DELETE FROM main."+tableName+" WHERE "+columnName+" = ?";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setString(1, str);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true; 	// Delete success
			}
			else
			{
				return false; 	// Delete unsuccessful
			}

		}catch(Exception e){
			System.out.println(e); // In case of error
		}
		return false;
	}

	// Scan an entry from user to insert a record to a database table
	private void askInsertContent(String tableName, String columnName)
	{
		System.out.print("    Insert new value: ");
		String str = dbInput.nextLine().toString();
		if(!str.equals(""))
		{
			String sqlQuery = "INSERT INTO main."+tableName+" ("+columnName+") VALUES ('"+str+"')";
			boolean succes=insertQuery(sqlQuery);
			if(succes)
			{
				System.out.println("\n    Successfully added");
			}
			else
			{
				System.out.println("\n    Error adding");
			}
		}
		Film.pressKeyToContinue();
	}

	// Insert content to tables
	private boolean insertQuery(String str)
	{
		sqlQuery=str;
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Read records of a table (ArrayList getters)
	private void readTable(ArrayList<String> table)
	{
		System.out.print("\n    | "); // Formatting
		for(int i=0;i<table.size();i++)
		{
			if(i%10==0 && i !=0) 			// Formatting: new line after 10 values
			{
				System.out.println("");		// Break line
				System.out.print("    | "); // Add spaces
			}
			System.out.print((i+1)+" "+table.get(i)+" | "); // Print values
		}
		System.out.println("\n");
	}

	// Database statistics
	public void databaseStats()
	{
		System.out.println("\n    Database statistics");
		System.out.println("    --------------------\n");
		System.out.println("    Amount of data stored in database:");
		System.out.printf("\n    %8d words", getWords().size());
		System.out.printf("\n    %8d verbs", getVerbs().size());
		System.out.printf("\n    %8d subjects", getSubjects().size());
		System.out.printf("\n    %8d stories", getStories().size());
		System.out.printf("\n    %8d locations", getLocations().size());
		System.out.printf("\n    %8d hyperbolics", getHyperbolics().size());
		System.out.printf("\n    %8d genres", getCategories().size());
		System.out.printf("\n    %8d generated films (title + descripton)", getFilmForeignKeys().size());
		System.out.printf("\n    %8d generated titles", getTitleForeignKeys().size());
		System.out.printf("\n    %8d generated subjecs", getDescriptionForeignKeys().size());
		System.out.println("\n");
		Film.pressKeyToContinue();
	}

	// Delete a generated film
	public boolean deleteFilm(int PK)
	{
		sqlQuery="DELETE FROM main.films WHERE film_id = ?";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, PK);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Insert Film title, foreign ID, indexes
	public boolean insertFilmIndex(int indexOfCategory, int indexOfWord1, int indexOfWord2, int indexOfhyperbolic, int indexOfStories,  int indexOfSubjects1, int indexOfSubjects2, int indexOfVerbs, int indexOfSubject3, int indexOfLocation)
	{
		sqlQuery="INSERT INTO main.films (fk_category_id, fk_word_title_id, fk_word_title_id_2, fk_hyperbolic_descrip_id, fk_story_descrip_id, fk_subjects_descrip_id, fk_subjects_descrip_id_2, fk_verb_descrip_id, fk_subjects_descrip_id_3, fk_location_descrip_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, indexOfCategory);
			preparedStatement.setInt(2, indexOfWord1);
			preparedStatement.setInt(3, indexOfWord2);
			preparedStatement.setInt(4, indexOfhyperbolic);
			preparedStatement.setInt(5, indexOfStories);
			preparedStatement.setInt(6, indexOfSubjects1);
			preparedStatement.setInt(7, indexOfSubjects2);
			preparedStatement.setInt(8, indexOfVerbs);
			preparedStatement.setInt(9, indexOfSubject3);
			preparedStatement.setInt(10, indexOfLocation);

			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Get Film FK, foreign ID, index
	public ArrayList getFilmForeignKeys()
	{
		ArrayList<String> TitleForeignKeys = new ArrayList<>();
		sqlQuery="select film_id, fk_category_id, fk_word_title_id, fk_word_title_id_2, fk_hyperbolic_descrip_id, fk_story_descrip_id, fk_subjects_descrip_id, fk_subjects_descrip_id_2, fk_verb_descrip_id, fk_subjects_descrip_id_3, fk_location_descrip_id from main.films";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				TitleForeignKeys.add(resultSet.getInt("film_id")+" "+resultSet.getInt("fk_category_id") +" "+resultSet.getInt("fk_word_title_id") +" "+ resultSet.getInt("fk_word_title_id_2") +" " + resultSet.getInt("fk_hyperbolic_descrip_id") +" "+ resultSet.getInt("fk_story_descrip_id") +" "+ resultSet.getInt("fk_subjects_descrip_id") +" "+ resultSet.getInt("fk_subjects_descrip_id_2") +" "+ resultSet.getInt("fk_verb_descrip_id") + " " + resultSet.getInt("fk_subjects_descrip_id_3") +" "+ resultSet.getInt("fk_location_descrip_id") +" ");
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(TitleForeignKeys);
	}

	// Delete a generated film title
	public boolean deleteTitle(int PK)
	{
		sqlQuery="DELETE FROM main.titles WHERE title_id = ?";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, PK);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Insert Film title, foreign ID, indexes
	public boolean insertTitleIndex(int indexOfCategory, int indexOfWord1, int indexOfWord2)
	{
		sqlQuery="INSERT INTO main.titles (fk_category_id, fk_word_id, fk_word_id_2) VALUES (?, ?, ?)";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, indexOfCategory);
			preparedStatement.setInt(2, indexOfWord1);
			preparedStatement.setInt(3, indexOfWord2);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Get Titles FK, foreign ID, index
	public ArrayList getTitleForeignKeys()
	{
		ArrayList<String> TitleForeignKeys = new ArrayList<>();
		sqlQuery="select title_id, fk_category_id, fk_word_id, fk_word_id_2 from main.titles";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				TitleForeignKeys.add(resultSet.getInt("title_id")+" "+resultSet.getInt("fk_category_id") +" "+resultSet.getInt("fk_word_id") +" "+ resultSet.getInt("fk_word_id_2") +" ");
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(TitleForeignKeys);
	}

	// Get Descriptions FK, foreign ID, index
	public ArrayList getDescriptionForeignKeys()
	{
		ArrayList<String> DescriptionForeignKeys = new ArrayList<>();
		sqlQuery="select description_id, fk_category_id, fk_hyperbolic_id, fk_story_id, fk_subjects_id, fk_subjects_id_2, fk_verb_id, fk_subjects_id_3, fk_location_id from main.descriptions";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				DescriptionForeignKeys.add(resultSet.getInt("description_id")+" "+resultSet.getInt("fk_category_id") +" "+ resultSet.getInt("fk_hyperbolic_id") +" "+ resultSet.getInt("fk_story_id") +" "+ resultSet.getInt("fk_subjects_id") +" "+ resultSet.getInt("fk_subjects_id_2") +" "+ resultSet.getInt("fk_verb_id")
				+" "+ resultSet.getInt("fk_subjects_id_3") +" "+ resultSet.getInt("fk_location_id"));
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(DescriptionForeignKeys);
	}

	// Delete a generated film description
	public boolean deleteDescription(int PK)
	{
		sqlQuery="DELETE FROM main.descriptions WHERE description_id = ?";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, PK);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Insert Film description, foreign ID, indexes
	public boolean insertDescriptionIndex(int indexOfcategory, int indexOfhyperbolic, int indexOfStories,  int indexOfSubjects1, int indexOfSubjects2, int indexOfVerbs, int indexOfSubject3, int indexOfLocation)
	{
		sqlQuery="INSERT INTO main.descriptions (fk_category_id, fk_hyperbolic_id, fk_story_id, fk_subjects_id, fk_subjects_id_2, fk_verb_id, fk_subjects_id_3, fk_location_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, indexOfcategory);
			preparedStatement.setInt(2, indexOfhyperbolic);
			preparedStatement.setInt(3, indexOfStories);
			preparedStatement.setInt(4, indexOfSubjects1);
			preparedStatement.setInt(5, indexOfSubjects2);
			preparedStatement.setInt(6, indexOfVerbs);
			preparedStatement.setInt(7, indexOfSubject3);
			preparedStatement.setInt(8, indexOfLocation);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				return true;
			}
			else
			{
				return false;
			}

		}catch(Exception e){
			System.out.println(e);
		}
		return false;
	}

	// Getters for DB
	public ArrayList<String> getCategories()
	{
		ArrayList<String> categories = new ArrayList<>();
		sqlQuery="select category from categories";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				categories.add(resultSet.getString("category")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(categories);
	}

	public ArrayList<String> getHyperbolics()
	{
		ArrayList<String> hyperbolic = new ArrayList<>();
		sqlQuery="select hyperbolic from hyperbolic";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				hyperbolic.add(resultSet.getString("hyperbolic")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(hyperbolic);
	}

	public ArrayList<String> getLocations()
	{
		ArrayList<String> locations = new ArrayList<>();
		sqlQuery="select locations from locations";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				locations.add(resultSet.getString("locations")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(locations);
	}

	public ArrayList<String> getStories()
	{
		ArrayList<String> stories = new ArrayList<>();
		sqlQuery="select stories from stories";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				stories.add(resultSet.getString("stories")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(stories);
	}

	public ArrayList<String> getSubjects()
	{
		ArrayList<String> subjects = new ArrayList<>();
		sqlQuery="select subjects from subjects";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				subjects.add(resultSet.getString("subjects")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(subjects);
	}

	public ArrayList<String> getVerbs()
	{
		ArrayList<String> verbs = new ArrayList<>();
		sqlQuery="select verbs from verbs";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				verbs.add(resultSet.getString("verbs")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(verbs);
	}

	public ArrayList<String> getWords()
	{
		ArrayList<String> words = new ArrayList<>();
		sqlQuery="select word from words";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				words.add(resultSet.getString("word")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(words);
	}

	// Close the scanner
	public void closeScanner()
	{
		dbInput.close();
	}

}