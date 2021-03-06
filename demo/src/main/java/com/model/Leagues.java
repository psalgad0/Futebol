package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Leagues {
	
	@Id
	@GeneratedValue
	public int id;
	public String country_id;
	public String country_name;
	public String league_id;
	public String league_name;
	
	/*public String getId() 
	{
		return country_id;
	}
	public void setId( String id) 
	{
		this.country_id = id;
	}
	public String getCountryName() 
	{
		return country_name;
	}
	public void setCountryName( String country_name) 
	{
		this.country_name = country_name;
	}
	public String getLeagueId() 
	{
		return league_id;
	}
	public void setLeagueId( String id) 
	{
		this.league_id = id;
	}
	public String getLeagueName() 
	{
		return league_name;
	}
	public void setLeagueName( String country_name) 
	{
		this.league_name = country_name;
	}*/
	
}
