package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class DependencyInjectionApplication {
	
	@Autowired
	private NotificationManager notify;
	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionApplication.class, args);
	}
	
	@PostConstruct // used to execute a method automatically after your bean creation and dependency  injection 
	public void execute() {
		notify.showmessage();
	}

}
