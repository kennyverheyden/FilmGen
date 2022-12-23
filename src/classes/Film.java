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
		System.out.print("  "); // formatting: add extra space before line
		for(int i=0;i<length-1;i++)  // extend dynamic the line as long as the string
		{
			System.out.print("-");	
		}
		System.out.println(); // new line
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
