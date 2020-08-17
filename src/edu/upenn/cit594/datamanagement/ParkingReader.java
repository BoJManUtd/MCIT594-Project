package edu.upenn.cit594.datamanagement;

import java.util.List;
import edu.upenn.cit594.data.Ticket;


public interface ParkingReader {
	public List<Ticket> getAllTickets();
	public String getFileName();
}
