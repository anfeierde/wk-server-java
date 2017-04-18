package com.cz1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class WkServerApplication extends SpringBootServletInitializer {

	// run with war
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WkServerApplication.class);
	}

	// run with jar
	public static void main(String[] args) throws Exception {
		SpringApplication.run(WkServerApplication.class, args);
	}
}
