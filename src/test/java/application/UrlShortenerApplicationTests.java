package application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import application.dao.UrlShortenerDao;
import application.dao.entity.ShortenedUrl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlShortenerApplicationTests {

	@Autowired
	UrlShortenerDao urlShortenerDao;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void shouldInsertUrl() {
		String url = "http://www.google.it";
		
		ShortenedUrl shortenedUrl = new ShortenedUrl();
		shortenedUrl.setId("abcdef12");
		shortenedUrl.setUrl(url);
		shortenedUrl.setRedirCount(0);
		urlShortenerDao.insertShortenedUrl(shortenedUrl);
		
		ShortenedUrl sh2 = urlShortenerDao.getShortenedUrl("abcdef12");
		assert sh2.getUrl().equals(url);
	}
	

}
