package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Ticket;

public class ParkingReaderCSV implements ParkingReader {
    protected String filename;

    public ParkingReaderCSV(String fileName) {
        this.filename = fileName;
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> ticketData = new ArrayList<Ticket>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                // process the line.
                String[] ticketIdentity = line.split(",");

                Integer ticketNumber = Integer.parseInt(ticketIdentity[0].trim());
                String plateID = ticketIdentity[1].trim();
                String date = ticketIdentity[2];
                String zipCode = ticketIdentity[3].trim().substring(0, 5); //take only first five digit as zipcode
                String violation = ticketIdentity[4].trim();
                Double fine = Double.parseDouble(ticketIdentity[5].trim());
                String state = ticketIdentity[6].trim();

                ticketData.add(new Ticket(ticketNumber, plateID, date, zipCode, violation, fine, state));

            }
            return ticketData;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ticketData;

    }

}
