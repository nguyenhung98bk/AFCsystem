package bussinessLayer;

import java.awt.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import dataLayer.*;
import dataObject.*;
import presentationLayer.*;
import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.gate.Gate;
import hust.soict.se.scanner.CardScanner;

public class prepaidCardBUS extends cardBUS implements prepaidCardInterface{
	public boolean checkValidationCard(prepaidCardDTO card,float price) {
		if(card.getBalance()>=price) {
			return true;
		}
		else {
			return false;
		}
	}
	public ArrayList<prepaidCardDTO> loadListPrepaidCard() {
		String[] listcard,a;
		readInfoText re = new readInfoText();
		listcard = re.readcard();
		ArrayList<prepaidCardDTO> allCard = new ArrayList<prepaidCardDTO>();
		for(int i=0;i<listcard.length;i++) {
			if(listcard[i] != null) {
				a = listcard[i].split("-");
				prepaidCardDTO card = new prepaidCardDTO();
				card.setCardCode1(a[0]);
				card.setBalance(Float.parseFloat(a[1]));
				allCard.add(card);
			}
		}
		return allCard;
	}
	public String[] formatOutputFile(ArrayList<prepaidCardDTO> allCard) {
		String[] textInALine = new String[5];
		int i=0;
		for(prepaidCardDTO card : allCard) {
			System.out.println(card.getCardCode1()+"-"+card.getBalance());
			textInALine[i] = card.getCardCode1()+"-"+card.getBalance();
			i++;
		}
		return textInALine;
	}
	public boolean checkPrepaidCardStatus(prepaidCardDTO card) {
		if(card.getCardStatus()==1) {
			return false;
		}
		return true;
	}
	public prepaidCardDTO searchPrepaidCard(prepaidCardDTO card) {
		ArrayList<prepaidCardDTO> allCard = new ArrayList<prepaidCardDTO>();
		prepaidCardDAO pr = new prepaidCardDAO();
		allCard = pr.selectCard();
		for(prepaidCardDTO card2: allCard) {
			if(card.getCardCode1().equals(card2.getCardCode1())==true){
				card = card2;
				break;
			}
		}
		return card;
	}
	public boolean issetPrepaidCard(String cardCodePseudo) {
		ArrayList<prepaidCardDTO> allCard = new ArrayList<prepaidCardDTO>();
		prepaidCardDAO pr = new prepaidCardDAO();
		allCard = pr.selectCard();
		for(prepaidCardDTO card: allCard) {
			if(cardCodePseudo.equals(card.getCardCode1())==true){
				return true;
			}
		}
		return false;
	}
	public void enterByPrepaidCard(prepaidCardDTO card) {
		if(issetPrepaidCard(card.getCardCode1())==true){
    		card = searchPrepaidCard(card);
    		if(checkPrepaidCardStatus(card) == true) {
    			tripInfoDAO tr = new tripInfoDAO();
        		tr.updateCode(card);
    			displaySuccess dis = new displaySuccess();
    			dis.enteringSuccess(card);
    		}
    		else {
    			String error = "This card is using by another person.";
    			displayPrepaidCardError(card,error);
    		}
    	}
    	else {
    		card.setCardId(createIdCard());
    		prepaidCardDAO pr = new prepaidCardDAO();
    		pr.insertCard(card);
    		tripInfoDAO tr = new tripInfoDAO();
    		tr.updateCode(card);
    		displaySuccess dis = new displaySuccess();
			dis.enteringSuccess(card);
    	}
	}
	public void exitByPrePaidCard(prepaidCardDTO card, char idStation) {
		card = searchPrepaidCard(card);
    	if(checkPrepaidCardStatus(card)==true){
    		String error = "Card not entered.";
    		displayPrepaidCardError(card,error);
    	}
    	else {
    		char idStartStation;
    		char idEndStation = idStation;
    		tripInfoDAO tr = new tripInfoDAO();
    		idStartStation = tr.searchStartStation(card, idEndStation);
    		calculateBUS C = new calculateBUS();
    		float distance = C.distance(idStartStation, idEndStation);
    		float price = C.pricing(distance);
    		if(checkValidationCard(card, price)==true) {
    			card.setBalance(card.getBalance()-price);
    			ArrayList<prepaidCardDTO> allCard = loadListPrepaidCard();
    			int i=0;
    			for(prepaidCardDTO card2 : allCard) {
    				if(card2.getCardCode1().equals(card.getCardCode1())) {
    					allCard.get(i).setBalance(card.getBalance());
    				}
    				i++;
    			}
    			String[] textInAline = formatOutputFile(allCard);
    			for(int j=0;j<textInAline.length;j++) {
    				if(textInAline[i] != null) {
    					System.out.println(textInAline);
    				}
    			}
    			readInfoText re = new readInfoText();
    			re.editBalance(textInAline);
    			prepaidCardDAO pr = new prepaidCardDAO();
    			pr.updateCard(card);
    			displaySuccess dis = new displaySuccess();
    			dis.exitingSuccess(card);
    		}
    		else {
    			String error = "Not enough balance: ";
    			displayPrepaidcardError(card,error,price);
    		}
    	}
	}
	public void displayPrepaidCardError(prepaidCardDTO card, String error) {
		presentationLayer.displayError dis = new presentationLayer.displayError();
		dis.displayError(card, error);
	}
	public void displayPrepaidcardError(prepaidCardDTO card, String error, float price) {
		presentationLayer.displayError dis = new presentationLayer.displayError();
		dis.displayError(card, error,price);
	}
	public void choosePrepaidCard(prepaidCardDTO card, char action, char idStation) {
		card.setCardCode2(createCardCorresCode(card.getCardCode1()));;
        if(Character.toString(action).equals("1")) {
        	enterByPrepaidCard(card);
        }
        else {
        	exitByPrePaidCard(card, idStation);
        }
	}
}

