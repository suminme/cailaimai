package ec.core.goods.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.core.goods.dao.CoreGoodsDao;
import ec.core.goods.model.CoreGoods;
import ec.core.goods.model.CoreGoodsImg;
import ec.core.goods.model.CoreGoodsMeta;
import ec.core.goods.model.CoreGoodsSample;
import ec.core.mall.dao.CoreMallDao;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.model.CoreMallTypeMeta;
import ec.core.message.service.CoreMessageService;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.service.CoreUserService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
@Transactional
public class CoreGoodsService {

	/**
	 * 获取商品样品列表
	 */
	public List<CoreGoodsSample> getGoodsSampleListByStatus(int status) {
		List<CoreGoodsSample> datas = this.getCoreGoodsDao().getGoodsSampleListByStatus(status);
		return datas;
	}

	/**
	 * 申请样品
	 */
	public CoreGoodsSample goodsSampleApply(CoreUser user, Long goodsId, Long addressId, String about) {
		CoreGoodsSample sample = this.getCoreGoodsDao().getUserApplyGoodsSample(user.getId(), goodsId);
		if (null == sample) {
			sample = new CoreGoodsSample();
			sample.setUserId(user.getId());
			sample.setGoodsId(goodsId);
			sample.setStatus(CoreGoodsSample.STATUS_APPLY);
		}
		if (addressId <= 0) {
			sample.setAddress("自提");
		} else {
			CoreUserAddress address = this.getCoreUserService().getAddressByUserAndId(user.getId(), addressId);
			sample.setAddress(address.getName() + "(" + address.getPhone() + ") " + address.getProvince() + ","
					+ address.getCity() + "," + address.getArea() + "," + address.getDetail());
		}
		sample.setAddressId(addressId);
		sample.setAbout(about);
		this.getCoreGoodsDao().saveGoodsSample(sample);
		this.getCoreMessageService().sendMessageToAdmin("用户:" + user.getMobile() + " 申请了一个样品", user.getId());
		return sample;
	}

	/**
	 * 根据ID获取商品
	 */
	public CoreGoods getGoodsById(Long id) {
		CoreGoods data = this.getCoreGoodsDao().getGoodsById(id);
		return data;
	}
	
	/**
	 * 根据Code获取商品
	 */
	public CoreGoods getGoodsByCode(String code) {
		CoreGoods data = this.getCoreGoodsDao().getGoodsByCode(code);
		return data;
	}

	/**
	 * 获取商品列表
	 */
	public List<CoreGoods> getGoodsList() {
		List<CoreGoods> datas = this.getCoreGoodsDao().getGoodsList();
		return datas;
	}

	/**
	 * 根据商城获取商品列表
	 */
	public List<CoreGoods> getGoodsListByMall(Long mallId) {
		List<CoreGoods> datas = this.getCoreGoodsDao().getGoodsListByMall(mallId);
		return datas;
	}

	/**
	 * 根据商品类别ID获取商品列表
	 */
	public List<CoreGoods> getGoodsListByType(Long typeId) {
		List<CoreGoods> datas = this.getCoreGoodsDao().getGoodsListByType(typeId);
		return datas;
	}

	/**
	 * 根据类别及筛选条件获取商品
	 */
	public List<CoreGoods> getGoodsListByTypeAndMetas(Long typeId, List<CoreGoodsMeta> metas) {
		List<CoreGoods> datas = this.getCoreGoodsDao().getGoodsListByTypeAndMetas(typeId, metas);
		return datas;
	}

	/**
	 * 根据类别获取推荐商品
	 */
	public List<CoreGoods> getSuggestGoodsListByType(Long mallId, int count) {
		List<CoreGoods> datas = this.getCoreGoodsDao().getSuggestGoodsListByType(mallId, count);
		return datas;
	}

	/**
	 * 根据商城ID获取商品类别及商品数量
	 */
	public List<CoreMallType> getTypeListByMallWithGoodsCount(Long mallId) {
		List<CoreMallType> datas = this.getCoreGoodsDao().getTypeListByMallWithGoodsCount(mallId);
		return datas;
	}

	/**
	 * 根据商品ID获取商品属性值
	 */
	public List<CoreGoodsMeta> getGoodsMetaListByGoods(Long goodsId) {
		List<CoreGoodsMeta> datas = this.getCoreGoodsDao().getGoodsMetaListByGoods(goodsId);
		return datas;
	}

	/**
	 * 根据商品ID获取商品配图
	 */
	public List<CoreGoodsImg> getGoodsImgListByGoods(Long goodsId) {
		List<CoreGoodsImg> datas = this.getCoreGoodsDao().getGoodsImgListByGoods(goodsId);
		return datas;
	}

