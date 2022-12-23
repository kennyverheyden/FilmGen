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

	private String generatedDescription;

	public Film()
	{
		generatedDescription=this.generateDescription();
	}

	// random picker in the ArrayList
	private int randomPicker(ArrayList obj)
	{
		Random rn = new Random();
		int randomNum = rn.nextInt(obj.size());
		return randomNum;
	}

	private String generateDescription() 
	{

		String generatedDescription; // Generated description

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
		generatedDescription=  "   A " +hyperbolic.toLowerCase()+ " " +story.toLowerCase()+ " of a "+subject1.toLowerCase()+" and a "+subject2.toLowerCase()+" who must "+verb.toLowerCase()+" a "+subject3.toLowerCase()+ " in "+location.toLowerCase(); 

		return generatedDescription;
	}

	public static void readStoredDescription()
	{
		ArrayList<String> keys = myDBConnection.getDescriptionForeignKeys(); // Contains Primary Key and foreign keys from database
		ArrayList<String> descriptions = new ArrayList<>(); // Here we will store the merged descriptions
		// Merge the descriptions
		for(int i=0;i<keys.size();i++)
		{
			String[] parts = keys.get(i).split(" "); // Retrieve a record and split to array by space
			// Here we merge to one complete description in template
			// We get the keywords from the other ArrayList getters from the fields, which are already connected to the DB
			// The index number is the foreign key number stored in the parts array
			String mergedDescription="   ["+(i+1)+"] Gengre: "+ getCategories().get(Integer.parseInt((parts[1]))-1).toLowerCase()+" - " +"A "+getHyperbolics().get(Integer.parseInt((parts[2]))-1).toLowerCase()+ " " +getStories().get(Integer.parseInt((parts[3]))-1).toLowerCase()+ " of a "+getSubjects().get(Integer.parseInt((parts[4]))-1).toLowerCase()+" and a "+getSubjects().get(Integer.parseInt((parts[5]))-1).toLowerCase()+" who must "+getVerbs().get(Integer.parseInt((parts[6]))-1).toLowerCase()+" a "+getSubjects().get(Integer.parseInt((parts[7]))-1).toLowerCase()+ " in "+getLocations().get(Integer.parseInt((parts[8]))-1).toLowerCase(); 
			descriptions.add(mergedDescription); // Add description to ArrayList
		}

		// Print the descriptions from the ArrayList
		System.out.println("");
		for(int i=0;i<descriptions.size();i++)
		{
			if(i==0)
			{
				printFormattingLine(descriptions.get(i).length()); // Add line to the screen
			}
			System.out.println(descriptions.get(i));
			printFormattingLine(descriptions.get(i).length()); // Add line to the screen

		}
		System.out.println("");

		// Show options to the user
		Scanner userInput = new Scanner(System.in);
		char userChoice=' ';
		System.out.println("    [1] Delete a description");
		System.out.println(" ");
		System.out.println("    Press [q] for back");
		System.out.println("");
		System.out.print("  Choice: ");
		userChoice=userInput.next().toLowerCase().charAt(0);
		switch(userChoice) {
		case '1':
			deleteDescription(keys);
			break;
		case 'q':
			break;
		}
	}

	private static void deleteDescription(ArrayList<String> keys) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("Delete description: ");
		int toDelete=userInput.nextInt(); // Get userChose number
		String[] parts = keys.get((toDelete)-1).split(" "); // SUBTRACT -1 index array // Contains Primary Key on index 0; //
		int databasePrimaryKey = Integer.parseInt(parts[(0)]); // Read Primary Key on index 0;
		boolean success=myDBConnection.deleteDescription(databasePrimaryKey);
		if(success)
		{
			System.out.println("   \nDescription deleted");
		}
		else
		{
			System.out.println("   \nDescription not deleted due database error");
		}
		System.out.println("");
	}

	public void showFormattedDescription()
	{
		System.out.println("\n   Generated film description:");
		printFormattingLine(generatedDescription.length());  // Extend dynamic the line as long as the description
		System.out.println(generatedDescription); // Print the description
		printFormattingLine(generatedDescription.length());
		descriptionOptions(); // What can the user do with the description
	}

	// Dynamic line
	public static void printFormattingLine(int length) // take e.g string.length() 
	{
		System.out.print("  "); // formatting: add extra space before line
		for(int i=0;i<length-1;i++)  // extend dynamic the line as long as the string
		{
			System.out.print("-");	
		}
		System.out.println(); // new line
	}

	// Generated description options, regenerate and store in DB
	private void descriptionOptions()
	{
		System.out.println("");
		System.out.println("   [1] Generate another description");
		System.out.println("   [2] Save the generated description");
		System.out.println("");
		System.out.println("    Press [q] for back");
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

			this.generatedDescription=this.generateDescription();
			this.showFormattedDescription();
			break;
		case '2':
			this.storeGeneratedDescription();
			break;
		case 'q':
			break;
		}
	}

	private void storeGeneratedDescription() {
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
		boolean success=myDBConnection.insertDescriptionIndex(userChoiceGenre, indexOfhyperbolic+1, indexOfStories+1, indexOfSubject1+1, indexOfSubject2+1, indexOfVerbs+1, indexOfSubject3+1, indexOfLocation+1);
		if(success)
		{
			System.out.println("   Description saved\n");
		}
		else
		{
			System.out.println("   Description not saved due problem with database\n");
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
