package com.shortenMe.service;

public interface ShortenMeService {

	public String shortenUrl(String url);

	public String getOriginalUrl(String url);
}
