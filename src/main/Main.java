package main;

import java.util.ArrayList;
import java.util.Scanner;
import classes.DBConnect;
import classes.Film;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Scanner userinput = new Scanner(System.in);
		boolean exitProgram = false;
		char userChose=' ';
		Film film = new Film();

		
		showWelcome();

		
		do
		{

			System.out.println("    [1] Generate Film name");
			System.out.println("    [2] Stored generated films");
			System.out.println("    [3] Maintain \n");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");

			System.out.print("  Choice: ");
			userChose=userinput.next().toLowerCase().charAt(0);

			switch(userChose) {
			case '1':
				film.buildTitle();
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

	public static void showWelcome()
	{
		System.out.println("\n  *************************************************************************");
		System.out.println("  *****                                                               *****");
		System.out.println("  *****                       Film generator                          *****");
		System.out.println("  *****                                                               *****");
		System.out.println("  *************************************************************************");
		System.out.println(" ");
	}

}
