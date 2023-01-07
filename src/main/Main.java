package main;

import java.sql.SQLException;
import java.util.Scanner;

import classes.DBConnect;
import classes.Film;
import classes.FilmDescription;
import classes.FilmTitle;
import classes.FilmTitleDescription;

public class Main {

	// Main
	public static void main(String[] args) {

		showWelcome();
		runProgram(); 		// Start content menu
		showExitMessage();

	}

	// Start content menu
	private static void runProgram()
	{
		Scanner userInput = new Scanner(System.in);
		boolean exitProgram = false;
		String userChoice;
		FilmTitleDescription film = new FilmTitleDescription();
		FilmTitle filmTit = new FilmTitle();
		FilmDescription filmDescrib = new FilmDescription();
		DBConnect dbConnect = new DBConnect();		// For database statistics and viewing the tables

		do
		{
			System.out.println("");
			System.out.println("    [1] Generate film (title + description)");
			System.out.println("    [2] Generate title");
			System.out.println("    [3] Generate description");
			System.out.println("    [4] Stored generated films");
			System.out.println("    [5] Stored separately generated titles");
			System.out.println("    [6] Stored separately generated descriptions");
			System.out.println("    [7] Database statistics");
			System.out.println("    [8] View or edit database");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");
			System.out.print("    Choice: ");
			userChoice=userInput.next().toLowerCase();

			switch(userChoice) {
			case "1":
				film.showFormattedTitleDescription();	// Film title + description
				break;
			case "2":
				filmTit.showFormattedTitle();			// Film title
				break;
			case "3":
				filmDescrib.showFormattedDescription();	// Film description
				break;
			case "4":
				film.readStoredTitleDescription();		// Read films (titles + descriptions) from database
				break;
			case "5":
				filmTit.readStoredTitle(); 				// Read separately generated titles from database
				break;
			case "6":
				filmDescrib.readStoredDescription();	// Read separately generated descriptions from database
				break;
			case "7":
				dbConnect.databaseStats();				// Show database statistics
				break;
			case "8": 									
				try {
					dbConnect.databaseView(); 			// View or edit the database
				} catch (SQLException e) {
					e.printStackTrace();
				}		
				break;
			case "q":
				exitProgram = true;			// Quit program
				userInput.close(); 			// Close scanner of (this) Main class
				Film.closeScanner(); 		// Close scanner of (parent) Film class
				dbConnect.closeScanner(); 	// Close scanner of DBConnect class
				break;
			}
		}
		while(!exitProgram);
	}

	// Show welcome message 
	private static void showWelcome()
	{
		System.out.println("\n  *************************************************************************");
		System.out.println("  *****                                                               *****");
		System.out.println("  *****                       Film Generator                          *****");
		System.out.println("  *****                                                               *****");
		System.out.println("  *************************************************************************");
	}

	// Show message when the program closes
	private static void showExitMessage() {
		System.out.println("\n    Thank you for using this program!");
	}

}