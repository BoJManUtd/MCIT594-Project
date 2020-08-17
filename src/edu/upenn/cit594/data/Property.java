package edu.upenn.cit594.data;

public class Property {
	private String zipCode;
	private String marketValue;
	private String totalLivableArea;
	
	public Property(String zipCode, String marketValue, String totalLivableArea) {
		this.zipCode = zipCode;
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getMarketValue() {
		return marketValue;
	}

	public String getTotalLivableArea() {
		return totalLivableArea;
	}
	
	public String toString() {
		return "zipCode: " + this.getZipCode() + " " + "marketValue: " + this.getMarketValue() + " " + 
	           "totalLivableArea: " + this.getTotalLivableArea();
	}
}
