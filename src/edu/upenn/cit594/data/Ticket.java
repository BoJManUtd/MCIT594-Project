package edu.upenn.cit594.data;

public class Ticket {
	private int ticketNumber;
	private String plateID;
	private String date;
	private String zipCode;
	private String violation;
	private double fine;
	private String state;
	
	public Ticket(int ticketNumber, 
			      String plateID, 
			      String date, 
			      String zipCode, 
			      String violation,
			      double fine,
			      String state) {
		this.ticketNumber = ticketNumber;
		this.plateID = plateID;
		this.date = date;
		this.zipCode = zipCode;
		this.violation = violation;
		this.fine = fine;
		this.state = state;
	}

	public int getTicketNumber() {
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

	public double getFine() {
		return fine;
	}

	public String getState() {
		return state;
	}
	
}
