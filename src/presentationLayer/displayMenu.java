package presentationLayer;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import bussinessLayer.controllerBUS;
import bussinessLayer.prepaidCardBUS;
import bussinessLayer.ticket24hBUS;
import dataObject.prepaidCardDTO;
import dataObject.ticket24hDTO;

public class displayMenu {
	public void ChoosingType(char action,ArrayList<prepaidCardDTO> allCard,ArrayList<ticket24hDTO> allTicket24h, char idStation) {
		System.out.println("These are entering tickets/card:");
		for(prepaidCardDTO card : allCard) {
			System.out.println(card.getCardCode1()+": Prepaid card: "+card.getBalance());;
		}
		for(ticket24hDTO tf : allTicket24h) {
			if(tf.getTfStatus().equals("New")==true) {
				System.out.println(tf.getTfCode1()+": 24h Tickets: "+tf.getTfStatus());
			}
			else {
				System.out.println(tf.getTfCode1()+": 24h Tickets: "+tf.getTfStatus()+" "+tf.getTfEnd());
			}
		}
		System.out.print("Please provide the code you want to enter/exit:");
		Scanner sc = new Scanner(System.in);
		String pseudoBarCode = sc.nextLine();
		controllerBUS C = new controllerBUS();
		C.getInputCode(action, allCard,allTicket24h, pseudoBarCode,idStation);
	}
}