package com.shortenMe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class shortenMeCOntroller {
	
	Logger logger = LoggerFactory.getLogger(shortenMeCOntroller.class);
	
	@GetMapping("/string")
	  String all() {
	    return "Hello";
	  }

}
