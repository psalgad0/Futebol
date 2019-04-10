package com.dbcontroler;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.model.Countries;

@Repository
public interface CountriesRepository extends CrudRepository<Countries, String>{
	
}
