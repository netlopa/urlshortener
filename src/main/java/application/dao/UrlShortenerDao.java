package application.dao;

import java.util.Set;

import application.dao.entity.ShortenedUrl;

public interface UrlShortenerDao {

	ShortenedUrl getShortenedUrl(String id);

	void insertShortenedUrl(ShortenedUrl shortenedUrl);

	void updateRedirCountShortenedUrl(ShortenedUrl shortenedUrl);

	void deleteShortenedUrlById(String id);

	Set<ShortenedUrl> getAllShortenedUrl();

}