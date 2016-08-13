package com.heinrichs.email.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heinrichs.email.domain.User;
import com.heinrichs.email.services.EmailService;

@RestController
@SuppressWarnings("deprecation")
public class EmailController {

	
	@Autowired
	EmailService service;

	@RequestMapping(value = "/")
	public void sendEmail() {
		User user = new User();
		user.setName("Jordan");
		service.sendConfirmationEmail(user);
	}
//	@RequestMapping(value = "/pdf")
//	public void createPdf() {
//		service.createPdfFromHtml();
//	}
}
