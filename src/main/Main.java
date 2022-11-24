package main;

import java.util.ArrayList;
import java.util.Random;

import classes.DBConnect;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		// Make DB connection
		DBConnect myDBConnection = new DBConnect();

		// DB test
		myDBConnection.openDB();

		// retrieve films to test
		ArrayList<String> films = myDBConnection.getCategories();

		// print loaded arrayList
		for(String value: films)
		{
			System.out.println(value);
		}
	}

}
