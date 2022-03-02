package br.com.coelhovictor.springapibase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.coelhovictor.springapibase.services.DbService;

@SpringBootApplication
public class SpringApiBaseApplication implements CommandLineRunner {

	@Autowired
	private DbService dbService;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringApiBaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbService.instantiateTestDatabase();
	}

}
