package br.com.coelhovictor.springapibase.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.coelhovictor.springapibase.services.DbService;

@Configuration
@Profile("prod")
public class ProdConfig {

	@Autowired
	private DbService dbService;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.createOrUpdateRootUser();
		return true;
	}

}