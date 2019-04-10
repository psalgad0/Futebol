package com.api;

import java.util.Comparator;

import com.model.Standing;

public class standComparator implements Comparator<Standing>{
	 	@Override
		public int compare(Standing o1, Standing o2) {
		  int i1 = Integer.parseInt(o1.overall_league_position);
		  int i2 = Integer.parseInt(o2.overall_league_position);
		    return i1-i2;
		}
}