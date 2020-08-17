package edu.upenn.cit594.data;

public class Ticket {
	private String ticketNumber;
	private String plateID;
	private String date;
	private String zipCode;
	private String violation;
	private String fine;
	private String state;
	
	public Ticket(String ticketNumber, 
			      String plateID, 
			      String date, 
			      String zipCode, 
			      String violation,
			      String fine,
			      String state) {
		this.ticketNumber = ticketNumber;
		this.plateID = plateID;
		this.date = date;
		this.zipCode = zipCode;
		this.violation = violation;
		this.fine = fine;
		this.state = state;
	}

	@Override
	public String toString() {
		return this.getTicketNumber() + " " + this.getPlateID() + " " + this.getFine() + " " + this.getZipCode();
	}
	
	
	public String getTicketNumber() {
		return ticketNumber;
	}

	public String getPlateID() {
		return plateID;
	}

	public String getDate() {
		return date;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getViolation() {
		return violation;
	}

	public String getFine() {
		return fine;
	}

	public String getState() {
		return state;
	}
	
}
