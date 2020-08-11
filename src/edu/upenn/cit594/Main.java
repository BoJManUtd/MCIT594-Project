package edu.upenn.cit594;

import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {
	public static void main(String[] args) {
		/***
		 * Check validity of input
		 */
		if(args.length != 5) {
			System.out.println("Incorrect number of arguments !");
			System.exit(0);
		}
		
		ParkingReader parkingReader = null;
		if(args[0].compareTo("csv") == 0) {
			parkingReader = new ParkingReaderCSV(args[1]);
		}else if(args[0].compareTo("json") == 0){
			parkingReader = new ParkingReaderJSON(args[1]);
		}else {
			System.out.println("First argument should be either 'csv' or 'json' !");
			System.exit(1);
		}
		
		PropertyReader propertyReader = new PropertyReader(args[2]);
		PopulationReader populationReader = new PopulationReader(args[3]);
		
		Processor processor = new Processor(parkingReader, populationReader, propertyReader);
		
		UserInterface ui = new UserInterface(processor);
		ui.start();
	}
}
