package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmTitle extends Film{

	private String generatedTitle;

	// Index of elements for DB operations
	private int indexOfSubject;
	private int indexOfVerbs;
	private int indexOfWord;

	public FilmTitle()
	{
		generatedTitle=this.generateTitle();
	}

	private String generateTitle()
	{

		String generatedTitle; // Generated title

		// Assign random content to fields
		String subject=subjects.get(randomPicker(subjects));
		indexOfSubject=subjects.indexOf(subject);
		String verb=verbs.get(randomPicker(verbs));
		indexOfVerbs=verbs.indexOf(verb);
		String word=words.get(randomPicker(words));
		indexOfWord=words.indexOf(word);

		//  Build the string with the fields in the template string
		generatedTitle=  "" +capitalize(subject)+ " " +verb+" "+word+""; 

		return generatedTitle;
	}

	public static void readStoredTitle()
	{
		ArrayList<String> keys = myDBConnection.getTitleForeignKeys(); // Contains Primary Key and foreign keys from database
		ArrayList<String> titles = new ArrayList<>(); // Here we will store the merged titles
		FilmTitle filmTit = new FilmTitle(); // Create obj for calling delete method in parent class
		// Merge the titles
		for(int i=0;i<keys.size();i++)
		{
			String[] parts = keys.get(i).split(" "); // Retrieve a record and split to array by space
			// Here we merge to one complete title in template
			// We get the keywords from the other ArrayList getters from the fields, which are already connected to the DB
			// The index number is the foreign key number stored in the parts array
			String mergedTitle="    ["+(i+1)+"] Gengre: "+ getCategories().get(Integer.parseInt((parts[1]))-1).toLowerCase()+" - "+getSubjects().get(Integer.parseInt((parts[2]))-1).toLowerCase()+ " " +getVerbs().get(Integer.parseInt((parts[3]))-1).toLowerCase()+ " of a "+getWords().get(Integer.parseInt((parts[4]))-1).toLowerCase()+""; 
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
		String userChoice;
		System.out.println("    [1] Delete a title");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");
		userChoice=userInput.nextLine().toLowerCase();
		switch(userChoice) {
		case "1":
			filmTit.deleteItem(keys);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean executeDelete(int databasePrimaryKey)
	{
		boolean success=myDBConnection.deleteTitle(databasePrimaryKey);
		return success;
	}

	public void showFormattedTitle()
	{
		System.out.println("\n    Generated film title:");
		printFormattingLine(generatedTitle.length());  // Extend dynamic the line as long as the title
		System.out.println("    "+generatedTitle); // Print the title
		printFormattingLine(generatedTitle.length());
		descriptionOptions(); // What can the user do with the title
	}

	// Generated title options, regenerate and store in DB
	private void descriptionOptions()
	{
		System.out.println("");
		System.out.println("    [1] Generate another title");
		System.out.println("    [2] Save the generated title");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");

		Scanner userInput = new Scanner(System.in);
		String userChoice = userInput.nextLine().toLowerCase();
	
		switch(userChoice) {
		case "1":
			this.generatedTitle=this.generateTitle();
			this.showFormattedTitle();
			break;
		case "2":
			this.storeGeneratedTitle();
			break;
		default:
			break;
		}
	}

	private void storeGeneratedTitle() {
		int userChoiceGenre=askGengre(); // Ask genre to assign
		// Add +1 to index because array starts from 0; foreign keys in DB starts from 1
		boolean success=myDBConnection.insertTitleIndex(userChoiceGenre, indexOfSubject+1, indexOfVerbs+1, indexOfWord+1);
		if(success)
		{
			System.out.println("\n    Title saved");
		}
		else
		{
			System.out.println("\n    Title not saved due problem with database");
		}
	}
}