package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.upenn.cit594.data.Ticket;

public class ParkingReaderJSON implements ParkingReader {
    protected String filename;

    public ParkingReaderJSON(String fileName) {
        this.filename = fileName;
    }

    @Override
    public List<Ticket> getAllTickets() {

        JSONParser parser = new JSONParser();
        List<Ticket> ticketData = new ArrayList<Ticket>();
        try {
            JSONArray tickets = (JSONArray) parser.parse(new FileReader(filename));
            Iterator iter = tickets.iterator();
            while (iter.hasNext()) {
                JSONObject ticket = (JSONObject) iter.next();
                Integer ticketNumber = Integer.parseInt(ticket.get("ticket_number").toString());
                String plateID = ticket.get("plate_id").toString();
                String date = ticket.get("date").toString();
                String zipCode = ticket.get("zip_code").toString().substring(0, 5); //take only first five digit as zipcode
                String violation = ticket.get("violation").toString();
                Double fine = Double.parseDouble(ticket.get("fine").toString());
                String state = ticket.get("state").toString();
                ticketData.add(new Ticket(ticketNumber, plateID, date, zipCode, violation, fine, state));

            }

        } catch (Exception e) {

        }
        return ticketData;
    }

}
