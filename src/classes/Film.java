package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public abstract class Film {

	// Make DB connection
	static DBConnect myDBConnection = new DBConnect();

	// Tables of DB loaded separately in ArrayLists
	static ArrayList<String> categories = myDBConnection.getCategorie();
	static ArrayList<String> hyperbolics = myDBConnection.getHyperbolic();
	static ArrayList<String> locations = myDBConnection.getLocations();
	static ArrayList<String> stories = myDBConnection.getStories();
	static ArrayList<String> subjects = myDBConnection.getSubjects();
	static ArrayList<String> verbs = myDBConnection.getVerbs();
	static ArrayList<String> words = myDBConnection.getWords();

	//PARENT CLASS

	public Film(){	
	}

	// random picker in the ArrayList
	public int randomPicker(ArrayList obj)
	{
		Random rn = new Random();
		int randomNum = rn.nextInt(obj.size());
		return randomNum;
	}

	// Dynamic line
	public static void printFormattingLine(int length) // take e.g string.length() 
	{
		System.out.print("    "); // formatting: add extra space before line
		for(int i=0;i<length-1;i++)  // extend dynamic the line as long as the string
		{
			System.out.print("-");	
		}
		System.out.println(); // new line
	}

	public int askGengre()
	{
		System.out.println("\n    Assign one of the following genres.");
		System.out.println("");  // new line
		System.out.print("   "); // add some spaces before line
		for(int i=0;i<categories.size();i++)
		{
			System.out.print(" "+(i+1)+ "="+capitalize(categories.get(i))+" ");
			if(i>3 && i%5==0) {System.out.print("\n   ");}; // add new line for readability
		}
		System.out.print("\n    Assign genre number: ");
		// Ask user choice
		Scanner userInput = new Scanner(System.in);

		String input = userInput.nextLine();
		int userChoiceGenre=Integer.parseInt(input);

		// As long as input is integer
		// Input choice is not 0, because PK in DB starts from 1
		// Input choice not bigger than highest PK in DB (Size arrayList categories)
		while (!isInteger(input) || userChoiceGenre==0 || userChoiceGenre>categories.size()) {
			System.out.print("\n    Enter a valid number: ");
			input = userInput.nextLine();
			userChoiceGenre=Integer.parseInt(input);
		}

		return userChoiceGenre;
	}

	public static boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		}
		// only got here if we didn't return false
		return true;
	}

	public abstract boolean executeDelete(int databasePrimaryKey);

	public void deleteItem(ArrayList<String> keys) {
		Scanner userInput = new Scanner(System.in);
		System.out.print("\n    Delete item: ");
		String input=userInput.nextLine(); // Get userChose number
		int toDelete = Integer.parseInt(input);
		if(!keys.isEmpty())
		{
			// As long as input is integer
			// Input choice is not 0, because PK in DB starts from 1
			// Input choice not bigger than highest PK in DB (Size arrayList)
			while (!isInteger(input) || toDelete==0 || toDelete>keys.size()) 
			{
				System.out.print("\n    Enter a valid number: ");
				input=userInput.nextLine();
				toDelete = Integer.parseInt(input);
			}

			String[] parts = keys.get((toDelete)-1).split(" "); // SUBTRACT -1 index array // Contains Primary Key on index 0; //
			int databasePrimaryKey = Integer.parseInt(parts[(0)]); // Read Primary Key on index 0;
			if(executeDelete(databasePrimaryKey))
			{
				System.out.println("\n    Chosen item deleted");
			}
			else
			{
				System.out.println("    Item not deleted due database error");
			}
			System.out.println("");
		}
		else
		{
			System.out.println("\n    No stored items in database\n");
		}
	}

	public static String capitalize(String str) {
		if(str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1);
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
