package application.dao;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import application.dao.entity.ShortenedUrl;
import application.dao.util.ObjectExtractor;
import application.dao.util.ObjectSetExtractor;

@Repository
@Transactional
public class UrlShortenerDaoImpl implements UrlShortenerDao {

	public UrlShortenerDaoImpl() {
	}

	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	// Valori presi dal query configuration file	

	@Value("${query.getShortenedUrl}")
	private String getShortenedUrl;

	@Value("${query.insertShortenedUrl}")
	private String insertShortenedUrl;

	@Value("${query.getAllShortenedUrls}")
	private String getAllShortenedUrls;

	@Value("${query.deleteShortenedUrl}")
	private String deleteShortenedUrl;

	@Value("${query.updateRedirCountShortenedUrl}")
	private String updateRedirCountShortenedUrl;

	@Override
	public ShortenedUrl getShortenedUrl(String id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);
		System.out.println(getShortenedUrl);
		try {
			return jdbcTemplate.queryForObject(getShortenedUrl, parameterSource,
					new ObjectExtractor<ShortenedUrl>(ShortenedUrl.class));
		}
		catch (Exception e) {
			return null;
		}


	}

	@Override
	public void insertShortenedUrl(ShortenedUrl shortenedUrl) {
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(shortenedUrl);
		jdbcTemplate.batchUpdate(insertShortenedUrl, params);

	}

	@Override
	public void updateRedirCountShortenedUrl(ShortenedUrl shortenedUrl) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", shortenedUrl.getId());
		parameterSource.addValue("redirCount", shortenedUrl.getRedirCount());

		jdbcTemplate.update(updateRedirCountShortenedUrl, parameterSource);

	}

	@Override
	public void deleteShortenedUrlById(String id) {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", id);

		jdbcTemplate.update(deleteShortenedUrl, parameterSource);

	}

	@Override
	public Set<ShortenedUrl> getAllShortenedUrl() {
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();

		return jdbcTemplate.query(getAllShortenedUrls, parameterSource,
				new ObjectSetExtractor<ShortenedUrl>(ShortenedUrl.class));
	}

}
