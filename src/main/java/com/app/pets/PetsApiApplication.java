package com.app.pets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class PetsApiApplication extends SpringBootServletInitializer    {
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PetsApiApplication.class);

	}
	public static void main(String[] args) {
		SpringApplication.run(PetsApiApplication.class, args);
	}
	
	/*@Bean
	public PasswordEncoder getPasswordEncoder( ) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}*/

}
