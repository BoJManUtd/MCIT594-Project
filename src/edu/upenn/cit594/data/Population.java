package edu.upenn.cit594.data;

public class Population {
	private String zipCode;
	private String size;
	
	public Population(String zipCode, String size) {
		this.zipCode = zipCode;
		this.size = size;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getSize() {
		return size;
	}
	
}
