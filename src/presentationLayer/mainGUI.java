package presentationLayer;

import java.sql.SQLException;
import java.util.Scanner;

import bussinessLayer.*;

public class mainGUI {
	public static void mainMenu() {
		System.out.println("These are station in the line M14 of Paris");
		System.out.println("a. Saint-Lazare"); 
		System.out.println("b,Madeleine");
		System.out.println("c,Pyramides"); 
		System.out.println("d,Chatelet"); 
		System.out.println("e,Gare de Lyon"); 
		System.out.println("f,Bercy");
		System.out.println("g,Cour Saint-Emilion"); 
		System.out.println("h,Bibliotheque Francois Mitterrand"); 
		System.out.println("i,Olympiades");
		System.out.println("\nAvailable actions: 1. Enter station, 2. Exit station");
		System.out.println("You can provide a combination of number (1 or 2) and a letter from (a to i) to enter or exit a station (using hyphen in between).");
		System.out.print("Enter your choice:");
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String choice,pseudoBarCode;
		int con=0;
		do {
			mainMenu();
			Scanner sc = new Scanner(System.in);
		    choice = sc.nextLine();
		    controllerBUS c = new controllerBUS();
		    c.GetDataInput(choice);
		}while(con==0);
	}

}
