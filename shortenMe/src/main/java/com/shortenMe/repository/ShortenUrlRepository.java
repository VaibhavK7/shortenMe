package com.shortenMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shortenMe.Entity.UrlShortenerEntity;

@Repository
public interface ShortenUrlRepository extends JpaRepository<UrlShortenerEntity, Integer>{
	
	public UrlShortenerEntity findByAlias(String url);

	public UrlShortenerEntity findByUrl(String url);

}
