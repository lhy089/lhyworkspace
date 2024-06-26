package com.hyong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyong.util.MailService;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@GetMapping("/send")
	public void mailAuthentication() throws Exception {
		String verifyCodeId = mailService.sendCertificationMail("hyong0701@gmail.com");
		System.out.println(verifyCodeId);

	}

}
