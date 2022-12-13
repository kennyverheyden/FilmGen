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
	static String AccDB="jdbc:sqlite:src/resources/FilmGen.sqlite";
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
	//				c = DriverManager.getConnection(AccDB);
	//			} catch ( Exception e ) {
	//				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	//				System.exit(0);
	//			}
	//			System.out.println("Opened database successfully");
	//
	//		}
	//	}


	// getters for DB
	public ArrayList getCategorie()
	{
		ArrayList<String> films = new ArrayList<>();
		sqlQuery="select category from categories";
		try{
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
			c= DriverManager.getConnection(AccDB);//Establishing Connection
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
