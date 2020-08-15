package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Population;


public class PopulationReader {
    protected String filename;
	public PopulationReader(String fileName) {
	    this.filename = fileName;
	}
	
	public List<Population> getAllPopulations(){
	    List<Population> zipcodePop = new ArrayList<Population>();
	        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	            String line;

	            while ((line = br.readLine()) != null) {
	                // process the line.
	                String[] ticketIdentity = line.split("\\s+");

	                String Zipcode =  ticketIdentity[0].trim();
	                Integer Population = Integer.parseInt(ticketIdentity[1].trim());
	                

	                zipcodePop.add(new Population(Zipcode, Population));

	            }
	            return zipcodePop;
	        } catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return zipcodePop;

	    }
	}

