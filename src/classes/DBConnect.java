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


	// Database test
	public static void openDB() {

		{

			try {
				Class.forName("org.sqlite.JDBC");
				c = DriverManager.getConnection(AccDB);
			} catch ( Exception e ) {
				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
				System.exit(0);
			}
			System.out.println("Opened database successfully");

		}
	}


	// Data ophalen en in ArrayList
	public void getFilmTitles()
	{
		ArrayList<String> films = new ArrayList<>();
		sqlQuery="select cat.category, hyp.hyperbolic, stor.stories, sub.subjects, sub2.subjects, ver.verbs, sub3.subjects, loc.locations"
				+ "from films fil join categories cat on cat.category_id=fil.fk_category_id"
				+ "join hyperbolic hyp on hyp.hyperbolic_id=fil.fk_hyperbolic_id"
				+ "join stories stor on stor.story_id=fil.fk_story_id"
				+ "join subjects sub on sub.subject_id = fil.fk_subjects_id"
				+ "join subjects sub2 on sub2.subject_id = fil.fk_subjects_id_2"
				+ "join verbs ver on ver.verb_id = fil.fk_verb_id"
				+ "join subjects sub3 on sub3.subject_id = fil.fk_subjects_id_3"
				+ "join locations loc on loc.loc_id = fil.fk_location_id";
		try{
			c= DriverManager.getConnection(AccDB);//Establishing Connection
			PreparedStatement preparedStatement=c.prepareStatement(sqlQuery);

			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()){
				
			    System.out.println("Category " + resultSet.getString("cat.category"));
			    System.out.println("Hyperbolic "+resultSet.getString("hyp.hyperbolic"));
			    System.out.println("Storie "+ resultSet.getString("stor.stories"));
			    System.out.println("Subject "+resultSet.getString("sub.subjects"));
			    System.out.println("Subject2 "+resultSet.getString("sub2.subjects"));
			    System.out.println("Verb "+resultSet.getString("ver.verbs"));
			    System.out.println("Subject3 "+resultSet.getString("sub3.subjects"));
			    System.out.println("Location "+resultSet.getString("loc.locations"));
				//films.add(resultSet.getString("")); 
			}
		}catch(Exception e){
			System.out.println("Error in connection");
		}
		//return(films);
	}
}
