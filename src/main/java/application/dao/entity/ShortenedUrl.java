package application.dao.entity;

import javax.persistence.Column;

public class ShortenedUrl implements Comparable<ShortenedUrl> {

	@Column(name = "ID")
	private String id;
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "REDIR_COUNT")
	private int redirCount;
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public int getRedirCount() {
		return redirCount;
	}



	public void setRedirCount(int redirCount) {
		this.redirCount = redirCount;
	}



	@Override
	public int compareTo(ShortenedUrl o) {
		return this.id.compareTo(o.url);
	}
}
