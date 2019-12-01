package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dataObject.prepaidCardDTO;
import dataObject.ticket24hDTO;


public class tripInfoDAO extends connectdb{
    private String query;
	public void insertStartStation(char idStation){
		try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "INSERT INTO tripinfo (idstartstation) values ('" + idStation + "')";
			st.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void updateCode(prepaidCardDTO card) {
		int idTrip = 0;
		try {
			Connection conn = getPostgresqlConnection();
			query = "select max(id) from tripinfo"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				idTrip = Rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "UPDATE tripinfo SET pseudocode = '"+card.getCardCode1()+"' WHERE id = "+idTrip;
			st.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void updateCode(ticket24hDTO tf,char idStartStation) {
		int idTrip = 0;
		try {
			Connection conn = getPostgresqlConnection();
			query = "select max(id) from tripinfo"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				idTrip = Rs.getInt(1);
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "UPDATE tripinfo SET pseudocode = '"+tf.getTfCode1()+"' WHERE id = "+idTrip;
			st.executeUpdate(query);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public char searchEndStation(ticket24hDTO tf) {
		char idEndStation = 0;
		try {
			Connection conn = getPostgresqlConnection();
			query = "select idendstation from tripinfo  group by id having id = All(select max(id) from tripinfo where pseudocode = '"+ tf.getTfCode1() +"')"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				if(Rs.getString(1) != null) {
					idEndStation = Rs.getString(1).charAt(0);
				}
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return idEndStation;
	}
	public char searchStartStation(prepaidCardDTO card, char idEndStation) {
		char idStartStation = 0;
		int maxId = 0;
		try {
			Connection conn = getPostgresqlConnection();
			query = "select idstartstation,max(id) from tripinfo  group by id having id = All(select max(id) from tripinfo where pseudocode = '"+ card.getCardCode1() +"')"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				idStartStation = Rs.getString(1).charAt(0);
				maxId = Rs.getInt(2);
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "UPDATE tripinfo SET idendstation = '" 
					+ idEndStation 
					+ "' WHERE pseudocode = '"
					+ card.getCardCode1()
					+ "'AND id = " + maxId;
			st.executeUpdate(query);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return idStartStation;
	}
	public void updateEndStation(ticket24hDTO tf, char idEndStation) {
		char idStartStation = 0;
		int maxId = 0;
		try {
			Connection conn = getPostgresqlConnection();
			query = "select idstartstation,max(id) from tripinfo  group by id having id = All(select max(id) from tripinfo where pseudocode = '"+ tf.getTfCode1() +"')"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				idStartStation = Rs.getString(1).charAt(0);
				maxId = Rs.getInt(2);
			}
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
		}
		try {
			Connection conn = getPostgresqlConnection();
			Statement st = conn.createStatement();
			query = "UPDATE tripinfo SET idendstation = '" 
					+ idEndStation 
					+ "' WHERE pseudocode = '"
					+ tf.getTfCode1()
					+ "'AND id = " + maxId;
			st.executeUpdate(query);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
