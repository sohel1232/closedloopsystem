package com.card91.closedloopsystem;

//import com.card91.closedloopsystem.config.TwilioConfig;
import com.card91.closedloopsystem.config.TwilioConfig;
import com.card91.closedloopsystem.entity.User;
import com.card91.closedloopsystem.repository.UserRepository;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class ClosedloopsystemApplication {

	@Autowired
	private TwilioConfig twilioConfig;

	@Autowired
	private UserRepository userRepository;


	@PostConstruct
	public void setup() {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
	}

	public static void main(String[] args) {
		SpringApplication.run(ClosedloopsystemApplication.class, args);
	}

}
