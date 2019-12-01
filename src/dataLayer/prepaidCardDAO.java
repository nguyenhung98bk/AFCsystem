package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dataObject.*;

public class prepaidCardDAO extends connectdb{
    private String query;
    public ArrayList<prepaidCardDTO> selectCard() {
    	ArrayList<prepaidCardDTO> allCard = new ArrayList<prepaidCardDTO>();
	    try {
			Connection conn = getPostgresqlConnection();
			query = "select * from prepaidcard"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				prepaidCardDTO card = new prepaidCardDTO();
				card.setCardId(Rs.getString(1));
				card.setCardCode1(Rs.getString(2));
				card.setCardCode2(Rs.getString(3));
				card.setBalance(Rs.getFloat(4));
				card.setCardStatus(Rs.getInt(5));
				allCard.add(card);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	    return allCard;
    }
    public void insertCard(prepaidCardDTO card) {
    	try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "INSERT INTO prepaidcard values ('" + card.getCardId() +"','"+card.getCardCode1()+"','"+card.getCardCode2()+"','"+card.getBalance() + "','1')";
			st.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    }
    public void updateCard(prepaidCardDTO card) {
    	try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "UPDATE prepaidcard SET cardbalance = " + card.getBalance() + ",cardstatus=0 WHERE cardpseudocode='"+card.getCardCode1()+"'";
			st.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
    }
}
