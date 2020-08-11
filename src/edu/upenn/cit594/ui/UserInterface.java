package edu.upenn.cit594.ui;

import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Ticket;
import edu.upenn.cit594.processor.Calculator;
import edu.upenn.cit594.processor.Processor;

public class UserInterface {
	private Calculator calculator;
	private Processor processor;
	
	public UserInterface(Processor processor) {
		this.calculator = new Calculator();
		this.processor = processor;
	}
	
	
	public void start() {
        Scanner in = new Scanner(System.in); 
        boolean cond = true;
        
        while(cond) {
        	System.out.println("Please enter a number between 0 and 6:");
        	String s = in.nextLine();
            try {
            	int a = Integer.parseInt(s);
            	if(a == 0) {
            		System.exit(0);
            	}else if(a == 1) {
            		calculator.getTotalPopulation(processor.getPopulationReader().getAllPopulations());
            	}else if(a == 2) {
            		calculator.getTotalFinesPerCapita(processor.getParkingReader().getAllTickets(),
            				                          processor.getPopulationReader().getAllPopulations());
            	}else if(a == 3) {
            		System.out.println("Please enter a ZIP code:");
            		String zipCode = in.nextLine();
            		calculator.getAverageMarketValue(processor.getPropertyReader().getAllProperties(), zipCode);
            	}else if(a == 4) {
            		System.out.println("Please enter a ZIP code:");
            		String zipCode = in.nextLine();
            		calculator.getAverageTotalLivableArea(processor.getPropertyReader().getAllProperties(), zipCode);
            	}else if(a == 5) {
            		System.out.println("Please enter a ZIP code:");
            		String zipCode = in.nextLine();
        		calculator.getTotalResidentialMarketValuePerCapita(processor.getPropertyReader().getAllProperties(), 
        				                                           processor.getPopulationReader().getAllPopulations(),
        				                                           zipCode);
            	}else if(a == 6) {
            		calculator.getAverageMarketValueForAreaWithMostTickets(processor.getParkingReader().getAllTickets(), 
            				                                               processor.getPropertyReader().getAllProperties());
            	}else {
            		System.out.println("Number is not between 0 and 6 !");
            		continue;
            	}
            }catch(Exception e) {
            	System.out.println("Wrong format !");
            	continue;
            }
        }
        

	}
	
	
}
