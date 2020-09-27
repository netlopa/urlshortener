package application.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.ResultSetExtractor;

public class ObjectSetExtractor<T> implements ResultSetExtractor<Set<T>> {

	private static final Logger logger = LogManager.getLogger(ObjectSetExtractor.class);

	private Class clazz;
	private Set<T> results;

	@SuppressWarnings("unchecked")
	public ObjectSetExtractor(Class clazz) {
		this.clazz = clazz;
		results = new HashSet<>();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<T> extractData(ResultSet resultSet) throws SQLException {
		while (resultSet.next()) {

			try {

				ResultSetUtil<T> utils = new ResultSetUtil<T>(clazz);
				T obj = utils.getEntityFromResultSet(resultSet);

				results.add(obj);

			} catch (IllegalAccessException | InstantiationException e) {
				logger.error("Errore nell'estrazione dei dati da DB con ObjectSerExtractor");
			}
		}

		return results;
	}
}