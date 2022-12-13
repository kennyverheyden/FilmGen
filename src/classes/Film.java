package classes;

import java.util.ArrayList;

public class Film {

	private String hyperbolic;
	private String story;
	private String subject1;
	private String subject2;
	private String subject3;
	private String verb;
	private String location;
	private String title;

	public Film()
	{
		//Load testdata
		hyperbolic="Fast-Paced";
		story="Drama";
		subject1="Mad Sientist";
		subject2="Database Administrator";
		subject3="Lumberjack";
		verb="Sink";
		location="The Canadian Rockies";
	}

	// Make DB connection
	DBConnect myDBConnection = new DBConnect();

	// Tables of DB loaded separately in ArrayLists
	ArrayList<String> categories = myDBConnection.getCategorie();
	ArrayList<String> hyperbolics = myDBConnection.getHyperbolic();
	ArrayList<String> languages = myDBConnection.getLanguages();
	ArrayList<String> locations = myDBConnection.getLocations();
	ArrayList<String> stories = myDBConnection.getStories();
	ArrayList<String> subjects = myDBConnection.getSubjects();
	ArrayList<String> verbs = myDBConnection.getVerbs();
	ArrayList<String> words = myDBConnection.getVerbs();

	public void buildTitle() 
	{
		String title=  "A " +hyperbolic+ " " +story+ " of a "+subject1+" and a "+subject2+" who must "+verb+" a "+subject3+ " in "+location; 
		System.out.println(title);
	}

}
