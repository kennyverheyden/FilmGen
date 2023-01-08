package classes;

import java.util.ArrayList;
import java.util.Arrays;

public class FilmDescription extends Film{

	private String generatedDescription; 	// Store generated description
	private String objName; 				// Used for file name, ..

	// Fields: Index of elements for DB operations
	private int indexOfhyperbolic;
	private int indexOfStory;
	private int indexOfSubject1;
	private int indexOfSubject2;
	private int indexOfSubject3;
	private int indexOfVerb;
	private int indexOfLocation;

	// Constructor
	public FilmDescription()
	{
		generatedDescription=this.generateDescription();
		objName="Description";
	}

	// Delete a record in the database
	@Override
	public boolean executeDelete(int databasePrimaryKey)
	{
		boolean success=myDBConnection.deleteDescription(databasePrimaryKey);
		return success;
	}

	// Generate description
	public String generateDescription() 
	{
		String generatedDescription; // Generated description

		// Assign random content to fields
		String hyperbolic=hyperbolics.get(randomPicker(hyperbolics));
		indexOfhyperbolic=hyperbolics.indexOf(hyperbolic);
		String story=stories.get(randomPicker(stories));
		indexOfStory=stories.indexOf(story);
		String subject1=subjects.get(randomPicker(subjects));
		indexOfSubject1=subjects.indexOf(subject1);
		String subject2=subjects.get(randomPicker(subjects));
		indexOfSubject2=subjects.indexOf(subject2);
		String subject3=subjects.get(randomPicker(subjects));
		indexOfSubject3=subjects.indexOf(subject3);
		String verb=verbs.get(randomPicker(verbs));
		indexOfVerb=verbs.indexOf(verb);
		String location=locations.get(randomPicker(locations));
		indexOfLocation=locations.indexOf(location);

		//  Build the String with the fields in the template String
		generatedDescription=  capitalize(articleWord(hyperbolic)) +" "+ hyperbolic + " " +story+ " of "+ subject1 + " and " + subject2 + " who must " + verb + " " + subject3 + " in "+ location; 

		return generatedDescription;
	}

	// Print a list of the stored descriptions to the user
	public void readStoredDescription()
	{
		ArrayList<String> keys = myDBConnection.getDescriptionForeignKeys(); // Contains Primary Key and foreign keys from database
		ArrayList<String> descriptions = new ArrayList<>(); // Here we will store the merged descriptions
		FilmDescription filmDes = new FilmDescription(); //Create obj for calling delete method in parent class
		ArrayList<Integer> pkListDescription = new ArrayList<Integer>(); // Here we store primary keys for the delete option
		// Merge the descriptions
		for(int i=0;i<keys.size();i++)
		{
			String[] parts = keys.get(i).split(" "); 	// Retrieve a record and split to array by space
			pkListDescription.add(Integer.parseInt(parts[0])); 	// Primary key

			// Here we merge to one complete description in template
			String genre= capitalize(myDBConnection.getCategoryByFK(Integer.parseInt(parts[1])));
			String hyperbolic=myDBConnection.getHyperbolicByFK(Integer.parseInt(parts[2]));
			String story=myDBConnection.getStoryByFK(Integer.parseInt(parts[3]));
			String subject1=myDBConnection.getSubjectByFK(Integer.parseInt(parts[4]));
			String subject2=myDBConnection.getSubjectByFK(Integer.parseInt(parts[5]));
			String verb=myDBConnection.getVerbByFK(Integer.parseInt(parts[6]));
			String subject3=myDBConnection.getSubjectByFK(Integer.parseInt(parts[7]));
			String location=myDBConnection.getLocationByFK(Integer.parseInt(parts[8]));
			// Merge
			String mergedDescription=String.format("    %5d Genre: "+genre+ " - "+ capitalize(articleWord(hyperbolic)) + " "+ hyperbolic + " " + story + " of "+ subject1 +" and "+ subject2 +" who must "+ verb +" "+ subject3 + " in "+ location, (i+1)); 
			descriptions.add(mergedDescription); // Add description to ArrayList
		}

		// Print the descriptions from the ArrayList
		System.out.println("");
		if(!keys.isEmpty())
		{
			System.out.println("    Stored separately generated descriptions:");
		}
		else
		{
			System.out.println("    Nothing saved");
		}
		System.out.println("");
		for(int i=0;i<descriptions.size();i++)
		{
			if(i==0)
			{
				printFormattingLine(descriptions.get(i).length());	// Add line to the screen
			}
			System.out.println(descriptions.get(i));
			printFormattingLine(descriptions.get(i).length());		// Add line to the screen

		}
		System.out.println("");

		// Show options to the user
		String userChoice;
		System.out.println("    [1] Delete a description");
		System.out.println("    [2] Save to file");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");
		userChoice=userInput.nextLine().toLowerCase();
		switch(userChoice) {
		case "1":
			filmDes.deleteItem(pkListDescription);
			break;
		case "2":
			writeToFile(objName,descriptions);
			break;
		default:
			break;
		}
	}

	// Print generated description to the user
	public void showFormattedDescription()
	{
		System.out.println("\n    Generated film description:");
		printFormattingLine(generatedDescription.length());			// Dynamic line as long as the description
		System.out.println("    "+generatedDescription);			// Print the description
		printFormattingLine(generatedDescription.length());
		descriptionOptions();										// What can the user do with the description
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

	// Save generated description to the database
	private void storeGeneratedDescription() {
		int userChoiceGenre=assignGenre(); // Ask genre to assign
		// Add +1 to index because array starts from 0; foreign keys in DB starts from 1
		boolean success=myDBConnection.insertDescriptionIndex(userChoiceGenre, indexOfhyperbolic+1, indexOfStory+1, indexOfSubject1+1, indexOfSubject2+1, indexOfVerb+1, indexOfSubject3+1, indexOfLocation+1);
		if(success)
		{
			System.out.println("\n    Description saved");
		}
		else
		{
			System.out.println("\n    Description not saved due problem with database");
		}
		pressKeyToContinue();
	}

	// Needed for FilmTitleDescription class
	public String getGeneratedDescription() {
		return generatedDescription;
	}

	public int getIndexOfSubject3() {
		return indexOfSubject3;
	}

	public void setIndexOfSubject3(int indexOfSubject3) {
		this.indexOfSubject3 = indexOfSubject3;
	}

	public int getIndexOfhyperbolic() {
		return indexOfhyperbolic;
	}

	public int getIndexOfStory() {
		return indexOfStory;
	}

	public int getIndexOfSubject1() {
		return indexOfSubject1;
	}

	public int getIndexOfSubject2() {
		return indexOfSubject2;
	}

	public int getIndexOfVerb() {
		return indexOfVerb;
	}

	public int getIndexOfLocation() {
		return indexOfLocation;
	}

}