package com.model;

public class Leagues {
	String country_id;
	String country_name;
	String league_id;
	String league_name;
	
	public String getId() 
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
	}
	
}
