package com.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Games {
	public String match_hometeam_name;
	public String match_awayteam_name;
	public String match_hometeam_score;
	public String match_awayteam_score;
	public String match_status;
	public String league_name;
	public String country_name;
	public String match_time;
	public String match_id;
	public String match_date;

	
}
