package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {
	
	@Autowired // field injection pattern
	private Message message;
	
	@Autowired
	@Qualifier("sms")
	private Message message1;
	
	public void showmessage() 
	{
		message.sendmessage();
		message1.sendmessage();
	}
}
