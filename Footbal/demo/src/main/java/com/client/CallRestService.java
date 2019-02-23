package com.client;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.model.Countries;
import com.model.Leagues;

@Component
public class CallRestService implements CommandLineRunner {

	private static void callRestService() 
	{
		RestTemplate restTemplate = new RestTemplate();
		String count= restTemplate.getForObject("https://apifootball.com/api/?action=get_countries&APIkey=3451414a6094c64e81cc19ab493dc311eecfae5aed4460620a0a6f4027d5e4fd", String.class);
		/*Countries countries[] = restTemplate.getForObject("https://apifootball.com/api/?action=get_countries&APIkey=3451414a6094c64e81cc19ab493dc311eecfae5aed4460620a0a6f4027d5e4fd", Countries[].class);
		*/
		System.out.println("countries: "+ count);
		
		ResponseEntity<List<Countries>> response = restTemplate.exchange(
				  "https://apifootball.com/api/?action=get_countries&APIkey=3451414a6094c64e81cc19ab493dc311eecfae5aed4460620a0a6f4027d5e4fd",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Countries>>(){});
		List<Countries> countries = response.getBody();
		for(int i = 0; i < countries.size(); i++)
			System.out.println("countries: "+ countries.get(i).getId());
		/////////////////////////////////////////
		//////////////////////////////////////////
		
		String lg = restTemplate.getForObject("https://apifootball.com/api/?action=get_leagues&APIkey=3451414a6094c64e81cc19ab493dc311eecfae5aed4460620a0a6f4027d5e4fd", String.class);
		
		System.out.println("countries: "+ lg);
		
		ResponseEntity<List<Leagues>> responses = restTemplate.exchange(
				"https://apifootball.com/api/?action=get_leagues&APIkey=3451414a6094c64e81cc19ab493dc311eecfae5aed4460620a0a6f4027d5e4fd",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Leagues>>(){});
		List<Leagues> Leagues = responses.getBody();
		for(int i = 0; i < Leagues.size(); i++)
			System.out.println("Leagues: "+ countries.get(i).getId());
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		callRestService();
		
	}
}
