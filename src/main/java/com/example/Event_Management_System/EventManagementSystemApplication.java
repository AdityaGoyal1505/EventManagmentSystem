package com.example.Event_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class EventManagementSystemApplication {

	public static void main(String[] args) {

        SpringApplication.run(EventManagementSystemApplication.class, args);

	}
}
