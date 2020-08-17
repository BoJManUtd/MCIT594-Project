package edu.upenn.cit594.processor;

import java.util.List;

import edu.upenn.cit594.data.*;
import edu.upenn.cit594.datamanagement.ParkingReader;
import edu.upenn.cit594.datamanagement.PopulationReader;
import edu.upenn.cit594.datamanagement.PropertyReader;
import edu.upenn.cit594.logging.Logger;

public class Processor {
	private ParkingReader parkingReader;
	private PopulationReader populationReader;
	private PropertyReader propertyReader;
	private Logger logger;
	private List<Ticket> tickets;
	private List<Population> populations;
	private List<Property> properties;
	
	public Processor(ParkingReader parkingReader, PopulationReader populationReader,
			         PropertyReader propertyReader, Logger logger) {
		this.parkingReader = parkingReader;
		this.populationReader = populationReader;
		this.propertyReader = propertyReader;
		this.logger = logger;
		this.readData();
	}

	private void readData(){
		this.tickets = this.parkingReader.getAllTickets();
		this.logger.log(System.currentTimeMillis() + this.parkingReader.getFileName());
		this.populations = this.populationReader.getAllPopulations();
		this.logger.log(System.currentTimeMillis() + this.populationReader.getFileName());
		this.properties = this.propertyReader.getAllProperties();
		this.logger.log(System.currentTimeMillis() + this.propertyReader.getFileName());
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

	public List<Ticket> getTickets() {
		return tickets;
	}

	public List<Population> getPopulations() {
		return populations;
	}

	public List<Property> getProperties() {
		return properties;
	}
	
	public Logger getLogger() {
		return this.logger;
	}
}
