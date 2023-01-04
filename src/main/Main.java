package main;

import java.util.Scanner;
import classes.FilmDescription;
import classes.FilmTitle;
import classes.FilmTitleDescription;

public class Main {

	// Main
	public static void main(String[] args) {

		// Initialize main scanner
		Scanner mainScanner = new Scanner(System.in);
		
		showWelcome();
	    runProgram(mainScanner); // Start content menu
		showExitMessage();
		
		// Close main scanner to prevent resource leakage
		mainScanner.close();

	}

	// Start content menu
	private static void runProgram(Scanner userInput)
	{
		// Scanner userInput = new Scanner(System.in);
		boolean exitProgram = false;
		String userChoice;
		FilmTitleDescription film = new FilmTitleDescription();
		FilmTitle filmTit = new FilmTitle();
		FilmDescription filmDescrib = new FilmDescription();

		do
		{
			System.out.println("");
			System.out.println("    [1] Generate film (title + description)");
			System.out.println("    [2] Generate title");
			System.out.println("    [3] Generate description");
			System.out.println("    [4] Stored generated films");
			System.out.println("    [5] Stored separately generated titles");
			System.out.println("    [6] Stored separately generated descriptions");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");
			System.out.print("    Choice: ");
			userChoice=userInput.next().toLowerCase();

			switch(userChoice) {
			case "1":
				film.showFormattedTitleDescription();
				break;
			case "2":
				filmTit.showFormattedTitle();
				break;
			case "3":
				filmDescrib.showFormattedDescription();
				break;
			case "4":
				film.readStoredTitleDescription(userInput);
				userInput.nextLine(); //Removing it causes to skip
				break;
			case "5":
				filmTit.readStoredTitle();
				break;
			case "6":
				filmDescrib.readStoredDescription();
				break;
			case "q":
				exitProgram = true;
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
		System.out.println("\n    Thank you for using this program");
	}
	
}