	/**
	 * 商品保存
	 */
	public void saveOrUpdateGoods(CoreGoods goods, List<CoreGoodsImg> imgs, List<CoreGoodsMeta> metas, CoreUser user) {
		if (null == goods.getId()) {
			goods.setCode(this.createCode());
		} else {
			boolean s = this.getCoreGoodsDao().changeGoodsToHistory(goods.getId());
			if (!s) {
				throw new RuntimeException("该商品已被编辑，请刷新后再编辑。");
			}
			goods.setId(null);
		}
		this.getCoreGoodsDao().saveGoods(goods, imgs, metas);
	}

	/**
	 * 生成一个商品编号
	 */
	private String createCode() {
		String prefix = new SimpleDateFormat("yyMMdd").format(new Date());
		int maxCode = this.getCoreGoodsDao().getMaxCode();
		String code = prefix + new DecimalFormat("0000").format(maxCode);
		return code;
	}

	/**
	 * 商品删除
	 */
	public boolean deleteGoods(Long id, CoreUser user) {
		boolean success = this.getCoreGoodsDao().updateGoodsStatus(id, CoreGoods.STATUS_DELETE);
		return success;
	}

	/**
	 * 商品表单数据效验
	 */
	public void goodsFormValidate(CoreGoods goods, List<CoreGoodsImg> imgs, List<CoreGoodsMeta> metas) {
		if (null == goods || null == goods.getTypeId()) {
			throw new RuntimeException("数据错误");
		}
		if (null == goods.getName() || "".equals(goods.getName()) || goods.getName().length() > 20) {
			throw new RuntimeException("商品名称不能为空且不能超过20个字符");
		}
		if (null == goods.getUnit() || "".equals(goods.getUnit())) {
			throw new RuntimeException("单位错误");
		}
		if (goods.getPrice() < 0) {
			throw new RuntimeException("价格错误");
		}
		if (null == goods.getDescription() || "".equals(goods.getDescription())
				|| goods.getDescription().length() > 100) {
			throw new RuntimeException("商品简介不能为空且不能超过100个字符");
		}
		CoreMallType type = this.getCoreMallDao().getTypeById(goods.getTypeId());
		if (null == type) {
			throw new RuntimeException("商品类别不存在");
		}
		if (null == imgs || imgs.size() < 1) {
			throw new RuntimeException("商品图片不能为空");
		}
		List<CoreMallTypeMeta> typeMetas = this.getCoreMallDao().getTypeMetaMap().get(goods.getTypeId());
		if (null != typeMetas && typeMetas.size() > 0) {
			for (CoreMallTypeMeta typeMeta : typeMetas) {
				List<CoreGoodsMeta> ms = new ArrayList<CoreGoodsMeta>();
				for (CoreGoodsMeta meta : metas) {
					if (typeMeta.getId().equals(meta.getTypeMetaId())) {
						if (null != meta.getTypeMetaValueId()) {
							ms.add(meta);
						}
					}
				}
				if (typeMeta.isRequired() && ms.size() <= 0) {
					throw new RuntimeException(typeMeta.getName() + "不能为空");
				}
				if (!typeMeta.isMultiple() && ms.size() > 1) {
					throw new RuntimeException(typeMeta.getName() + "只能单选");
				}
			}
		}
	}

	/**
	 * 
	 */
	@Resource
	private CoreMallDao coreMallDao;

	/**
	 * 
	 */
	@Resource
	private CoreGoodsDao coreGoodsDao;

	/**
	 * 
	 */
	@Resource
	private CoreUserService coreUserService;

	/**
	 * 
	 */
	@Resource
	private CoreMessageService coreMessageService;

	private CoreGoodsDao getCoreGoodsDao() {
		return coreGoodsDao;
	}

	public void setCoreGoodsDao(CoreGoodsDao coreGoodsDao) {
		this.coreGoodsDao = coreGoodsDao;
	}

	public CoreMallDao getCoreMallDao() {
		return coreMallDao;
	}

	public void setCoreMallDao(CoreMallDao coreMallDao) {
		this.coreMallDao = coreMallDao;
	}

	public CoreUserService getCoreUserService() {
		return coreUserService;
	}

	public void setCoreUserService(CoreUserService coreUserService) {
		this.coreUserService = coreUserService;
	}

	public CoreMessageService getCoreMessageService() {
		return coreMessageService;
	}

	public void setCoreMessageService(CoreMessageService coreMessageService) {
		this.coreMessageService = coreMessageService;
	}
}