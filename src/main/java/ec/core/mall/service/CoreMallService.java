package ec.core.mall.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ec.core.mall.dao.CoreMallDao;
import ec.core.mall.model.CoreMall;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.model.CoreMallTypeMeta;
import ec.core.mall.model.CoreMallTypeMetaValue;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
public class CoreMallService {

	/**
	 * 获取所有商城列表
	 */
	public List<CoreMall> getMallList() {
		List<CoreMall> datas = this.getCoreMallDao().getMallList();
		if (null != datas) {
			for (CoreMall data : datas) {
				this.dealMallFreight(data);
			}
		}
		return datas;
	}

	/**
	 * 根据ID获取商城
	 */
	public CoreMall getMallById(Long id) {
		CoreMall data = this.getCoreMallDao().getMallById(id);
		this.dealMallFreight(data);
		return data;
	}

	/**
	 * 根据Sign获取商城
	 */
	public CoreMall getMallBySign(String sign) {
		CoreMall data = this.getCoreMallDao().getMallBySign(sign);
		this.dealMallFreight(data);
		return data;
	}

	/**
	 * 处理商城运费模板
	 */
	public void dealMallFreight(CoreMall mall) {
		if (null == mall) {
			return;
		}
		String freightAbout = mall.getFreightAbout();
		if (null == freightAbout) {
			return;
		}
		freightAbout = freightAbout.replace("{free}", String.valueOf(mall.getFreightFree())).replace("{freight}",
				String.valueOf(mall.getFreight()));
		mall.setFreightAbout(freightAbout);
	}

	/**
	 * 更新商城运费模板
	 */
	public void updateMallFreight(CoreMall form) {
		CoreMall db = this.getMallById(form.getId());
		db.setFreight(form.getFreight());
		db.setFreightFree(form.getFreightFree());
		db.setFreightAbout(form.getFreightAbout());
		db.setFreightDelivery(form.getFreightDelivery());
		this.getCoreMallDao().updateMall(db);
	}

	/**
	 * 根据ID获取商品类别
	 */
	public CoreMallType getTypeById(Long id) {
		CoreMallType data = this.getCoreMallDao().getTypeById(id);
		return data;
	}

	/**
	 * 获取商城默认商品类别
	 */
	public CoreMallType getTypeByMallDefault(String mallSign) {
		CoreMallType data = this.getCoreMallDao().getTypeByMallDefault(mallSign);
		return data;
	}

	/**
	 * 获取所有商品类别
	 */
	public Map<Long, List<CoreMallType>> getTypeMap() {
		Map<Long, List<CoreMallType>> map = this.getCoreMallDao().getTypeMap();
		return map;
	}

	/**
	 * 获取所有商品类别属性
	 */
	public Map<Long, List<CoreMallTypeMeta>> getTypeMetaMap() {
		Map<Long, List<CoreMallTypeMeta>> map = this.getCoreMallDao().getTypeMetaMap();
		return map;
	}

	/**
	 * 获取所有商品类别属性值
	 */
	public Map<Long, List<CoreMallTypeMetaValue>> getTypeMetaValueMap() {
		Map<Long, List<CoreMallTypeMetaValue>> map = this.getCoreMallDao().getTypeMetaValueMap();
		return map;
	}

	/**
	 * 
	 */
	@Resource
	private CoreMallDao coreMallDao;

	public CoreMallDao getCoreMallDao() {
		return coreMallDao;
	}

	public void setCoreMallDao(CoreMallDao coreMallDao) {
		this.coreMallDao = coreMallDao;
	}
}