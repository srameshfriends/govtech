package com.govtech.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.govtech.project", "com.govtech.project.*"})
public class RestaurantBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantBookingApplication.class, args);
	}

}
