package edu.upenn.cit594.processor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import edu.upenn.cit594.data.*;

public class Calculator {
	private int totalPopulation;
	private Map<String, Double> totalFinesPerCapita;
	private Map<String, Integer> averageMarketValue;
	private Map<String, Integer> averageTotalLivableArea;
	private Map<String, Integer> totalResidentialMarketValuePerCapita;
	private int averageMarketValueForZipCodeWithMostTickets;
	
	public Calculator() {
		this.totalPopulation = -1;
		this.totalFinesPerCapita = new HashMap<String, Double>();
		this.averageMarketValue = new HashMap<String, Integer>();
		this.averageTotalLivableArea = new HashMap<String, Integer>();
		this.totalResidentialMarketValuePerCapita = new HashMap<String, Integer>();
		this.averageMarketValueForZipCodeWithMostTickets = -1;
	}
	
	// Function executes if user enters 1
	public void getOutput1(List<Population> pops) {
		if(this.totalPopulation < 0) {
			int total = 0;
			for(Population pop: pops) {
				try{
					int currSize = Integer.parseInt(pop.getSize());
					total += currSize;
				}catch(NumberFormatException e) {
					continue;
			    }catch(NullPointerException e) {
			    	continue;
			    }
			}
			this.totalPopulation = total;
		}
		System.out.println(this.totalPopulation);
	}
	
	
	// function executes if user enters 2
	// Does not print truncated number correctly
	public void getOutput2(List<Ticket> tickets, List<Population> pops) {
		if(this.totalFinesPerCapita.isEmpty()) {
			// totalFinesPerCapita
			// Map -- zipCode: population
			Map<String, Integer> allZipCodesPop = new HashMap<String, Integer>();
			for(Population pop: pops) {
				String currZipCode = pop.getZipCode();
				try{
					int currSize = Integer.parseInt(pop.getSize());
					allZipCodesPop.put(currZipCode, currSize);
				}catch(NumberFormatException e) {
					continue;
			    }catch(NullPointerException e) {
			    	continue;
			    }
				
			}
			
			// Map -- zipCode: total fines
			Map<String, Double> totalFinesPerZipCode = new HashMap<String, Double>();
			for(Ticket ticket: tickets) {
				String currZipCode = ticket.getZipCode();
				if(!allZipCodesPop.containsKey(currZipCode) || 
				   ticket.getState().trim().compareToIgnoreCase("PA") != 0){
					continue;
				}
				try{
					double currFine = Double.parseDouble(ticket.getFine());
					if(totalFinesPerZipCode.containsKey(currZipCode)) {
						totalFinesPerZipCode.put(currZipCode, totalFinesPerZipCode.get(currZipCode) + currFine);
					}else {
						totalFinesPerZipCode.put(currZipCode, currFine);
					}
				}catch(NumberFormatException e) {
					continue;
			    }catch(NullPointerException e) {
			    	continue;
			    }
			}
			
			// Map -- zipCode: total fines per capita
			this.totalFinesPerCapita = new HashMap<String, Double>();
			for(String zipCode: totalFinesPerZipCode.keySet()) {
				double value = totalFinesPerZipCode.get(zipCode) / allZipCodesPop.get(zipCode);
				double valueFormatted = Math.floor(value * 10000) / 10000;
				totalFinesPerCapita.put(zipCode, valueFormatted);
			}
		}
		
		List<String> zipCodesWithFines = new ArrayList<String> (this.totalFinesPerCapita.keySet());
		Collections.sort(zipCodesWithFines);
		for(String zipCode: zipCodesWithFines) {
			System.out.println(zipCode + " " + this.totalFinesPerCapita.get(zipCode));
		}
	}
	
	// helper function
	public int getAverageValue(List<Property> properties, String targetZipCode, AttributeValueGetter getter) {
		int count = 0;
		double value = 0.0;
		for(Property property: properties) {
			if(property.getZipCode().compareTo(targetZipCode) == 0) {
				try{
					double currValue = Double.parseDouble(getter.getAttributeValue(property));
					count += 1;
					value += currValue;
				}catch(NumberFormatException e) {
					continue;
			    }catch(NullPointerException e) {
			    	continue;
			    }
			}
		}
		if(count == 0) {
			return 0;
		}else {
			return (int) Math.floor(value / count);
		}
	}
	
	// function executes if user enters 3
	public void getOutput3(List<Property> properties, String targetZipCode) {
		if(!this.averageMarketValue.containsKey(targetZipCode)) {
			this.averageMarketValue.put(targetZipCode, getAverageValue(properties, targetZipCode, new MarketValueGetter()));
		}
		System.out.println(this.averageMarketValue.get(targetZipCode));
	}
	
	// function executes if user enters 4
	public void getOutput4(List<Property> properties, String targetZipCode) {
		if(!this.averageTotalLivableArea.containsKey(targetZipCode)) {
			this.averageTotalLivableArea.put(targetZipCode, getAverageValue(properties, targetZipCode, new TotalLivableAreaGetter()));
		}
		System.out.println(this.averageTotalLivableArea.get(targetZipCode));
	}
	
	// function executes if user enters 5
	public void getOutput5(List<Property> properties, List<Population> pops, String targetZipCode) {
		if(!this.totalResidentialMarketValuePerCapita.containsKey(targetZipCode)) {
			Map<String, Integer> allZipCodesPop = new HashMap<String, Integer>();
			for(Population pop: pops) {
				try{
					int currSize = Integer.parseInt(pop.getSize());
					allZipCodesPop.put(pop.getZipCode(), currSize);
				}catch(NumberFormatException e) {
					continue;
			    }catch(NullPointerException e) {
			    	continue;
			    }
			}
			this.totalResidentialMarketValuePerCapita = new HashMap<String, Integer>();
			
			if(!allZipCodesPop.containsKey(targetZipCode) || allZipCodesPop.get(targetZipCode) == 0) {
				this.totalResidentialMarketValuePerCapita.put(targetZipCode, 0);
			}else {
				double totalValue = 0.0;
				int totalPop = allZipCodesPop.get(targetZipCode);
				for(Property property: properties) {
					if(property.getZipCode().compareTo(targetZipCode) == 0) {
						try{
							double currMarketValue = Double.parseDouble(property.getMarketValue());
							totalValue += currMarketValue;
						}catch(NumberFormatException e) {
							continue;
					    }catch(NullPointerException e) {
					    	continue;
					    }
					}
				}
				this.totalResidentialMarketValuePerCapita.put(targetZipCode, (int) Math.floor(totalValue / totalPop));
			}
		}
		System.out.println(this.totalResidentialMarketValuePerCapita.get(targetZipCode));
	}
	
	/**
	 *  function executes if user enters 6
	 *  Intent: for the ZIP code with most parking tickets, what is the average market value
	 *  of its properties ?
	 */
	public void getOutput6(List<Ticket> tickets, List<Property> properties) {
		if(this.averageMarketValueForZipCodeWithMostTickets < 0) {
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
			
			this.averageMarketValueForZipCodeWithMostTickets = getAverageValue(properties, currZipCodeWithMaxTickets, 
			     															   new MarketValueGetter());
		}
		System.out.println(this.averageMarketValueForZipCodeWithMostTickets);
	}
}
