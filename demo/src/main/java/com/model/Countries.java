package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="countries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Countries {
	
	
	@Id
	@GeneratedValue
	public int id;
	public String country_id;
	public String country_name;
	
	
	
	/*
	
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
	}*/

}
