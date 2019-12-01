package bussinessLayer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;

public class ticketBUS {
	protected String createIdTicket(){
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
	protected String createTicketCorresCode(String ticketPseudocode) {
		TicketRecognizer ticketRecognizer = TicketRecognizer.getInstance();
		String ticketCorresCode = null;
		try {
			ticketCorresCode = ticketRecognizer.process(ticketPseudocode);
		} catch (InvalidIDException e) {
			e.printStackTrace();
		}
		return ticketCorresCode;
	}
}
