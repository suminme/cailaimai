package framework.service.rds;

import java.util.List;

/**
 * @author sumin
 * @version 0.1
 * @since 2016-01-09
 */
public interface RdsService {

	/**
	 *
	 */
	public <T> int save(T t);

	/**
	 *
	 */
	public <T> int update(T t);

	/**
	 * 
	 */
	public <T> void delete(T t);

	/**
	 *
	 */
	public <T> void delete(Class<T> c, Object id);

	/**
	 * 
	 */
	public <T> int update(String sql, Object[] parameters);

	/**
	 * 
	 */
	public <T> int update(Class<T> c, String sql, Object[] parameters);

	/**
	 *
	 */
	public <T> T get(Class<T> c, Object id);

	/**
	 *
	 */
	public <T> T get(Class<T> c, String sql, Object[] parameters);

	/**
	 *
	 */
	public <T> List<T> gets(Class<T> c, String sql, Object[] parameters);

	/**
	 * 
	 */
	public <T> String getTableName(Class<T> c);
}
