package edu.upenn.cit594.datamanagement;

import java.util.List;
import edu.upenn.cit594.data.Ticket;
import edu.upenn.cit594.logging.Logger;


public interface ParkingReader {
	public List<Ticket> getAllTickets();
	public String getFileName();
}
