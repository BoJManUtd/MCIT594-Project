package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.data.Ticket;

public class PropertyReader {
    protected String filename;

    public PropertyReader(String fileName) {
        this.filename = fileName;
    }

    public List<Property> getAllProperties() {
        List<Property> propertyData = new ArrayList<Property>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            String[] firstRow = line.split(",");
            Integer zip_codeLocation = 999;
            Integer total_livable_areaLocation = 999;
            Integer market_valueLocation = 999;
            
            //Finding the location for market_value, total_livable_area, and zip_code fields.
            for (int i = 0; i < firstRow.length; i++) {

                if (firstRow[i].equalsIgnoreCase("zip_code")) {
                    zip_codeLocation = i;
                    break;
                }
                if (firstRow[i].equalsIgnoreCase("total_livable_area")) {
                    total_livable_areaLocation = i;
                    break;

                }
                if (firstRow[i].equalsIgnoreCase("market_value")) {
                    market_valueLocation = i;
                    break;

                }
            }
            
            List<String> rowArray = new ArrayList<String>();
            int begin = 0;
            boolean flagQuotes = false;
         
            // Act as a parser to parse the one with double quote separtely.
            
            while ((line = br.readLine()) != null) {
                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == '\"') {
                        flagQuotes = !flagQuotes;
                    }
                    boolean atLastPosition = (i == line.length()-1);
                    if(atLastPosition) {
                        rowArray.add(line.substring(begin));
                        
                    }
                    else if(line.charAt(i) == ',' && !flagQuotes) {
                        rowArray.add(line.substring(begin,i));
                        begin = i+1;
                        
                    }
                    
                    
                }
                // process the line.
              

                Double total_livable_area = Double.parseDouble(rowArray.get(total_livable_areaLocation).trim());
                Double market_value = Double.parseDouble(rowArray.get(market_valueLocation).trim());

                String zip_code = rowArray.get(zip_codeLocation).trim().substring(0, 5);// take only first five digit
                                                                                          // as zipcode
                propertyData.add(new Property(zip_code, market_value, total_livable_area));

            }
            return propertyData;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return propertyData;
        
        

    }
}
