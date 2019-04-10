 package com.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.KafkaCOntroler.KafkaProducer;
import com.KafkaCOntroler.MessageStorage;
import com.dbcontroler.CountriesRepository;
import com.dbcontroler.GamesRepository;
import com.dbcontroler.LeaguesRepository;
import com.model.Countries;
import com.model.Leagues;
import com.model.Standing;
import com.model.Games;

@Component
@RestController
public class RestApi {
	static RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	CountriesRepository countriesRepository; 
	
	@Autowired
	LeaguesRepository leaguesRepository; 
	
	@Autowired
	GamesRepository gamesRepository; 
	
	  @Autowired
	  KafkaProducer producer;
	  
	  @Autowired
	  MessageStorage storage;
	  
	  
	
	
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/countries/",  method = RequestMethod.GET)
	public ResponseEntity<List<Countries>> Countries() {
		
		Iterable<Countries> countries = countriesRepository.findAll();
		List<Countries> c = new ArrayList<Countries>();
		countries.forEach(e -> c.add(e));
		

		Collections.sort(c,new countryComparator());
		
		return new ResponseEntity<List<Countries>>(c, HttpStatus.OK);		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/leagues/",  method = RequestMethod.GET)
	public ResponseEntity<List<Leagues>> Leagues() {
		
		Iterable<Leagues> leagues = leaguesRepository.findAll();
		List<Leagues> c = new ArrayList<Leagues>();
		leagues.forEach(e -> c.add(e));
		
			
		return new ResponseEntity<List<Leagues>>(c, HttpStatus.OK);		
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value="/games/",  method = RequestMethod.GET)
	public ResponseEntity<List<Games>> Games() {
		
		Iterable<Games> leagues = gamesRepository.findAll();
		List<Games> c = new ArrayList<Games>();
		leagues.forEach(e -> c.add(e));
		
		return new ResponseEntity<List<Games>>(c, HttpStatus.OK);		
	}
	
	  @CrossOrigin(origins = "http://localhost:3000")
	  @RequestMapping(value="/consumer")
	  public String getAllRecievedMessage(){
	    String messages = storage.toString();
	    storage.clear();
	    
	    return messages;
	  }
	  
	  
	    @CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value="/league",  method = RequestMethod.GET)
		public ResponseEntity<List<Standing>> standing(@RequestParam("league_id") String league_id) {
			
			ResponseEntity<List<Standing>> responses = restTemplate.exchange(
					"https://apifootball.com/api/?action=get_standings&league_id="+league_id+"&APIkey=4e431f9c6e1d5e2241cba0e305f02ebf40d8da5a547c28092ce5d2da472739d7",
					HttpMethod.GET,
					  null,
					  new ParameterizedTypeReference<List<Standing>>(){});
			List<Standing> games = responses.getBody(); //Leagues list
			
			Collections.sort(games,new standComparator());
			
			return new ResponseEntity<List<Standing>>(games, HttpStatus.OK);			
		}
	    
	    @CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value="/country",  method = RequestMethod.GET)
		public ResponseEntity<List<Leagues>> leagues(@RequestParam("country_id") String country_id) {
			

			Iterable<Leagues> leagues = leaguesRepository.findAll();
			List<Leagues> c = new ArrayList<Leagues>();
			leagues.forEach(e -> {
				if(e.country_id.equals(country_id)){
					c.add(e);
				}
			});
			
			return new ResponseEntity<List<Leagues>>(c, HttpStatus.OK);			
		}
	    
	    @CrossOrigin(origins = "http://localhost:3000")
		@RequestMapping(value="/live/",  method = RequestMethod.GET)
		public ResponseEntity<List<Games>> live() {
			
			Iterable<Games> leagues = gamesRepository.findAll();
			List<Games> c = new ArrayList<Games>();
			leagues.forEach(e -> {
				if(e.match_live.equals("1")) {
					c.add(e);
				}
			});
			
			return new ResponseEntity<List<Games>>(c, HttpStatus.OK);		
		}

}
