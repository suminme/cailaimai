package ec.core.mall.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.mall.model.CoreMall;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.model.CoreMallTypeMeta;
import ec.core.mall.model.CoreMallTypeMetaValue;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Component
public class CoreMallDao {

	/**
	 * 获取所有商城列表
	 */
	public List<CoreMall> getMallList() {
		String sql = "select * from {TABLE} where valid=? order by id asc";
		List<CoreMall> datas = this.getRdsService().gets(CoreMall.class, sql, new Object[] { true });
		return datas;
	}

	/**
	 * 根据ID获取商城
	 */
	public CoreMall getMallById(Long id) {
		String sql = "select a.* from {TABLE} a where a.id=? and a.valid=? order by id asc";
		CoreMall data = this.getRdsService().get(CoreMall.class, sql, new Object[] { id, true });
		return data;
	}

	/**
	 * 根据Sign获取商城
	 */
	public CoreMall getMallBySign(String sign) {
		String sql = "select a.* from {TABLE} a where a.sign=? and a.valid=? order by id asc";
		CoreMall data = this.getRdsService().get(CoreMall.class, sql, new Object[] { sign, true });
		return data;
	}

	/**
	 * 更新商城
	 */
	public void updateMall(CoreMall data) {
		this.getRdsService().save(data);
	}

	/**
	 * 根据ID获取商品类别
	 */
	public CoreMallType getTypeById(Long id) {
		String sql = "select a.* from {TABLE} a where a.id=? and a.valid=?";
		CoreMallType data = this.getRdsService().get(CoreMallType.class, sql, new Object[] { id, true });
		return data;
	}

	/**
	 * 获取商城默认商品类别
	 */
	public CoreMallType getTypeByMallDefault(String mallSign) {
		String sql = "select a.* from {TABLE} a where a.mallId in (select id from "
				+ this.getRdsService().getTableName(CoreMall.class)
				+ " where sign=? and valid=?) and a.valid=? order by id asc limit 0,1";
		CoreMallType data = this.getRdsService().get(CoreMallType.class, sql, new Object[] { mallSign, true, true });
		return data;
	}

	/**
	 * 获取所有商品类别
	 */
	public Map<Long, List<CoreMallType>> getTypeMap() {
		String sql = "select * from {TABLE} where valid=? order by mallId asc,orderIndex asc,id asc";
		List<CoreMallType> datas = this.getRdsService().gets(CoreMallType.class, sql, new Object[] { true });
		if (null == datas || datas.size() == 0) {
			return null;
		}
		Map<Long, List<CoreMallType>> map = new HashMap<Long, List<CoreMallType>>();
		for (CoreMallType data : datas) {
			Long key = data.getMallId();
			List<CoreMallType> list = map.get(key);
			if (null == list) {
				list = new ArrayList<CoreMallType>();
			}
			list.add(data);
			map.put(key, list);
		}
		return map;
	}

	/**
	 * 获取所有商品类别属性
	 */
	public Map<Long, List<CoreMallTypeMeta>> getTypeMetaMap() {
		String sql = "select * from {TABLE} where valid=? order by typeId asc,orderIndex asc";
		List<CoreMallTypeMeta> datas = this.getRdsService().gets(CoreMallTypeMeta.class, sql, new Object[] { true });
		if (null == datas || datas.size() == 0) {
			return null;
		}
		Map<Long, List<CoreMallTypeMeta>> map = new HashMap<Long, List<CoreMallTypeMeta>>();
		for (CoreMallTypeMeta data : datas) {
			Long key = data.getTypeId();
			List<CoreMallTypeMeta> list = map.get(key);
			if (null == list) {
				list = new ArrayList<CoreMallTypeMeta>();
			}
			list.add(data);
			map.put(key, list);
		}
		return map;
	}

	/**
	 * 获取所有商品类别属性值
	 */
	public Map<Long, List<CoreMallTypeMetaValue>> getTypeMetaValueMap() {
		String sql = "select * from {TABLE} where valid=? order by metaId asc,orderIndex asc";
		List<CoreMallTypeMetaValue> datas = this.getRdsService().gets(CoreMallTypeMetaValue.class, sql,
				new Object[] { true });
		if (null == datas || datas.size() == 0) {
			return null;
		}
		Map<Long, List<CoreMallTypeMetaValue>> map = new HashMap<Long, List<CoreMallTypeMetaValue>>();
		for (CoreMallTypeMetaValue data : datas) {
			Long key = data.getMetaId();
			List<CoreMallTypeMetaValue> list = map.get(key);
			if (null == list) {
				list = new ArrayList<CoreMallTypeMetaValue>();
			}
			list.add(data);
			map.put(key, list);
		}
		return map;
	}

	/**
	 * 
	 */
	@Resource
	private RdsService rdsService;

	public RdsService getRdsService() {
		return rdsService;
	}

	public void setRdsService(RdsService rdsService) {
		this.rdsService = rdsService;
	}
}