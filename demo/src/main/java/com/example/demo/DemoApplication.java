package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.api.RestApi;
import com.dbcontroler.MainController;


@SpringBootApplication
@ComponentScan({"com.dbcontroler", "com.api","com.KafkaCOntroler"})
@EnableJpaRepositories({"com.dbcontroler"})
@EntityScan({"com.model"})
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		MainController mainController = context.getBean(MainController.class);
		mainController.addCountries();
		mainController.addLeagues();
		mainController.addGames();
		
		RestApi restapi = context.getBean(RestApi.class);
	}

}
