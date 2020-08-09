package edu.upenn.cit594.processor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	// Does not print truncated number correctly
	public void getTotalFinesPerCapita(List<Ticket> tickets, List<Population> pops) {
		Map<String, Integer> allZipCodesPop = new HashMap<String, Integer>();
		for(Population pop: pops) {
			allZipCodesPop.put(pop.getZipCode(), pop.getSize());
		}
		
		Map<String, Double> totalFinesPerZipCode = new HashMap<String, Double>();
		for(Ticket ticket: tickets) {
			String currZipCode = ticket.getZipCode();
			if(!allZipCodesPop.containsKey(currZipCode) || 
			   ticket.getState().trim().compareToIgnoreCase("PA") != 0){
				continue;
			}
			
			if(totalFinesPerZipCode.containsKey(currZipCode)) {
				totalFinesPerZipCode.put(currZipCode, totalFinesPerZipCode.get(currZipCode) + ticket.getFine());
			}else {
				totalFinesPerZipCode.put(currZipCode, ticket.getFine());
			}
		}
		
		Map<String, Double> totalFinesPerCapita = new HashMap<String, Double>();
		for(String zipCode: totalFinesPerZipCode.keySet()) {
			totalFinesPerCapita.put(zipCode, totalFinesPerZipCode.get(zipCode) / allZipCodesPop.get(zipCode));
		}
		
		List<String> zipCodesWithFines = new ArrayList<String> (totalFinesPerCapita.keySet());
		Collections.sort(zipCodesWithFines);
		for(String zipCode: zipCodesWithFines) {
			System.out.println(zipCode + " " + String.format("%.4f", totalFinesPerCapita.get(zipCode)));
		}
	}
	
	// helper function
	public void getAverageValue(List<Property> properties, String targetZipCode, AttributeValueGetter getter) {
		int count = 0;
		double value = 0.0;
		for(Property property: properties) {
			if(property.getZipCode().compareTo(targetZipCode) == 0) {
				count += 1;
				value += getter.getAttributeValue(property);
			}
		}
		if(count == 0) {
			System.out.println(0);
		}else {
			System.out.println((int) (value / count));
		}
	}
	
	// function executes if user enters 3
	public void getAverageMarketValue(List<Property> properties, String targetZipCode) {
		getAverageValue(properties, targetZipCode, new MarketValueGetter());
	}
	
	// function executes if user enters 4
	public void getAverageTotalLivableArea(List<Property> properties, String targetZipCode) {
		getAverageValue(properties, targetZipCode, new TotalLivableAreaGetter());
	}
	
	// function executes if user enters 5
	public void getTotalResidentialMarketValuePerCapita(List<Property> properties, List<Population> pops, String targetZipCode) {
		Map<String, Integer> allZipCodesPop = new HashMap<String, Integer>();
		for(Population pop: pops) {
			allZipCodesPop.put(pop.getZipCode(), pop.getSize());
		}
		
		if(!allZipCodesPop.containsKey(targetZipCode) || allZipCodesPop.get(targetZipCode) == 0) {
			System.out.println(0);
		}
		
		double totalValue = 0.0;
		for(Property property: properties) {
			if(property.getZipCode().compareTo(targetZipCode) == 0) {
				totalValue += property.getMarketValue();
			}
		}
		
		System.out.println((int) (totalValue / allZipCodesPop.get(targetZipCode)));
	}
	
	/**
	 *  function executes if user enters 6
	 *  Intent: for the ZIP code with most parking tickets, what is the average market value
	 *  of its properties ?
	 */
	public void getAverageMarketValueForAreaWithMostTickets(List<Ticket> tickets, List<Property> properties) {
		Map<String, Integer> allZipCodesTickets = new HashMap<String, Integer>();
		for(Ticket ticket: tickets) {
			String currZipCode = ticket.getZipCode();
			if(allZipCodesTickets.containsKey(currZipCode)) {
				allZipCodesTickets.put(currZipCode, allZipCodesTickets.get(currZipCode) + 1);
			}else {
				allZipCodesTickets.put(currZipCode, 1);
			}
		}
		
		String currZipCodeWithMaxTickets = "";
		int currMaxCount = 0;
		for(String zipCode: allZipCodesTickets.keySet()) {
			if(allZipCodesTickets.get(zipCode) > currMaxCount) {
				currZipCodeWithMaxTickets = zipCode;
				currMaxCount = allZipCodesTickets.get(zipCode);
			}
		}	
		getAverageTotalLivableArea(properties, currZipCodeWithMaxTickets);
	}
}
