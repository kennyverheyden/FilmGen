package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnect {

	static Connection c;
	PreparedStatement preparedStatement;
	ResultSet resultSet;
	String sqlQuery;

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
	public ArrayList getCategorie()
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

	public ArrayList getHyperbolic()
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

	public ArrayList getLocations()
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

	public ArrayList getStories()
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

	public ArrayList getSubjects()
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

	public ArrayList getVerbs()
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

	public ArrayList getWords()
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

}