package com.agilesolutions.spring.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author u24279
 *
 */
@RestController
@RequestMapping("/api")
public class LdapController {


	private static final Logger logger = LoggerFactory.getLogger(LdapController.class);

	/**
	 * GET /api/version
	 *
	 * @return default response (status code 200)
	 */
	public ResponseEntity<String> showVersion() {

		logger.info("Running version {} of Demo application");

		return new ResponseEntity<String>("", HttpStatus.NOT_IMPLEMENTED);
	}

}