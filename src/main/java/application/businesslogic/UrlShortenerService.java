package application.businesslogic;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import application.dao.UrlShortenerDao;
import application.dao.entity.ShortenedUrl;
import application.exceptions.NotFoundException;

@Component
public class UrlShortenerService {

	@Autowired
	UrlShortenerDao urlShortenerDao;

	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public ShortenedUrl createShortenedUrl(String urlToShorten) {

		//I need to check if the created ID exists
		boolean available = false;
		String id = "";
		while (!available) {
			id = this.randomAlphaNumeric(6);
			if (urlShortenerDao.getShortenedUrl(id) == null) available=true;
		}
		ShortenedUrl shortenedUrl = new ShortenedUrl();
		shortenedUrl.setId(id);
		shortenedUrl.setRedirCount(0);
		shortenedUrl.setUrl(urlToShorten);
		urlShortenerDao.insertShortenedUrl(shortenedUrl);
		return shortenedUrl;
	}


	public String getShortenedUrlAndAddCount(String id) {
		ShortenedUrl shortenedUrl = urlShortenerDao.getShortenedUrl(id);
		if (shortenedUrl == null) throw new NotFoundException();
		shortenedUrl.setRedirCount(shortenedUrl.getRedirCount()+1);
		urlShortenerDao.updateRedirCountShortenedUrl(shortenedUrl);

		return shortenedUrl.getUrl();
	}

	public Set<ShortenedUrl> getAllShortenedUrls() {
		Set<ShortenedUrl> ret = urlShortenerDao.getAllShortenedUrl();
		return ret;
	}

	public void deleteShortenedUrl(String id) {
		urlShortenerDao.deleteShortenedUrlById(id);
	}

	private String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

}
