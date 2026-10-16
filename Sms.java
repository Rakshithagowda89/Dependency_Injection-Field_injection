package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Sms implements Message{

	public void sendmessage() {
		System.out.println("This is a SMS Service");
		
	}

}
