package com.client;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.model.Countries;
import com.model.Games;
import com.model.Leagues;

@Component
@RestController
@EnableScheduling
public class CallRestService implements CommandLineRunner {
	
	static RestTemplate restTemplate = new RestTemplate();
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/country/",method = RequestMethod.GET)
	public ResponseEntity<List<Countries>> GetCountries() {
		//Get all Countries
		ResponseEntity<List<Countries>> response = restTemplate.exchange(
				  "https://apifootball.com/api/?action=get_countries&APIkey=90cda1905d8cbcdeece01e6eafb321cad1dbb7338608f88bcf85d010b014c3a0",
				  HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Countries>>(){});
		List<Countries> countries = response.getBody(); //Countries List
		

		return new ResponseEntity<List<Countries>>(countries, HttpStatus.OK);
	}
	
	
//------------------------------------------------------------------------------------------
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/leagues/",  method = RequestMethod.GET)
	public ResponseEntity<List<Leagues>> GetLeagues() {
		// Get All Leagues
		ResponseEntity<List<Leagues>> responses = restTemplate.exchange(
				"https://apifootball.com/api/?action=get_leagues&APIkey=90cda1905d8cbcdeece01e6eafb321cad1dbb7338608f88bcf85d010b014c3a0",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Leagues>>(){});
		List<Leagues> Leagues = responses.getBody(); //Leagues list
		
		return new ResponseEntity<List<Leagues>>(Leagues, HttpStatus.OK);	
	}
//---------------------------------------------------------------------------------
	@CrossOrigin(origins = "http://localhost:3000")
	@Scheduled(fixedRate=60000)
	@RequestMapping(value="/live/",  method = RequestMethod.GET)
	public ResponseEntity<List<Games>> LiveScores() {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String data = dateFormat.format(date);
		ResponseEntity<List<Games>> responses = restTemplate.exchange(
				"https://apifootball.com/api/?action=get_events&match_live=1&from="+data+"&to="+data+"&APIkey=90cda1905d8cbcdeece01e6eafb321cad1dbb7338608f88bcf85d010b014c3a0",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Games>>(){});
		List<Games> games = responses.getBody(); //Leagues list
		
		return new ResponseEntity<List<Games>>(games, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/game",  method = RequestMethod.GET)
	public ResponseEntity<com.model.Games> Game(@RequestParam("match_id") String match_id) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(match_id);
		String data = dateFormat.format(date);
		ResponseEntity<List<Games>> responses = restTemplate.exchange(
				"https://apifootball.com/api/?action=get_events&match_id="+match_id+"&APIkey=90cda1905d8cbcdeece01e6eafb321cad1dbb7338608f88bcf85d010b014c3a0",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Games>>(){});
		List<Games> games = responses.getBody(); //Leagues list
		Games game=null;
		for(int i=0; i<games.size();i++) {
			if(games.get(i).match_id.equals(match_id)) {
				game=games.get(i);
				break;
			}
		}
		return new ResponseEntity<Games>(game, HttpStatus.OK);
	}
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/games",  method = RequestMethod.GET)
	public ResponseEntity<List<com.model.Games>> Games(@RequestParam("data") String data) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ResponseEntity<List<Games>> responses = restTemplate.exchange(
				"https://apifootball.com/api/?action=get_events&match_live=0&from="+data+"&to="+data+"&APIkey=90cda1905d8cbcdeece01e6eafb321cad1dbb7338608f88bcf85d010b014c3a0",
				HttpMethod.GET,
				  null,
				  new ParameterizedTypeReference<List<Games>>(){});
		List<Games> games = responses.getBody(); //Leagues list
		
		int i=0;
		while(i<games.size()) {
			if(!games.get(i).match_status.equals("FT")){
				games.remove(i);
				i--;
			}
			i++;
		}
		return new ResponseEntity<List<Games>>(games, HttpStatus.OK);
	}
	
	
	
	@Override
	public void run(String... args) throws Exception {
		//GetLeagues();
		//GetCountries();
		LiveScores();
		
	}
}
