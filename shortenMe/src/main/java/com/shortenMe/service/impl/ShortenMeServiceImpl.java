package com.shortenMe.service.impl;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.common.hash.Hashing;

import com.shortenMe.Entity.UrlShortenerEntity;
import com.shortenMe.repository.ShortenUrlRepository;
import com.shortenMe.service.ShortenMeService;

@Service
public class ShortenMeServiceImpl implements ShortenMeService {

	@Autowired
	ShortenUrlRepository shortenUrlRepository;

	Logger logger = LoggerFactory.getLogger(ShortenMeServiceImpl.class);

	@Override
	public String shortenUrl(String url) {

		logger.info("Got url {} to shorten", url);

		String key = "";

		UrlShortenerEntity urlShortenerEntity = shortenUrlRepository.findByUrl(url);
		if (Objects.isNull(urlShortenerEntity)) {
			key = Hashing.murmur3_32().hashString(url, Charset.defaultCharset()).toString();
			urlShortenerEntity = new UrlShortenerEntity(url, key, new Date());
			shortenUrlRepository.save(urlShortenerEntity);
		}

		return "localhost:8080/shortenMe/?url=".concat(urlShortenerEntity.getAlias());
	}

	@Override
	public String getOriginalUrl(String url) {
		UrlShortenerEntity urlShortenerEntity = shortenUrlRepository.findByAlias(url);
		if (Objects.isNull(urlShortenerEntity)) {
			return null;
		}
		return urlShortenerEntity.getUrl();
	}

}
