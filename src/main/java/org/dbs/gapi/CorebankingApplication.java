package org.dbs.gapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class CorebankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CorebankingApplication.class, args);
	}

}
