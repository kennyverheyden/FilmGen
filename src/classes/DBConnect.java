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
	public ArrayList getCategories()
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
}
