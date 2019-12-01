package dataLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class stationDAO extends connectdb {
    private String query;
    public float distance(char idStation) {
    	float distance=0;
    	try {
			Connection conn = getPostgresqlConnection();
			query = "select distance from station where stationid = '"+idStation +"'"; 
			PreparedStatement sml = conn.prepareStatement(query);
			ResultSet Rs = sml.executeQuery();
			while(Rs.next()) {
				distance = Rs.getFloat(1);
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
    	return distance;
    }
}
