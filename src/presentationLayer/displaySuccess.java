package presentationLayer;

import java.util.Scanner;

import dataLayer.prepaidCardDAO;
import dataObject.prepaidCardDTO;
import dataObject.ticket24hDTO;

public class displaySuccess {
	public void enteringSuccess(prepaidCardDTO card) {
		System.out.println("Opening gate...");
		System.out.println("Type : Card\tID: "+card.getCardId());
		System.out.println("Balance: "+card.getBalance());
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	public void exitingSuccess(prepaidCardDTO card) {
		System.out.println("Opening gate...");
		System.out.println("Type : Card\tID: "+card.getCardId());
		System.out.println("Balance: "+card.getBalance());
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	public void enteringSuccess(ticket24hDTO tf) {
		System.out.println("Opening gate...");
		System.out.println("Type : \tID: "+tf.getTfId());
		System.out.println("Valid until: "+tf.getTfEnd());
		System.out.println("Press any key to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
}
