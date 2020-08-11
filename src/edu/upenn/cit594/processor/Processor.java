package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;

public class Processor {
	private ParkingReader parkingReader;
	private PopulationReader populationReader;
	private PropertyReader propertyReader;
	
	public Processor(ParkingReader parkingReader, PopulationReader populationReader, PropertyReader propertyReader) {
		this.parkingReader = parkingReader;
		this.populationReader = populationReader;
		this.propertyReader = propertyReader;
	}

	public ParkingReader getParkingReader() {
		return parkingReader;
	}

	public PopulationReader getPopulationReader() {
		return populationReader;
	}

	public PropertyReader getPropertyReader() {
		return propertyReader;
	}
	
}
