package com.dbcontroler;

import org.springframework.data.repository.CrudRepository;

import com.model.Leagues;

public interface LeaguesRepository extends CrudRepository<Leagues, Integer> {

}
