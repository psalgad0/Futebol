package com.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class Countries {
	String country_id;
	String country_name;
	
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

}
