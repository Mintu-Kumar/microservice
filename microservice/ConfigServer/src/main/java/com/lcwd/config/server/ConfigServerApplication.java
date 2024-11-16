package com.lcwd.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer  // it will read the all configuration from config server
public class ConfigServerApplication {


	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
