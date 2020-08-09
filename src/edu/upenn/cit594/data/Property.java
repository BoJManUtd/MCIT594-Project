package edu.upenn.cit594.data;

public class Property {
	private String zipCode;
	private double marketValue;
	private double totalLivableArea;
	
	public Property(String zipCode, double marketValue, double totalLivableArea) {
		this.zipCode = zipCode;
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
	}

	public String getZipCode() {
		return zipCode;
	}

	public double getMarketValue() {
		return marketValue;
	}

	public double getTotalLivableArea() {
		return totalLivableArea;
	}
	
	
}
