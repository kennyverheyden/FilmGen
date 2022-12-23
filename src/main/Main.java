package main;

import java.util.Scanner;
import classes.Film;
import classes.FilmDescription;

public class Main {


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
		char userChoice=' ';
		FilmDescription filmDescrib = new FilmDescription();

		do
		{
			System.out.println("    [1] Generate description");
			System.out.println("    [2] Stored generated descriptions");
			//System.out.println("    [3] Edit content \n");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");
			System.out.print("  Choice: ");
			userChoice=userInput.next().toLowerCase().charAt(0);

			switch(userChoice) {
			case '1':
				filmDescrib.descriptionGenerateMenu();
				break;
			case '2':
				filmDescrib.readStoredDescription();
				break;
			case 'q':
				exitProgram = true;
				break;
			}
		}
		while(!exitProgram);
	}

	// Pause or stop the program till user input
	private static void pressKeyToContinue() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("   Press enter to continue");
		userInput.nextLine();
	}

	// Show welcome message 
	private static void showWelcome()
	{
		System.out.println("\n  *************************************************************************");
		System.out.println("  *****                                                               *****");
		System.out.println("  *****                       Film generator                          *****");
		System.out.println("  *****                                                               *****");
		System.out.println("  *************************************************************************");
		System.out.println(" ");
	}

	// Show message when the program closes
	private static void showExitMessage() {
		System.out.println("\n   Thank you for using this program");
	}
}
