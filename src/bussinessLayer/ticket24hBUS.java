package bussinessLayer;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dataLayer.readInfoText;
import dataLayer.tripInfoDAO;
import dataObject.ticket24hDTO;
import hust.soict.se.customexception.InvalidIDException;
import hust.soict.se.recognizer.TicketRecognizer;
import presentationLayer.displayError;
import presentationLayer.displaySuccess;

public class ticket24hBUS extends ticketBUS implements ticket24hInterface {
	public ArrayList<ticket24hDTO> loadListTicket24h(){
		readInfoText re = new readInfoText();
		String[] listTicket24h;
		String[] a;
		listTicket24h = re.readTicket24h();
		ArrayList<ticket24hDTO> allTicket24h = new ArrayList<ticket24hDTO>();
		for(int i=0;i<listTicket24h.length;i++) {
			if(listTicket24h[i] != null) {
				a = listTicket24h[i].split("-");
				ticket24hDTO tf = new ticket24hDTO();
				tf.setTfCode1(a[0]);;
				tf.setTfStatus(a[1]);
				if(a.length==3) {
					tf.setTfEnd(a[2]);
				}
				allTicket24h .add(tf);
			}
		}
		return allTicket24h;
	}
	public String formatDate() {
		Calendar c = Calendar.getInstance();
		c.roll(Calendar.DAY_OF_YEAR, true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		String tfEnd = dateFormat.format(c.getTime());
		return tfEnd;	
	}
	public String[] formatOutputFile(ArrayList<ticket24hDTO> allTicket24h) {
		String[] textInALine = new String[5];
		int i=0;
		for(ticket24hDTO tf : allTicket24h) {
			if(tf.getTfStatus().equals("New")==true) {
				textInALine[i] = tf.getTfCode1()+"-"+tf.getTfStatus();
			}
			else {
				textInALine[i] = tf.getTfCode1()+"-"+tf.getTfStatus()+"-"+tf.getTfEnd();
			}
			i++;
		}
		return textInALine;
	}
	public void enterByTicket24hNew(ticket24hDTO tf) {
		int i=0;
		tf.setTfStatus("Valid until");
		tf.setTfEnd(formatDate());
		ArrayList<ticket24hDTO> allTicket24h = loadListTicket24h();
		for(ticket24hDTO tf2 : allTicket24h) {
			if(tf2.getTfCode1().equals(tf.getTfCode1())==true) {
				allTicket24h.get(i).setTfStatus(tf.getTfStatus());
				allTicket24h.get(i).setTfEnd(tf.getTfEnd());
			}
			i++;
		}
		formatOutputFile(allTicket24h);
		String[] textInAline = formatOutputFile(allTicket24h);
		readInfoText re = new readInfoText();
		re.editStatusTicket24h(textInAline);
		displaySuccess dis = new displaySuccess();
		dis.enteringSuccess(tf);
	}
	public boolean checkValidUntilTicket24h(ticket24hDTO tf){
		Date dateCurrent = new Date();
		Date date = new Date();
		try {
			SimpleDateFormat datetime = new SimpleDateFormat("HH:mm dd/MM/yyyy");
			date = datetime.parse(tf.getTfEnd());
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			dateCurrent = datetime.parse(dtf.format(now));
			date.compareTo(dateCurrent);
		}
		catch (Exception e) {
			
		}
		if(date.compareTo(dateCurrent)==1) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean checkTicket24hUsing(ticket24hDTO tf) {
		tripInfoDAO tr = new tripInfoDAO();
		char idEndStation = tr.searchEndStation(tf);
		if(idEndStation != 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public void chooseTicket24h(ticket24hDTO tf, char action, char idStation) {
		tf.setTfId(createIdTicket());
		tf.setTfCode2(createTicketCorresCode(tf.getTfCode1()));
		if(Character.toString(action).equals("1")) {
			if(tf.getTfStatus().equals("New")) {
				tripInfoDAO tr = new tripInfoDAO();
        		tr.updateCode(tf,idStation);
				enterByTicket24hNew(tf);
			}
			else {
				if(checkValidUntilTicket24h(tf)) {
					if(checkTicket24hUsing(tf)) {
						tripInfoDAO tr = new tripInfoDAO();
		        		tr.updateCode(tf,idStation);
						displaySuccess dis = new displaySuccess();
						dis.enteringSuccess(tf);
					}
					else {
						displayError dis = new displayError();
						dis.displayError(tf, "This ticket is using by another person.");
					}
				}
				else {
					Calendar c = Calendar.getInstance();
					SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
					String dateCurrent = dateFormat.format(c.getTime());
					displayError dis = new displayError();
					dis.displayError(tf, "Expired: Try to enter",dateCurrent);
				}
			}
		}
		else {
			tripInfoDAO tr = new tripInfoDAO();
    		tr.updateEndStation(tf, idStation);
			displaySuccess dis = new displaySuccess();
			dis.enteringSuccess(tf);
		}
	}
}
