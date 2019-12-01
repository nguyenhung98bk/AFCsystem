package dataLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import dataObject.ticket24hDTO;

public class readInfoText {
	public String[] readcard() {
		BufferedReader br = null;		
        String[] textInALine = new String[5];
        try {   
        	int i=0;
        	br = new BufferedReader(new FileReader("/home/hung/card.txt"));
            while ((textInALine[i] = br.readLine()) != null) {
            	i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textInALine;
	}
	public String[] readTicket24h() {
		BufferedReader br = null;		
        String[] textInALine = new String[5];
        try {   
        	int i=0;
        	br = new BufferedReader(new FileReader("/home/hung/twentyfour.txt"));
            while ((textInALine[i] = br.readLine()) != null) {
            	i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textInALine;
	}
	public void editStatusTicket24h(String[] textInAline){
		try {
			FileWriter writer = new FileWriter("/home/hung/twentyfour.txt");
			BufferedWriter buffer = new BufferedWriter(writer);
			for(int i=0;i<textInAline.length;i++) {
				if(textInAline[i] != null) {
					buffer.write(textInAline[i]+"\n");
				}
			}
			buffer.close();
		}
		catch (Exception ex) {
			System.out.println("Loi ghi file: "+ex);
		}
	}
	public void editBalance(String[] textInAline) {
		try {
			FileWriter writer = new FileWriter("/home/hung/card.txt");
			BufferedWriter buffer = new BufferedWriter(writer);
			for(int i=0;i<textInAline.length;i++) {
				if(textInAline[i] != null) {
					buffer.write(textInAline[i]+"\n");
				}
			}
			buffer.close();
		}
		catch (Exception ex) {
			System.out.println("Loi ghi file: "+ex);
		}
	}
}
