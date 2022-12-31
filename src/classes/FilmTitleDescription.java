package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class FilmTitleDescription extends Film{

	FilmTitle title = new FilmTitle(); // Title
	FilmDescription description = new FilmDescription(); // Description
	private String generatedTitle; // Store generated title
	private String generatedDescription; // Store generated description

	// Constructor
	public FilmTitleDescription()
	{
		generatedTitle=title.getGeneratedTitle();
		generatedDescription=description.getGeneratedDescription();
	}

	// Delete a record in the database
	@Override
	public boolean executeDelete(int databasePrimaryKey)
	{
		boolean success=myDBConnection.deleteFilm(databasePrimaryKey);
		return success;
	}

	// Show the generated film title and description
	public void showFormattedTitleDescription()
	{
		System.out.println("\n    Generated film:");
		printFormattingLine(generatedDescription.length());  // Extend dynamic the line as long as the title
		System.out.println("    Title:\n    "+generatedTitle); // Print the title
		printFormattingLine(generatedTitle.length());
		System.out.println("    Description:\n    "+generatedDescription);
		printFormattingLine(generatedDescription.length());
		titleDescriptionOptions(); // What can the user do with the title
	}

	// User generate new title
	private void generateNewTitle()
	{
		this.generatedTitle=title.generateTitle();
	}

	// User generate new description
	private void generateNewDescription()
	{
		this.generatedDescription=description.generateDescription();
	}

	// Generated film options, regenerate and store in DB
	private void titleDescriptionOptions()
	{
		System.out.println("");
		System.out.println("    [1] Generate another film");
		System.out.println("    [2] Generate another title");
		System.out.println("    [3] Generate antoher description");
		System.out.println("    [4] Save the generated film");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");

		Scanner userInput = new Scanner(System.in);
		String userChoice = userInput.nextLine().toLowerCase();

		switch(userChoice) {
		case "1":
			// Generate another film (title and description)
			generateNewTitle();
			generateNewDescription();
			this.showFormattedTitleDescription();
			break;
		case "2":
			// Generate another title
			generateNewTitle();
			this.showFormattedTitleDescription();
			break;
		case "3":
			// Generate another description
			generateNewDescription();
			this.showFormattedTitleDescription();
			break;
		case "4":
			// Save film to the database
			this.storeGeneratedFilmDescription();
			break;
		default:
			break;
		}

	}

	// Save generated film to the database
	private void storeGeneratedFilmDescription() {
		// Ask genre to assign
		int userChoiceGenre=assignGenre();

		// Get indexes from object instances
		int indexOfWord1=title.getIndexOfWord();
		int indexOfWord2=title.getIndexOfWord_2();
		int indexOfhyperbolic=description.getIndexOfhyperbolic();
		int indexOfStories=description.getIndexOfStories();
		int indexOfSubjects1=description.getIndexOfSubject1();
		int indexOfSubjects2=description.getIndexOfSubject2();
		int indexOfVerbs=description.getIndexOfVerbs();
		int indexOfSubject3=description.getIndexOfSubject3();
		int indexOfLocation=description.getIndexOfLocation();

		// Add +1 to index because array starts from 0; foreign keys in DB starts from 1
		boolean success=myDBConnection.insertFilmIndex(userChoiceGenre, indexOfWord1+1, indexOfWord2+1, indexOfhyperbolic+1, indexOfStories+1, indexOfSubjects1+1, indexOfSubjects2+1, indexOfVerbs+1, indexOfSubject3+1, indexOfLocation+1);
		if(success)
		{
			System.out.println("\n    Film saved");
		}
		else
		{
			System.out.println("\n    Film not saved due problem with database");
		}
		pressKeyToContinue();
	}

	// Print a list of the stored films to the user
	public void readStoredTitleDescription()
	{
		ArrayList<String> keys = myDBConnection.getFilmForeignKeys(); // Contains Primary Key and foreign keys from database
		ArrayList<String> films = new ArrayList<>(); // Here we will store the merged film titles and descriptions
		FilmTitleDescription filmTitleDes = new FilmTitleDescription(); //Create obj for calling delete method in parent class
		// Merge
		for(int i=0;i<keys.size();i++)
		{
			String[] parts = keys.get(i).split(" "); // Retrieve a record and split to array by space

			// Here we merge to one complete film in template
			// We get the keywords from the other ArrayList getters from the fields, which are already connected to the DB
			// The index number is the foreign key number stored in the parts array

			String genre= getCategories().get(Integer.parseInt((parts[1]))-1).toLowerCase();
			String word1 = getWords().get(Integer.parseInt((parts[2]))-1).toLowerCase();;
			String word2=getWords().get(Integer.parseInt((parts[3]))-1);
			String hyperbolic=getHyperbolics().get(Integer.parseInt((parts[4]))-1);
			String stories=getStories().get(Integer.parseInt((parts[5]))-1);
			String subject1=getSubjects().get(Integer.parseInt((parts[6]))-1);
			String subject2=getSubjects().get(Integer.parseInt((parts[7]))-1);
			String verb=getVerbs().get(Integer.parseInt((parts[8]))-1);
			String subject3=getSubjects().get(Integer.parseInt((parts[9]))-1);
			String location=getLocations().get(Integer.parseInt((parts[10]))-1);
			// Merge
			String mergedFilm="    ["+(i+1)+"] Genre: "+ genre + " - Film: "+ capitalize(word1) +" "+ capitalize(word2) + "\n    Description: "+ capitalize(articleWord(hyperbolic)) +" " + hyperbolic +" "+ stories +" of "+ subject1 +" and "+ subject2 + " who must "+ verb + " " + subject3 + " in " + location; 
			films.add(mergedFilm); // Add film to ArrayList
		}

		// Print the films from the ArrayList
		System.out.println("");
		if(!keys.isEmpty())
		{
			System.out.println("    Stored generated films (title + description):");
		}
		else
		{
			System.out.println("    Nothing saved");
		}
		System.out.println("");
		for(int i=0;i<films.size();i++)
		{
			if(i==0)
			{
				printFormattingLine(films.get(i).length()); // Add line to the screen
			}
			System.out.println(films.get(i));
			printFormattingLine(films.get(i).length()); // Add line to the screen

		}
		System.out.println("");

		// Show options to the user
		Scanner userInput = new Scanner(System.in);
		String userChoice;
		System.out.println("    [1] Delete a film");
		System.out.println("\n    Press just enter for main menu");
		System.out.println("");
		System.out.print("    Choice: ");
		userChoice=userInput.nextLine().toLowerCase();
		switch(userChoice) {
		case "1":
			filmTitleDes.deleteItem(keys);
			break;
		default:
			break;
		}
	}

}