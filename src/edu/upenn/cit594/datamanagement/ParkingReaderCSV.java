package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Ticket;

public class ParkingReaderCSV implements ParkingReader {
    protected String fileName;

    public ParkingReaderCSV(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
    	return this.fileName;
    }
    
    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> ticketData = new ArrayList<Ticket>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {        	
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                String[] ticketIdentity = line.split(",");
                String date = ticketIdentity[0].trim();
                String fine = ticketIdentity[1].trim();
                String violation = ticketIdentity[2].trim();
                String plateID = ticketIdentity[3].trim();
                String state = ticketIdentity[4].trim();
                String ticketNumber = ticketIdentity[5].trim();
                // If zip code is missing, it is assigned as ""
                String zipCode = "";
                if(ticketIdentity.length > 6) {
                    zipCode = ticketIdentity[6].trim();
                    //take only first five digit as zipcode
                    if(zipCode.length() > 5) {
                    	zipCode = zipCode.substring(0, 5);
                    }	
                }

                ticketData.add(new Ticket(ticketNumber, plateID, date, zipCode, violation, fine, state));

            }
            return ticketData;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        	System.out.println("File not found !");
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	System.out.println("File cannot be openned !");
            e.printStackTrace();
        }
        return ticketData;
    }
}