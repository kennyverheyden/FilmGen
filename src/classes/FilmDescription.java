package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmDescription extends Film{

	private String generatedDescription;

	// Index of elements for DB operations
	private int indexOfCategory;
	private int indexOfhyperbolic;
	private int indexOfStories;
	private int indexOfSubject1;
	private int indexOfSubject2;
	private int indexOfSubject3;
	private int indexOfVerbs;
	private int indexOfLocation;

	public FilmDescription()
	{
		generatedDescription=this.generateDescription();
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
		generatedDescription=  capitalize(aOrAn(hyperbolic)) + " " + story + " of "+ subject1 + " and " + subject2 + " who must " + verb + " " + subject3 + " in "+ location; 

		return generatedDescription;
	}

	public static void readStoredDescription()
	{
		ArrayList<String> keys = myDBConnection.getDescriptionForeignKeys(); // Contains Primary Key and foreign keys from database
		ArrayList<String> descriptions = new ArrayList<>(); // Here we will store the merged descriptions
		FilmDescription filmDes = new FilmDescription(); //Create obj for calling delete method in parent class
		// Merge the descriptions
		for(int i=0;i<keys.size();i++)
		{
			String[] parts = keys.get(i).split(" "); // Retrieve a record and split to array by space
			// Here we merge to one complete description in template
			// We get the keywords from the other ArrayList getters from the fields, which are already connected to the DB
			// The index number is the foreign key number stored in the parts array
			String mergedDescription="    ["+(i+1)+"] Gengre: "+ getCategories().get(Integer.parseInt((parts[1]))-1).toLowerCase()+" - "+capitalize(getHyperbolics().get(Integer.parseInt((parts[2]))-1))+ " " +getStories().get(Integer.parseInt((parts[3]))-1)+ " of "+getSubjects().get(Integer.parseInt((parts[4]))-1)+" and "+getSubjects().get(Integer.parseInt((parts[5]))-1)+" who must "+getVerbs().get(Integer.parseInt((parts[6]))-1)+" "+getSubjects().get(Integer.parseInt((parts[7]))-1)+ " in "+getLocations().get(Integer.parseInt((parts[8]))-1); 
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
		String userChoice;
		System.out.println("    [1] Delete a description");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");
		userChoice=userInput.nextLine().toLowerCase();
		switch(userChoice) {
		case "1":
			filmDes.deleteItem(keys);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean executeDelete(int databasePrimaryKey)
	{
		boolean success=myDBConnection.deleteDescription(databasePrimaryKey);
		return success;
	}

	public void showFormattedDescription()
	{
		System.out.println("\n    Generated film description:");
		printFormattingLine(generatedDescription.length());  // Extend dynamic the line as long as the description
		System.out.println("    "+generatedDescription); // Print the description
		printFormattingLine(generatedDescription.length());
		descriptionOptions(); // What can the user do with the description
	}

	// Generated description options, regenerate and store in DB
	private void descriptionOptions()
	{
		System.out.println("");
		System.out.println("    [1] Generate another description");
		System.out.println("    [2] Save the generated description");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("   Choice: ");

		Scanner userInput = new Scanner(System.in);
		String userChoice= userInput.nextLine().toLowerCase();
		switch(userChoice) {
		case "1":
			this.generatedDescription=this.generateDescription();
			this.showFormattedDescription();
			break;
		case "2":
			this.storeGeneratedDescription();
			break;
		default:
			break;
		}
	}

	private void storeGeneratedDescription() {
		int userChoiceGenre=askGengre(); // Ask genre to assign
		// Add +1 to index because array starts from 0; foreign keys in DB starts from 1
		boolean success=myDBConnection.insertDescriptionIndex(userChoiceGenre, indexOfhyperbolic+1, indexOfStories+1, indexOfSubject1+1, indexOfSubject2+1, indexOfVerbs+1, indexOfSubject3+1, indexOfLocation+1);
		if(success)
		{
			System.out.println("\n    Description saved");
		}
		else
		{
			System.out.println("\n    Description not saved due problem with database");
		}
	}
}