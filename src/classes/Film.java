package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Film {

	// Make DB connection
	static DBConnect myDBConnection = new DBConnect();

	// Tables of DB loaded separately in ArrayLists
	static ArrayList<String> categories = myDBConnection.getCategorie();
	static ArrayList<String> hyperbolics = myDBConnection.getHyperbolic();
	static ArrayList<String> locations = myDBConnection.getLocations();
	static ArrayList<String> stories = myDBConnection.getStories();
	static ArrayList<String> subjects = myDBConnection.getSubjects();
	static ArrayList<String> verbs = myDBConnection.getVerbs();
	static ArrayList<String> words = myDBConnection.getVerbs();

	// Index of elements for DB operations
	private int indexOfCategory;
	private int indexOfhyperbolic;
	private int indexOfStories;
	private int indexOfSubject1;
	private int indexOfSubject2;
	private int indexOfSubject3;
	private int indexOfVerbs;
	private int indexOfLocation;

	private String generatedTitle;

	public Film()
	{
		generatedTitle=this.generateTitle();
	}

	// random picker in the ArrayList
	private int randomPicker(ArrayList obj)
	{
		Random rn = new Random();
		int randomNum = rn.nextInt(obj.size());
		return randomNum;
	}

	private String generateTitle() 
	{

		String generatedTitle; // Generated title

		// Assign random content to fields
		String hyperbolic=hyperbolics.get(randomPicker(hyperbolics));
		indexOfhyperbolic=hyperbolics.indexOf(hyperbolic);
		String story=stories.get(randomPicker(stories));
		indexOfStories=stories.indexOf(story);
		String subject1=subjects.get(randomPicker(subjects));
		indexOfSubject1=subjects.indexOf(subject1);
		String subject2=subjects.get(randomPicker(subjects));
		indexOfSubject2=subjects.indexOf(subject2);
		String subject3=subjects.get(randomPicker(subjects));
		indexOfSubject3=subjects.indexOf(subject3);
		String verb=verbs.get(randomPicker(verbs));
		indexOfVerbs=verbs.indexOf(verb);
		String location=locations.get(randomPicker(locations));
		indexOfLocation=locations.indexOf(location);

		//  Build the string with the fields in the template string
		generatedTitle=  "   A " +hyperbolic.toLowerCase()+ " " +story.toLowerCase()+ " of a "+subject1.toLowerCase()+" and a "+subject2.toLowerCase()+" who must "+verb.toLowerCase()+" a "+subject3.toLowerCase()+ " in "+location.toLowerCase(); 

		return generatedTitle;
	}

	public static void readStoredTitles()
	{
		ArrayList<String> keys = myDBConnection.getTitleForeignKeys(); // Contains Primary Key and foreign keys from database
		ArrayList<String> titles = new ArrayList<>(); // Here we will store the merged titles
		// Merge the titles
		for(int i=0;i<keys.size();i++)
		{
			String[] parts = keys.get(i).split(" "); // Retrieve a record and split to array by space
			// Here we merge to one complete title in template
			// We get the keywords from the other ArrayList getters from the fields, which are already connected to the DB
			// The index number is the foreign key number stored in the parts array
			for(int j=0;j<getCategories().size();j++)
			{
				System.out.println(getCategories().get(j));
			}
			String mergedTitle="   ["+(i+1)+"] Gengre: "+ getCategories().get(Integer.parseInt((parts[1]))-1).toLowerCase()+" - " +"A "+getHyperbolics().get(Integer.parseInt((parts[2]))-1).toLowerCase()+ " " +getStories().get(Integer.parseInt((parts[3]))-1).toLowerCase()+ " of a "+getSubjects().get(Integer.parseInt((parts[4]))-1).toLowerCase()+" and a "+getSubjects().get(Integer.parseInt((parts[5]))-1).toLowerCase()+" who must "+getVerbs().get(Integer.parseInt((parts[6]))-1).toLowerCase()+" a "+getSubjects().get(Integer.parseInt((parts[7]))-1).toLowerCase()+ " in "+getLocations().get(Integer.parseInt((parts[8]))-1).toLowerCase(); 
			titles.add(mergedTitle); // Add title to ArrayList
		}

		// Print the titles from the ArrayList
		System.out.println("");
		for(int i=0;i<titles.size();i++)
		{
			if(i==0)
			{
				printFormattingLine(titles.get(i).length()); // Add line to the screen
			}
			System.out.println(titles.get(i));
			printFormattingLine(titles.get(i).length()); // Add line to the screen

		}
		System.out.println("");

		// Show options to the user
		Scanner userInput = new Scanner(System.in);
		char userChoice=' ';
		System.out.println("    [1] Delete a title");
		System.out.println(" ");
		System.out.println("    Press [q] for back");
		System.out.println("");
		System.out.print("  Choice: ");
		userChoice=userInput.next().toLowerCase().charAt(0);
		switch(userChoice) {
		case '1':
			deleteTitle(keys);
			break;
		case 'q':
			break;
		}
	}

	private static void deleteTitle(ArrayList<String> keys) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Delete film: ");
		int toDelete=userInput.nextInt(); // Get userChose number
		String[] parts = keys.get((toDelete)-1).split(" "); // SUBTRACT -1 index array // Contains Primary Key on index 0; //
		int databasePrimaryKey = Integer.parseInt(parts[(0)]); // Read Primary Key on index 0;
		boolean success=myDBConnection.deleteTitle(databasePrimaryKey);
		if(success)
		{
			System.out.println("   \nTitle deleted");
		}
		else
		{
			System.out.println("   \nTitle not deleted due database error");
		}
		System.out.println("");
	}

	public void showFormattedTitle()
	{
		System.out.println("\n   Generated film title:");
		printFormattingLine(generatedTitle.length());  // Extend dynamic the line as long as the title
		System.out.println(generatedTitle); // Print the title
		printFormattingLine(generatedTitle.length());
		titleOptions(); // What can the user do with the title
	}

	// Dynamic line
	public static void printFormattingLine(int length) // take e.g string.length() 
	{
		System.out.print("  "); // formatting: add extra space before line
		for(int i=0;i<length-1;i++)  // extend dynamic the line as long as the title
		{
			System.out.print("-");	
		}
		System.out.println(); // new line
	}

	// Generated title options, regenerate and store in DB
	private void titleOptions()
	{
		System.out.println("");
		System.out.println("   [1] Generate another title");
		System.out.println("   [2] Save the generated title");
		System.out.println("");
		System.out.print("   Choice: ");
		System.out.println("");

		Scanner userInput = new Scanner(System.in);
		char userChoice=' ';
		try
		{
			userChoice=userInput.nextLine().toLowerCase().charAt(0);
		}
		catch(Exception e) {};
		switch(userChoice) {
		case '1':

			this.generatedTitle=this.generateTitle();
			this.showFormattedTitle();
			break;
		case '2':
			this.storeGeneratedFilm();
			break;
		default:
			break;
		}
	}

	private void storeGeneratedFilm() {
		System.out.println("   Assign one of the following genres.");
		System.out.println("");  // new line
		System.out.print("   "); // add some spaces before line
		for(int i=0;i<categories.size();i++)
		{
			System.out.print((i+1)+" "+categories.get(i)+" ");
			if(i==10) {System.out.print("\n   ");}; // add new line for readability
		}
		System.out.println("\n");
		System.out.print("   Assign genre number: ");
		// Ask user choice
		Scanner userInput = new Scanner(System.in);
		int userChoiceGenre=userInput.nextInt();

		// Add +1 to index because array starts from 0; forein keys in DB starts from 1
		boolean success=myDBConnection.insertFilmIndex(userChoiceGenre, indexOfhyperbolic+1, indexOfStories+1, indexOfSubject1+1, indexOfSubject2+1, indexOfVerbs+1, indexOfSubject3+1, indexOfLocation+1);
		if(success)
		{
			System.out.println("   Title saved\n");
		}
		else
		{
			System.out.println("   Title not saved due problem with database\n");
		}
	}

	public static ArrayList<String> getCategories() {
		return categories;
	}

	public static ArrayList<String> getHyperbolics() {
		return hyperbolics;
	}

	public static ArrayList<String> getLocations() {
		return locations;
	}

	public static ArrayList<String> getStories() {
		return stories;
	}

	public static ArrayList<String> getSubjects() {
		return subjects;
	}

	public static ArrayList<String> getVerbs() {
		return verbs;
	}

	public static ArrayList<String> getWords() {
		return words;
	}

}
