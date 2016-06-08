package framework.service.rds.impl.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import framework.service.rds.RdsService;
import framework.service.rds.annotation.Column;
import framework.service.rds.annotation.Id;
import framework.service.rds.annotation.Table;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-04-18
 */
public class RdsServiceImpl implements RdsService {

	/**
	 *
	 */
	@Override
	public <T> int save(T t) {
		Object ob = this.fieldGet(t, this.getIdField(t.getClass()));
		if (null != ob) {
			return this.update(t);
		}
		String tableName = this.getTableName(t.getClass());
		Field idf = null;
		String sql = "insert into " + tableName + "(";
		List<Object> parameters = new ArrayList<Object>();
		String vf = "";
		for (Field field : this.getDeclaredFields(t.getClass())) {
			if (null != field.getAnnotation(Id.class)) {
				idf = field;
			}
			if (null == field.getAnnotation(Column.class)) {
				continue;
			}
			vf = vf + "?,";
			sql = sql + field.getName() + ",";
			parameters.add(fieldGet(t, field));
		}
		sql = sql.substring(0, sql.length() - 1) + ") values(" + vf.substring(0, vf.length() - 1) + ")";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int count = this.getMainJdbcTemplate().update(this.getPreparedStatementCreator(sql, parameters.toArray()),
				keyHolder);
		this.fieldSet(t, idf, keyHolder.getKey());
		return count;
	}

	/**
	 *
	 */
	@Override
	public <T> int update(T t) {
		String tableName = this.getTableName(t.getClass());
		Field idf = null;
		String sql = "update " + tableName + " set ";
		List<Object> parameters = new ArrayList<Object>();
		for (Field field : this.getDeclaredFields(t.getClass())) {
			if (null != field.getAnnotation(Id.class)) {
				idf = field;
			}
			if (null == field.getAnnotation(Column.class)) {
				continue;
			}
			sql = sql + field.getName() + "=?,";
			parameters.add(fieldGet(t, field));
		}
		sql = sql.substring(0, sql.length() - 1) + " where " + idf.getName() + "=?";
		parameters.add(fieldGet(t, idf));
		int count = this.getMainJdbcTemplate().update(this.getPreparedStatementCreator(sql, parameters.toArray()));
		return count;
	}

	/**
	 * 
	 */
	@Override
	public <T> void delete(T t) {
		Object id = null;
		try {
			id = this.getIdField(t.getClass()).get(t);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("get [id] value error in Class[{1}]");
		}
		this.delete(t.getClass(), id);
	}

	/**
	 *
	 */
	@Override
	public <T> void delete(Class<T> c, Object id) {
		StringBuffer sql = new StringBuffer("delete from ").append((this.getTableName(c))).append(" where ")
				.append(this.getIdField(c).getName()).append("=?");
		this.update(c, sql.toString(), new Object[] { id });
	}

	/**
	 * 
	 */
	@Override
	public <T> int update(String sql, Object[] parameters) {
		int count = this.getMainJdbcTemplate().update(this.getPreparedStatementCreator(sql, parameters));
		return count;
	}

	/**
	 * 
	 */
	@Override
	public <T> int update(Class<T> c, String sql, Object[] parameters) {
		return this.update(this.sqlInject(sql, c), parameters);
	}

	/**
	 * 
	 */
	@Override
	public <T> T get(Class<T> c, Object id) {
		StringBuffer sql = new StringBuffer("select * from ").append((this.getTableName(c))).append(" where ");
		sql.append(this.getIdField(c).getName()).append("=?");
		return this.get(c, sql.toString(), new Object[] { id });
	}

	/**
	 * 
	 */
	@Override
	public <T> T get(Class<T> c, String sql, Object[] parameters) {
		List<T> datas = this.gets(c, sql, parameters);
		if (null == datas) {
			return null;
		}
		return datas.get(0);
	}

	/**
	 *
	 */
	@Override
	public <T> List<T> gets(Class<T> c, String sql, Object[] parameters) {
		String executeSql = this.sqlInject(sql, c);
		List<T> datas = this.getReadJdbcTemplate().query(this.getPreparedStatementCreator(executeSql, parameters),
				BeanPropertyRowMapper.newInstance(c));
		if (null == datas || datas.size() < 1) {
			return null;
		}
		return datas;
	}

	/**
	 *
	 */
	@Override
	public <T> String getTableName(Class<T> c) {
		Table p = ((Table) c.getAnnotation(Table.class));
		if (null == p) {
			return "";
		}
		String tablePrefix = this.getTablePrefix();
		if (null == tablePrefix) {
			return p.name();
		}
		return tablePrefix + p.name();
	}

	/**
	 * 
	 */
	private <T> String sqlInject(String sql, Class<T> c) {
		if (null == sql || null == c) {
			return sql;
		}
		String tn = this.getTableName(c);
		if (null == tn) {
			return sql;
		}
		return sql.replace("{TABLE}", tn);
	}

	/**
	 * Field get
	 */
	private <T> Object fieldGet(T t, Field field) {
		try {
			return field.get(t);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("get field {1} value error in Class[{2}]");
		}
	}

	/**
	 * Field get
	 */
	private <T> void fieldSet(T t, Field field, Object value) {
		try {
			field.set(t, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException("set field {1} value error in Class[{2}]");
		}
	}

	/**
	 *
	 */
	private <T> Field getIdField(Class<T> c) {
		for (Field field : this.getDeclaredFields(c)) {
			field.setAccessible(true);
			if (null != field.getAnnotation(Id.class)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private List<Field> getDeclaredFields(Class c) {
		List<Field> list = new ArrayList<Field>();
		return this.getDeclaredFields(c, list);
	}

	/**
	 * 
	 */
	@SuppressWarnings("rawtypes")
	private List<Field> getDeclaredFields(Class c, List<Field> list) {
		if (c == null) {
			return list;
		}
		for (Field field : c.getDeclaredFields()) {
			field.setAccessible(true);
			list.add(field);
		}
		return this.getDeclaredFields(c.getSuperclass(), list);
	}

	/**
	 * 
	 */
	private PreparedStatementCreator getPreparedStatementCreator(String sql, Object[] parameters) {
		return new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				if (null != parameters && parameters.length > 0) {
					for (int n = 0; n < parameters.length; n++) {
						ps.setObject(n + 1, parameters[n]);
					}
				}
				return ps;
			}
		};
	}

	/**
	 * 
	 */
	private String tablePrefix;

	/**
	 * 
	 */
	@Resource
	private JdbcTemplate jdbcTemplate;

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private JdbcTemplate getMainJdbcTemplate() {
		return jdbcTemplate;
	}

	private JdbcTemplate getReadJdbcTemplate() {
		return jdbcTemplate;
	}
}