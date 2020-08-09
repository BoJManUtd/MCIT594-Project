package edu.upenn.cit594.processor;

import edu.upenn.cit594.data.Property;

public class MarketValueGetter implements AttributeValueGetter{

	@Override
	public double getAttributeValue(Property property) {
		// TODO Auto-generated method stub
		return property.getMarketValue();
	}

}
