package com.dbcontroler;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.KafkaCOntroler.KafkaProducer;
import com.client.CallRestService;
import com.model.Countries;
import com.model.Games;
import com.model.Leagues;

@Component
@ComponentScan({"com.client"})
@EnableScheduling
public class MainController{
	
	
	
	@Autowired
	CallRestService restService;
	
	@Autowired
	CountriesRepository countriesRepository; 
	
	@Autowired
	LeaguesRepository leaguesRepository; 
	
	@Autowired
	GamesRepository gamesRepository;
	
	@Autowired
	KafkaProducer producer;
	  
	

	public String producers(String data){
		    producer.send(data);
		    
		    return "Done";
	}
		  
	public void addCountries () {
		List<Countries> countries = restService.GetCountries();
		for(int i=0; i<countries.size();i++) {
			if(countries.get(i)!=null) {
				countriesRepository.save(countries.get(i));
			}
		}
		producers("Countries added");
		System.out.println("Countries added");

	}
	
	public void addLeagues() {
		List<Leagues> leagues = restService.GetLeagues();
		for(int i=0; i<leagues.size();i++) {
			if(leagues.get(i)!=null) {
				leaguesRepository.save(leagues.get(i));
			}	
		}
		producers("Leagues added");
		System.out.println("Leagues added");
	}
	
	@Scheduled(fixedRate=60000)
	public void addGames() {
		List<Games> games= restService.GetGames();
		List<Games> gm = (List<Games>) gamesRepository.findAll();
		for(int i=0; i<games.size();i++) {
			if(games.get(i)!=null) {
				
				//if(gm.size()!=games.size()) {
					gamesRepository.save(games.get(i));
				//}
				/*
				else {
					for(int j = 0; j < gm.size(); j++) {
						if(gm.get(j).match_id.equals(games.get(i).match_id)){
							Games ga = gamesRepository.findById(games.get(i).id).get();
							ga.setMatch_awayteam_score(games.get(i).match_awayteam_score);
							ga.setMatch_hometeam_score(games.get(i).match_hometeam_score);
							ga.setMatch_status(games.get(i).match_status);
							gamesRepository.save(ga);
							System.out.print("ola");
							break;
						}
					}
					*/
				}
			}	
		
		producers("Games added");
		System.out.println("Games added");
	}
	
	
		
}
