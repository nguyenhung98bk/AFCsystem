package bussinessLayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import dataLayer.prepaidCardDAO;
import dataLayer.tripInfoDAO;
import dataObject.prepaidCardDTO;
import dataObject.ticket24hDTO;
import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.scanner.CardScanner;
import presentationLayer.displaySuccess;

public class cardBUS {
	protected String createIdCard(){
		Random random = new Random();
		int x = random.nextInt(10000);
		String ran = Integer.toString(x);
		if(ran.length()<4) {
			for(int i=0;i<4-ran.length();i++) {
				ran = "0"+ran;
			}
		}
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd");
	    String formattedDate = myDateObj.format(myFormatObj);
	    String idTicket = formattedDate.concat(ran);
	    return idTicket;
	}
	protected String createCardCorresCode(String cardPseudoCode) {
		String cardCorresCode = null;
		CardScanner cardScanner = CardScanner.getInstance();
        try {
			cardCorresCode = cardScanner.process(cardPseudoCode);
		} catch (InvalidIDException e) {
			e.printStackTrace();
		}
		return cardCorresCode;	
	}
}
