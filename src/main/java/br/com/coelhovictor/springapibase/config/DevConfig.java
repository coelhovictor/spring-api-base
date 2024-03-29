package br.com.coelhovictor.springapibase.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.coelhovictor.springapibase.services.DbService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DbService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.createOrUpdateRootUser();
		
		if(!strategy.equals("create"))
			return false;

		dbService.instantiateTestDatabase();
		return true;
	}

}