package org.power.action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ActionListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActionListApplication.class, args);
	}
}
