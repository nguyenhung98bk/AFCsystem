package presentationLayer;

import java.util.Scanner;

import dataObject.*;

public class displayError {
	public void displayError(prepaidCardDTO card, String error) {
		System.out.println("Invalid Prepaid Card");
		System.out.println("ID: "+card.getCardId()+", balance: "+card.getBalance()+" euros");
		System.out.println(error);
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	public void displayError(prepaidCardDTO card,String error, float price) {
		System.out.println("Invalid Prepaid Card");
		System.out.println("ID: "+card.getCardId()+", balance: "+card.getBalance()+" euros");
		System.out.println("Not enough balance: "+price +" euros");
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	public void displayError(ticket24hDTO tf,String error) {
		System.out.println("Invalid 24h Ticket");
		System.out.println("ID: "+tf.getTfId()+", valid until "+tf.getTfEnd());
		System.out.println(error);
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	public void displayError(ticket24hDTO tf,String error,String dateCurrent) {
		System.out.println("Invalid 24h Ticket");
		System.out.println("ID: "+tf.getTfId()+", valid until "+tf.getTfEnd());
		System.out.println(error+" at "+dateCurrent);
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
}
