package com.shortenMe.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shortenMe.service.ShortenMeService;

@RestController
@RequestMapping("/shortenMe")
public class ShortenMeController {

	Logger logger = LoggerFactory.getLogger(ShortenMeController.class);

	@Autowired
	ShortenMeService shortenMeService;

	@GetMapping("/isLive")
	public String all() {
		return "Yups";
	}

	@GetMapping("/shortenUrl")
	public ResponseEntity<?> shortenUrl(@RequestParam String url) {

		String uri = shortenMeService.shortenUrl(url);
		return new ResponseEntity<>(uri, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<?> originalUrl(@RequestParam String url) throws URISyntaxException {

		HttpHeaders httpHeaders = new HttpHeaders();
		String originalUrl = shortenMeService.getOriginalUrl(url);
		if (StringUtils.isEmpty(originalUrl)) {
			return new ResponseEntity<>(httpHeaders, HttpStatus.BAD_REQUEST);
		}
		URI uri = new URI(originalUrl);

		httpHeaders.setLocation(uri);
		logger.info("http header is {}", httpHeaders);
		return new ResponseEntity<>(httpHeaders, HttpStatus.MOVED_PERMANENTLY);
	}

}
