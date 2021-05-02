package com.company.UgonnaGuadalupeCapstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication //(exclude =  {SecurityAutoConfiguration.class})
@EnableResourceServer
public class UgonnaGuadalupeCapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(UgonnaGuadalupeCapstoneApplication.class, args);
	}

}
