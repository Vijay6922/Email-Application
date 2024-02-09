package com.demoApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demoApplication.entity.emailEntity;
import com.demoApplication.service.SimpleEmail;
import com.demoApplication.service.ScheduledExample;
import com.demoApplication.service.mailwithFile;

@RestController
public class EmailController {
	@Autowired
	SimpleEmail demoEmail;

	@Autowired
	mailwithFile withfile;

	@Autowired
	ScheduledExample scheduled;

	@PostMapping("/sendmail")
	public ResponseEntity<String> sendMail(@RequestBody emailEntity request) {
		System.out.println(request);
		// autowired demoemail class from services and using methods in it
		this.demoEmail.sendEmail(request.getMessage(), request.getSubject(), request.getTo());
		return ResponseEntity.ok("Done");
	}

	@Scheduled(cron = "0 57 11 * * ?") // Execute for given time
	@PostMapping("/sendmailwithfile")
	public ResponseEntity<String> sendMailWithFile() {
		System.out.println("Done");
		// autowired email with file class from services and using methods in it
		this.withfile.sendFile();
		return ResponseEntity.ok("done");
	}

	// @Scheduled(cron = "0 6 11 * * ?")
	// @Scheduled(fixedRate=5000)
	@PostMapping("/sendmailtime")
	public ResponseEntity<String> Example() {
		System.out.println("done");
		// autowired email with file class from services and using methods in it
		this.scheduled.sendEmail();
		return ResponseEntity.ok("Done");
	}

}
