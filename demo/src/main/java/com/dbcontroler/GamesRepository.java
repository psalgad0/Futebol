package com.dbcontroler;

import org.springframework.data.repository.CrudRepository;

import com.model.Games;

public interface GamesRepository extends CrudRepository<Games, Integer>{
	
}
