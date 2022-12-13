package main;

import java.util.ArrayList;
import java.util.Scanner;
import classes.DBConnect;

public class Main {


	public static void main(String[] args) {
		// TODO Auto-generated method stub


				// Make DB connection
				DBConnect myDBConnection = new DBConnect();
		
				// Tables of DB loaded separately in ArrayLists
				ArrayList<String> categories = myDBConnection.getCategorie();
				ArrayList<String> hyperbolic = myDBConnection.getHyperbolic();
				ArrayList<String> languages = myDBConnection.getLanguages();
				ArrayList<String> locations = myDBConnection.getLocations();
				ArrayList<String> stories = myDBConnection.getStories();
				ArrayList<String> subjects = myDBConnection.getSubjects();
				ArrayList<String> verbs = myDBConnection.getVerbs();
				ArrayList<String> words = myDBConnection.getVerbs();
		
				// print loaded arrayList to test
				for(String value: words)
				{
					System.out.println(value);
				}

		Scanner userinput = new Scanner(System.in);
		boolean exitProgram = false;
		char userChose=' ';

		showWelcome();


		do
		{

			System.out.println("    [1] Generate Film name");
			System.out.println("    [2] Stored generated films \n");
			System.out.println("    [3] Maintain \n");
			System.out.println(" ");
			System.out.println("    Press [q] to quit");
			System.out.println("");

			System.out.print("  Choice: ");
			userChose=userinput.next().toLowerCase().charAt(0);

			switch(userChose) {
			case '1':
				System.out.println("....");
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
