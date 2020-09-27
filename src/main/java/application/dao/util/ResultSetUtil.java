package application.dao.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;

public class ResultSetUtil<T> {
	T obj;
	private Class clazz;

	public ResultSetUtil(Class clazz) {
		super();
		this.clazz = clazz;
	}

	public T getEntityFromResultSet(ResultSet resultSet)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, SQLException {

		T obj = (T) clazz.newInstance();

		for (Field field : obj.getClass().getDeclaredFields()) {

			field.setAccessible(true);
			Column annotation = field.getAnnotation(Column.class);
			if (annotation == null)
				continue;
			String annotationName = annotation.name();
			String name = !annotationName.isEmpty() ? annotationName : field.getName();
			Class<?> type = field.getType();

			if (type.equals(Long.class) || type.toString().equals("long")) {
				field.set(obj, resultSet.getLong(name));
			} else if (type.equals(Integer.class) || type.toString().equals("int")) {

				if (resultSet.getObject(name) == null)
					field.set(obj, null);
				else
					field.set(obj, resultSet.getInt(name));
			} else if (type.equals(Byte.class) || type.toString().equals("byte")) {
				field.set(obj, resultSet.getByte(name));
			} else if (type.equals(Float.class) || type.toString().equals("float")) {
				field.set(obj, resultSet.getFloat(name));
			} else if (type.equals(Double.class) || type.toString().equals("double")) {
				field.set(obj, resultSet.getLong(name));
			} else if (type.equals(Date.class)) {
				field.set(obj, resultSet.getDate(name));
			} else if (type.equals(Timestamp.class)) {
				field.set(obj, resultSet.getTimestamp(name));
			} else if (type.equals(String.class)) {
				field.set(obj, resultSet.getString(name));
			} else if (type.equals(Boolean.class) || type.toString().equals("boolean")) {
				field.set(obj, resultSet.getBoolean(name));
			} else {
				field.set(obj, resultSet.getObject(name));
			}
		}

		return obj;

	}

}
