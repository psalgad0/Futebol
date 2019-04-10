package com.api;

import java.util.Comparator;

import com.model.Countries;
import com.model.Standing;

public class countryComparator implements Comparator<Countries> {
	  @Override
	    public int compare(Countries o1, Countries o2) {
		       return o1.country_name.compareToIgnoreCase(o2.country_name);
	    }

}