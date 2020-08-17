package edu.upenn.cit594;

import java.util.List;

import edu.upenn.cit594.data.*;
import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.UserInterface;

public class Main {
	public static String logfileName; // used to pass in log file name.
	
	public static void main(String[] args) {
		
		if(args.length != 5) {
			System.out.println("Incorrect number of arguments !");
			System.exit(0);
		}
		
		ParkingReader parkingReader = null;
		logfileName = args[4];
		Logger l = Logger.getInstance();
		String argString = "";
		for(String arg: args) {
			argString += " ";
			argString += arg;
		}
		l.log(System.currentTimeMillis() + argString);
		
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
		Processor processor = new Processor(parkingReader, populationReader, propertyReader, l);
		UserInterface ui = new UserInterface(processor);
		ui.start();
	}
}
