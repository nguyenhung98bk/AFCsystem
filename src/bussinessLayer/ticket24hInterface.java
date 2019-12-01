package bussinessLayer;

import java.util.ArrayList;

import dataObject.ticket24hDTO;

public interface ticket24hInterface {
	public ArrayList<ticket24hDTO> loadListTicket24h();
	public String formatDate();
	public String[] formatOutputFile(ArrayList<ticket24hDTO> allTicket24h);
	public void enterByTicket24hNew(ticket24hDTO tf);
	public boolean checkValidUntilTicket24h(ticket24hDTO tf);
	public boolean checkTicket24hUsing(ticket24hDTO tf);
	public void chooseTicket24h(ticket24hDTO tf, char action, char idStation);
}
