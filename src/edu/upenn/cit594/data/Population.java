package edu.upenn.cit594.data;

public class Population {
	private String zipCode;
	private int size;
	
	public Population(String zipCode, int size) {
		this.zipCode = zipCode;
		this.size = size;
	}

	public String getZipCode() {
		return zipCode;
	}

	public int getSize() {
		return size;
	}
	
}
