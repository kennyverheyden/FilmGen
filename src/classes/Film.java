package classes;

import java.util.ArrayList;
import java.util.Random;

public class Film {

	private String hyperbolic;
	private String story;
	private String subject1;
	private String subject2;
	private String subject3;
	private String verb;
	private String location;
	private String title; // full film title

	// Make DB connection
	DBConnect myDBConnection = new DBConnect();

	// Tables of DB loaded separately in ArrayLists
	private ArrayList<String> categories = myDBConnection.getCategorie();
	private ArrayList<String> hyperbolics = myDBConnection.getHyperbolic();
	private ArrayList<String> languages = myDBConnection.getLanguages();
	private ArrayList<String> locations = myDBConnection.getLocations();
	private ArrayList<String> stories = myDBConnection.getStories();
	private ArrayList<String> subjects = myDBConnection.getSubjects();
	private ArrayList<String> verbs = myDBConnection.getVerbs();
	private ArrayList<String> words = myDBConnection.getVerbs();

	public Film()
	{
	}

	// random picker in the ArrayList
	public int randomPicker(ArrayList obj)
	{
		Random rn = new Random();
		int randomNum = rn.nextInt(obj.size());
		return randomNum;
	}

	public void buildTitle() 
	{
		// assign random content to fields
		hyperbolic=hyperbolics.get(randomPicker(hyperbolics));
		story=stories.get(randomPicker(stories));
		subject1=subjects.get(randomPicker(subjects));
		subject2=subjects.get(randomPicker(subjects));
		subject3=subjects.get(randomPicker(subjects));
		verb=verbs.get(randomPicker(verbs));
		location=locations.get(randomPicker(locations));

		//  build the string with the fields in the template string
		String title=  "   A " +hyperbolic+ " " +story+ " of a "+subject1+" and a "+subject2+" who must "+verb+" a "+subject3+ " in "+location; 

		// show on the screen
		System.out.println("\n   Generated film title:");
		printFormattingLine(title.length());
		System.out.println(title); // print the title
		printFormattingLine(title.length());
		System.out.println();
	}

	public void printFormattingLine(int length) // take e.g string.length() 
	{
		System.out.print("  "); // formatting: add extra space before line
		for(int i=0;i<length-1;i++)
		{
			System.out.print("-");	
		}
		System.out.println(); // new line
	}

}
