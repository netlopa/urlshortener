package application.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

public class ObjectExtractor<T> implements RowMapper<T> {

	private static final Logger logger = LogManager.getLogger(ObjectExtractor.class);

	private Class clazz;

	@SuppressWarnings("unchecked")
	public ObjectExtractor(Class clazz) {
		this.clazz = clazz;

	}

	@Override
	public T mapRow(ResultSet resultSet, int rowNum) throws SQLException {

		try {

			ResultSetUtil<T> utils = new ResultSetUtil<T>(clazz);
			return utils.getEntityFromResultSet(resultSet);

		} catch (IllegalAccessException | InstantiationException e) {
			logger.error("Errore nell'estrazione dei dati da DB con ObjectSerExtractor");
		}
		return null;
		// TODO ritornare exception
	}

}