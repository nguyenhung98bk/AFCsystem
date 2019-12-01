package bussinessLayer;

import java.util.ArrayList;

import dataObject.prepaidCardDTO;

public interface prepaidCardInterface {
	public boolean checkPrepaidCardStatus(prepaidCardDTO card);
	public ArrayList<prepaidCardDTO> loadListPrepaidCard();
	public String[] formatOutputFile(ArrayList<prepaidCardDTO> allCard);
	public boolean checkValidationCard(prepaidCardDTO card,float price);
	public boolean issetPrepaidCard(String cardCodePseudo);
	public prepaidCardDTO searchPrepaidCard(prepaidCardDTO card);
	public void enterByPrepaidCard(prepaidCardDTO card);
	public void exitByPrePaidCard(prepaidCardDTO card, char idStation);
	public void displayPrepaidCardError(prepaidCardDTO card, String error);
}
