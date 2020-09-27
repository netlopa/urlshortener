package application.ws;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import application.businesslogic.UrlShortenerService;
import application.dao.entity.ShortenedUrl;


@RestController
public class UrlShortenerController {

    @Autowired
    ApplicationContext applicationContext;
    
    @Autowired
    UrlShortenerService urlShortenerService;
    
    @PostMapping(value = "/api/createurl", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ShortenedUrl createShortenedUrl(@RequestParam(value = "url") String url) {
    	ShortenedUrl ret = urlShortenerService.createShortenedUrl(url);
    	return ret;
    }
    
    @DeleteMapping(value = "/api/deleteurl/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String deleteShortenedUrl(@PathVariable String id) {
    	urlShortenerService.deleteShortenedUrl(id);
    	return "OK";
    }
    
    @GetMapping(value = "/api/stats", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Set<ShortenedUrl> getStatistics() {
    	return urlShortenerService.getAllShortenedUrls();
    }
    
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void redirectShortenedUrl(@PathVariable String id, HttpServletResponse response) throws IOException {
    	String url = urlShortenerService.getShortenedUrlAndAddCount(id);
    	response.sendRedirect(url);
    }

}
