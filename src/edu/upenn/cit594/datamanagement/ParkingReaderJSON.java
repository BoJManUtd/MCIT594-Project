package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import edu.upenn.cit594.data.Ticket;

public class ParkingReaderJSON implements ParkingReader {
    protected String fileName;

    public ParkingReaderJSON(String fileName) {
        this.fileName = fileName;
    }
    
    public String getFileName() {
    	return this.fileName;
    }

    @Override
    public List<Ticket> getAllTickets() {
        JSONParser parser = new JSONParser();
        List<Ticket> ticketData = new ArrayList<Ticket>();
        try {
			JSONArray JSONTickets = (JSONArray) parser.parse(new FileReader(this.fileName));
			@SuppressWarnings("rawtypes")
			Iterator iter = JSONTickets.iterator();
			while(iter.hasNext()) {
				JSONObject JSONTicket = (JSONObject) iter.next();
                String ticketNumber = JSONTicket.get("ticket_number").toString().trim();
                String plateID = JSONTicket.get("plate_id").toString().trim();
                String date = JSONTicket.get("date").toString().trim();
                String zipCode = JSONTicket.get("zip_code").toString().trim();
                if(zipCode.length() > 5) {
                	zipCode = zipCode.substring(0, 5);
                }
                String violation = JSONTicket.get("violation").toString().trim();
                String fine = JSONTicket.get("fine").toString().trim();
                String state = JSONTicket.get("state").toString().trim();
				ticketData.add(new Ticket(ticketNumber, plateID, date, zipCode, violation, fine, state));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found !");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("File cannot be openned !");
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return ticketData;
    }
}