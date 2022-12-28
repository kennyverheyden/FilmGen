package main;

import java.util.Scanner;
import classes.FilmDescription;
import classes.FilmTitle;

public class Main {

	// Main
	public static void main(String[] args) {

		showWelcome();

		runProgram(); // start content menu

		showExitMessage();

	}

	// Start content menu
	private static void runProgram()
	{
		Scanner userInput = new Scanner(System.in);
		boolean exitProgram = false;
		String userChoice;
		FilmTitle filmTit = new FilmTitle();
		FilmDescription filmDescrib = new FilmDescription();

		do
		{
			System.out.println("");
			System.out.println("    [1] Generate film (title + description)");
			System.out.println("    [2] Generate title");
			System.out.println("    [3] Generate description");
			System.out.println("    [4] Stored generated titles ");
			System.out.println("    [5] Stored generated descriptions");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");
			System.out.print("    Choice: ");
			userChoice=userInput.next().toLowerCase();

			switch(userChoice) {
			case "1":
				// PLACEHOLDER
				System.out.println("test");
				break;
			case "2":
				filmTit.showFormattedTitle();
				break;
			case "3":
				filmDescrib.showFormattedDescription();
				break;
			case "4":
				filmTit.readStoredTitle();
				break;
			case "5":
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
		System.out.println("  *****                       Film generator                          *****");
		System.out.println("  *****                                                               *****");
		System.out.println("  *************************************************************************");
	}

	// Show message when the program closes
	private static void showExitMessage() {
		System.out.println("\n    Thank you for using this program");
	}
}
