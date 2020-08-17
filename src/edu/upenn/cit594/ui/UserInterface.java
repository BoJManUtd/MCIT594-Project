package edu.upenn.cit594.ui;

import java.util.Scanner;

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
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in); 
        boolean cond = true;
        
        while(cond) {
        	System.out.println("Please enter a number between 0 and 6:");
        	String s = in.nextLine();
            try {
            	int a = Integer.parseInt(s);
            	if(a == 0) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		cond = false;
            		System.exit(0);
            	}else if(a == 1) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		calculator.getOutput1(processor.getPopulations());
            	}else if(a == 2) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		calculator.getOutput2(processor.getTickets(),
              				              processor.getPopulations());
            	}else if(a == 3) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		System.out.println("Please enter a ZIP code:");
            		String zipCode = in.nextLine();
            		processor.getLogger().log(System.currentTimeMillis() + " " + zipCode);
            		calculator.getOutput3(processor.getProperties(), zipCode);
            	}else if(a == 4) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		System.out.println("Please enter a ZIP code:");
            		String zipCode = in.nextLine();
            		processor.getLogger().log(System.currentTimeMillis() + " " + zipCode);
            		calculator.getOutput4(processor.getProperties(), zipCode);
            	}else if(a == 5) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		System.out.println("Please enter a ZIP code:");
            		String zipCode = in.nextLine();
            		processor.getLogger().log(System.currentTimeMillis() + " " + zipCode);
            		calculator.getOutput5(processor.getProperties(), processor.getPopulations(),
        				                                           zipCode);
            	}else if(a == 6) {
            		processor.getLogger().log(System.currentTimeMillis() + " " + s);
            		calculator.getOutput6(processor.getTickets(), processor.getProperties());
            	}else {
            		System.out.println("Number is not between 0 and 6 !");
            		continue;
            	}
            }catch(Exception e) {
            	System.out.println("Wrong input format !");
            	continue;
            }
        }
	}
	
	
}
