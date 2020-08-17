package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Property;

public class PropertyReader {
    protected String fileName;
    private int zipCodeLocation;
    private int totalLivableAreaLocation;
    private int marketValueLocation;

    public PropertyReader(String fileName) {
        this.fileName = fileName;
        this.zipCodeLocation = 999;
        this.totalLivableAreaLocation = 999;
        this.marketValueLocation = 999;
    }
    
    public String getFileName() {
    	return this.fileName;
    }
    
    //Finding the location for market_value, total_livable_area, and zip_code fields.
    public void findLocation(String[] firstRow) {
        for(int i = 0; i < firstRow.length; i++) {
        	if (firstRow[i].equalsIgnoreCase("zip_code")) {
            	this.zipCodeLocation = i;
                continue;
            }
            if (firstRow[i].equalsIgnoreCase("total_livable_area")) {
            	this.totalLivableAreaLocation = i;
                continue;
            }
            if (firstRow[i].equalsIgnoreCase("market_value")) {
            	this.marketValueLocation = i;
                continue;
            }
        }
    }
    
    public Property getPropertyFromLine(String line) {
    	String totalLivableArea = "";
    	String marketValue = "";
    	String zipCode = "";
    	
    	int numOfQuotationMark = 0;
    	int start = 0;
    	int end = 0;
    	int correctIndex = 0;
    	String[] fields = line.split(",");
    	// System.out.println(Arrays.toString(fields));
    	for(String s: fields) {
    		int count = 0;
            for(int j = 0; j < s.length(); j++) {    
                if(s.charAt(j) == '"')    
                	count += 1;  
            }
            numOfQuotationMark += count;
    		if(numOfQuotationMark % 2 == 0) {
    			String targetString = "";
    			for(int k = start; k <= end; k++) {
    				targetString += fields[k];
    			}
    			if(correctIndex == this.zipCodeLocation) {
    				zipCode = targetString;
    			}else if(correctIndex == this.marketValueLocation) {
    				marketValue = targetString;
    			}else if(correctIndex == this.totalLivableAreaLocation) {
    				totalLivableArea = targetString;
    			}
				start = end + 1;
				end = start;
				correctIndex += 1;
    		}else {
    			end += 1;
    		}
    	}
    	if(zipCode.length() > 5) {
    		zipCode = zipCode.substring(0, 5);
    	}
		return new Property(zipCode, marketValue, totalLivableArea);
    }
    public List<Property> getAllProperties() {
        List<Property> propertyData = new ArrayList<Property>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            String[] firstRow = line.split(",");
            findLocation(firstRow);
           
            // Act as a parser to parse the one with double quote separtely.
   
            while ((line = br.readLine()) != null) {
            	propertyData.add(getPropertyFromLine(line));
            }
            return propertyData;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("File not found !");
        	e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        	System.out.println("File cannot be openned !");
            e.printStackTrace();
        }
        return propertyData;
    }
}