package classes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Film {

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
		generatedTitle=  "   A " +hyperbolic+ " " +story+ " of a "+subject1+" and a "+subject2+" who must "+verb+" a "+subject3+ " in "+location; 

		return generatedTitle;
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
	private void printFormattingLine(int length) // take e.g string.length() 
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
		System.out.println("   \nPress enter to continue");
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
		System.out.println("Assign genre number: ");
		// Ask user choice
		Scanner userInput = new Scanner(System.in);
		int userChoiceGenre=userInput.nextInt();
		myDBConnection.insertFilmIndex(userChoiceGenre, indexOfhyperbolic, indexOfStories, indexOfSubject1, indexOfSubject2, indexOfVerbs, indexOfSubject3, indexOfLocation);
	}

	public String getGeneratedTitle() {
		return generatedTitle;
	}

}
