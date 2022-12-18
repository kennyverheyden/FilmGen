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
	static String url="jdbc:sqlite:src/resources/FilmGen.sqlite";
	String sqlQuery;

	public DBConnect()
	{

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

	// Insert Film title, foreign ID, indexes
	public void insertFilmIndex(int indexOfcategory, int indexOfhyperbolic, int indexOfStories,  int indexOfSubjects1, int indexOfSubject2, int indexOfVerbs, int indexOfSubject3, int indexOfLocation)
	{
		sqlQuery="INSERT INTO main.films (fk_category_id, fk_hyperbolic_id, fk_story_id, fk_subjects_id, fk_subjects_id_2, fk_verb_id, fk_subjects_id_3, fk_location_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);
			preparedStatement.setInt(1, indexOfcategory);
			preparedStatement.setInt(2, indexOfhyperbolic);
			preparedStatement.setInt(3, indexOfStories);
			preparedStatement.setInt(4, indexOfSubjects1);
			preparedStatement.setInt(5, indexOfSubject2);
			preparedStatement.setInt(6, indexOfVerbs);
			preparedStatement.setInt(7, indexOfSubject3);
			preparedStatement.setInt(8, indexOfLocation);
			int row=preparedStatement.executeUpdate();
			if(row==1)
			{
				System.out.println("Title saved\n");
			}
			else
			{
				System.out.println("Title not saved due problem with database\n");
			}

		}catch(Exception e){
			System.out.println(e);
		}

	}


	// Getters for DB
	public ArrayList getCategorie()
	{
		ArrayList<String> films = new ArrayList<>();
		sqlQuery="select category from categories";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				films.add(resultSet.getString("category")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(films);
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

	public ArrayList getLanguages()
	{
		ArrayList<String> languages = new ArrayList<>();
		sqlQuery="select langCode, language from languages";
		try{
			c= DriverManager.getConnection(url);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				//languages.add(resultSet.getString("language")); 
				//languages.add(resultSet.getString("langCode"));
				String language=resultSet.getString("langCode")+" "+resultSet.getString("language");
				languages.add(language);
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		return(languages);
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
