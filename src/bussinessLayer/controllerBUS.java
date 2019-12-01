package bussinessLayer;

import java.sql.SQLException;
import java.util.ArrayList;

import dataLayer.*;
import dataObject.*;
import presentationLayer.*;

public class controllerBUS {
	public void GetDataInput(String choice) throws ClassNotFoundException, SQLException {
		String[] a;
		char idStation;
		char action;
		a=choice.split("-");
		action = a[0].charAt(0);
		idStation = a[1].charAt(0);
		tripInfoDAO t = new tripInfoDAO();
		if(Character.toString(action).equals("1")) {
			t.insertStartStation(idStation);
		}
		ArrayList<prepaidCardDTO> allCard = new ArrayList<prepaidCardDTO>();
		prepaidCardBUS pr = new prepaidCardBUS();
		allCard = pr.loadListPrepaidCard();
		ArrayList<ticket24hDTO> allTicket24h = new ArrayList<ticket24hDTO>();
		ticket24hBUS ti = new ticket24hBUS();
		allTicket24h = ti.loadListTicket24h();
		displayMenu dis = new displayMenu();
		dis.ChoosingType(action,allCard,allTicket24h,idStation);
	}
	public void getInputCode(char action, ArrayList<prepaidCardDTO> allCard, ArrayList<ticket24hDTO> allTicket24h, String pseudoBarCode, char idStation) {
		prepaidCardDTO card = new prepaidCardDTO();
		for(prepaidCardDTO card2 : allCard) {
			if(card2.getCardCode1().equals(pseudoBarCode)==true) {
				card = card2;
				prepaidCardBUS pr = new prepaidCardBUS();
				pr.choosePrepaidCard(card, action,idStation);
			}
		}
		ticket24hDTO tf = new ticket24hDTO();
		for(ticket24hDTO tf2 : allTicket24h) {
			if(tf2.getTfCode1().equals(pseudoBarCode)==true) {
				tf = tf2;
				ticket24hBUS ti = new ticket24hBUS();
				ti.chooseTicket24h(tf, action,idStation);
			}
		}
	}
}
