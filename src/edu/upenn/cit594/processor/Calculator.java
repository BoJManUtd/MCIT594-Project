package edu.upenn.cit594.processor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.upenn.cit594.data.*;

public class Calculator {
	// Function executes if user enters 1
	public void getTotalPopulation(List<Population> pops) {
		int total = 0;
		for(Population pop: pops) {
			total += pop.getSize();
		}
		System.out.println(total);
	}
	
	// function executes if user enters 2
	public void getTotalFinesPerCapita(List<Ticket> tickets, List<Population> pops) {
		Set<String> allZipCodes = new HashSet<String>();
		for(Population pop: pops) {
			allZipCodes.add(pop.getZipCode());
		}
		Map<String, Double> totalFinesPerCapita = new HashMap<String, Double>();
		for(Ticket ticket: tickets) {
			String currZipCode = ticket.getZipCode();
			if(!allZipCodes.contains(currZipCode) || 
			   ticket.getState().trim().compareToIgnoreCase("PA") != 0){
				continue;
			}
			
			if(totalFinesPerCapita.containsKey(currZipCode)) {
				totalFinesPerCapita.put(currZipCode, totalFinesPerCapita.get(currZipCode) + ticket.getFine());
			}else {
				totalFinesPerCapita.put(currZipCode, ticket.getFine());
			}
		}
		
		List<String> zipCodesWithFines = new ArrayList<String> (totalFinesPerCapita.keySet());
		Collections.sort(zipCodesWithFines);
		for(String zipCode: zipCodesWithFines) {
			System.out.println(zipCode + " " + totalFinesPerCapita.get(zipCode));
		}
	}
}
