package dataObject;

public class prepaidCardDTO {
	private String cardId;
	private String cardCode1;
	private String cardCode2;
	private float balance;
	private int cardStatus;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getCardCode1() {
		return cardCode1;
	}
	public void setCardCode1(String cardCode1) {
		this.cardCode1 = cardCode1;
	}
	public String getCardCode2() {
		return cardCode2;
	}
	public void setCardCode2(String cardCode2) {
		this.cardCode2 = cardCode2;
	}
	public int getCardStatus() {
		return cardStatus;
	}
	public void setCardStatus(int cardStatus) {
		this.cardStatus = cardStatus;
	}
}
