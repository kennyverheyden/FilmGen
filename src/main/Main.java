package main;

import java.util.Scanner;
import classes.Film;

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
		char userChose=' ';

		do
		{
			System.out.println("    [1] Generate film name");
			System.out.println("    [2] Stored generated films");
			System.out.println("    [3] Edit content \n");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");
			System.out.print("  Choice: ");
			userChose=userInput.next().toLowerCase().charAt(0);

			switch(userChose) {
			case '1':
				filmGenerateMenu();
				break;
			case '2':
				System.out.println("....");
				break;
			case 'q':
				exitProgram = true;
				break;
			}
		}
		while(!exitProgram);
	}

	private static void filmGenerateMenu()
	{
		Scanner userInput = new Scanner(System.in);
		Film film = new Film();
		char userChose=' ';

		System.out.println("\n    Select a genre\n");
		System.out.println("    [1] Generate general film name");
		System.out.println("    [2] Genre 1");
		System.out.println("    [3] Genre 2 \n");
		System.out.println(" ");
		System.out.println("    Press [q] to quit");
		System.out.println("");
		System.out.print("  Choice: ");

		userChose=userInput.next().toLowerCase().charAt(0);
		switch(userChose) {
		case '1':
			film.buildTitle();
			pressKeyToContinue();
			break;

		case '2':
			// submenu
			System.out.print("Genre 1");
			pressKeyToContinue();
			break;
			// end sub
		case '3':
			System.out.println("Genre 2");
			break;
		}
	}

	// Pause or stop the program till user input
	private static void pressKeyToContinue() {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Press enter to continue");
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
		System.out.println("\nThank you for using this program");
	}
}
