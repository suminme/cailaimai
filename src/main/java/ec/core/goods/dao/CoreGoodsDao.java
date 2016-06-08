package ec.core.goods.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.goods.model.CoreGoods;
import ec.core.goods.model.CoreGoodsCode;
import ec.core.goods.model.CoreGoodsImg;
import ec.core.goods.model.CoreGoodsMeta;
import ec.core.goods.model.CoreGoodsSample;
import ec.core.mall.model.CoreMallType;
import ec.core.mall.model.CoreMallTypeMeta;
import ec.core.mall.model.CoreMallTypeMetaValue;
import ec.core.order.model.CoreOrder;
import ec.core.order.model.CoreOrderGoods;
import ec.core.stock.model.CoreStock;
import ec.core.user.model.CoreUser;
import ec.system.file.model.SystemFile;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Component
public class CoreGoodsDao {

	/**
	 * 根据ID获取商品
	 */
	public CoreGoods getGoodsById(Long id) {
		String sql = "select a.*,ifnull(b.c,0) as stock,c.mallId as mallId,d.bucketKey as imagePath,IFNULL(e.amount,0) as totalSale from {TABLE} a left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) b on a.code=b.goodsCode left join "
				+ this.getRdsService().getTableName(CoreMallType.class)
				+ " c on a.typeId=c.id  left join (select a.*,b.bucketKey from "
				+ this.getRdsService().getTableName(CoreGoodsImg.class) + " a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.main=?) d on a.id=d.goodsId left join (select a.goodsCode,sum(a.amount) amount from "
				+ this.getRdsService().getTableName(CoreOrderGoods.class) + " a left join "
				+ this.getRdsService().getTableName(CoreOrder.class)
				+ " b on a.orderId=b.id where b.status!=? group by a.goodsCode) e on a.code=e.goodsCode where a.id=? and a.status in (?,?)";
		CoreGoods data = this.getRdsService().get(CoreGoods.class, sql,
				new Object[] { true, CoreOrder.STATUS_CANEL, id, CoreGoods.STATUS_NORMAL, CoreGoods.STATUS_HISTORY });
		return data;
	}

	/**
	 * 根据Code获取商品
	 */
	public CoreGoods getGoodsByCode(String code) {
		String sql = "select a.*,ifnull(b.c,0) as stock,c.mallId as mallId,d.bucketKey as imagePath,IFNULL(e.amount,0) as totalSale from {TABLE} a left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) b on a.code=b.goodsCode left join "
				+ this.getRdsService().getTableName(CoreMallType.class)
				+ " c on a.typeId=c.id  left join (select a.*,b.bucketKey from "
				+ this.getRdsService().getTableName(CoreGoodsImg.class) + " a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.main=?) d on a.id=d.goodsId left join (select a.goodsCode,sum(a.amount) amount from "
				+ this.getRdsService().getTableName(CoreOrderGoods.class) + " a left join "
				+ this.getRdsService().getTableName(CoreOrder.class)
				+ " b on a.orderId=b.id where b.status!=? group by a.goodsCode) e on a.code=e.goodsCode where a.code=? and a.status in (?)";
		CoreGoods data = this.getRdsService().get(CoreGoods.class, sql,
				new Object[] { true, CoreOrder.STATUS_CANEL, code, CoreGoods.STATUS_NORMAL });
		return data;
	}

	/**
	 * 根据商城获取商品列表
	 */
	public List<CoreGoods> getGoodsListByMall(Long mallId) {
		String sql = "select a.id,a.status,a.typeId,a.code,a.name,a.unit,a.price,a.suggest,a.description,a.createTime,a.onsale,a.sample,a.upload,b.bucketKey as imagePath,ifnull(c.c,0) as stock,IFNULL(e.amount,0) as totalSale from {TABLE} a left join (select a.*,b.bucketKey from "
				+ this.getRdsService().getTableName(CoreGoodsImg.class) + " a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.main=?) b on a.id=b.goodsId left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) c on a.code=c.goodsCode left join "
				+ this.getRdsService().getTableName(CoreMallType.class)
				+ " d on a.typeId=d.id left join (select a.goodsCode,sum(a.amount) amount from "
				+ this.getRdsService().getTableName(CoreOrderGoods.class) + " a left join "
				+ this.getRdsService().getTableName(CoreOrder.class)
				+ " b on a.orderId=b.id where b.status!=? group by a.goodsCode) e on a.code=e.goodsCode where d.mallId=? and a.status=? and onsale=?";
		List<CoreGoods> datas = this.getRdsService().gets(CoreGoods.class, sql,
				new Object[] { true, CoreOrder.STATUS_CANEL,mallId, CoreGoods.STATUS_NORMAL, true });
		return datas;
	}

	/**
	 * 根据类别及筛选条件获取商品
	 */
	public List<CoreGoods> getGoodsListByTypeAndMetas(Long typeId, List<CoreGoodsMeta> metas) {
		List<Object> obs = new ArrayList<Object>();
		String sql = "select a.id,a.status,a.typeId,a.code,a.name,a.unit,a.price,a.suggest,a.description,a.createTime,a.onsale,a.sample,a.upload,b.bucketKey as imagePath,ifnull(c.c,0) as stock,IFNULL(e.amount,0) as totalSale from {TABLE} a left join (select a.*,b.bucketKey from "
				+ this.getRdsService().getTableName(CoreGoodsImg.class) + " a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.main=?) b on a.id=b.goodsId left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) c on a.code=c.goodsCode left join (select a.goodsCode,sum(a.amount) amount from "
				+ this.getRdsService().getTableName(CoreOrderGoods.class) + " a left join "
				+ this.getRdsService().getTableName(CoreOrder.class)
				+ " b on a.orderId=b.id where b.status!=? group by a.goodsCode) e on a.code=e.goodsCode where a.typeId=? and a.status=? and onsale=?";
		obs.add(true);
		obs.add(CoreOrder.STATUS_CANEL);
		obs.add(typeId);
		obs.add(CoreGoods.STATUS_NORMAL);
		obs.add(true);
		if (null != metas && metas.size() > 0) {
			sql = sql + " and a.id in (select goodsId from " + this.getRdsService().getTableName(CoreGoodsMeta.class)
					+ " where ";
			for (CoreGoodsMeta meta : metas) {
				sql = sql + "(typeMetaId=? and typeMetaValueId=?) or";
				obs.add(meta.getTypeMetaId());
				obs.add(meta.getTypeMetaValueId());
			}
			if (sql.endsWith("or")) {
				sql = sql.substring(0, sql.length() - 2);
			}
			sql = sql + ")";
		}
		sql = sql + " order by a.id desc";
		List<CoreGoods> datas = this.getRdsService().gets(CoreGoods.class, sql, obs.toArray());
		return datas;
	}

	/**
	 * 获取商城的推荐商品
	 */
	public List<CoreGoods> getSuggestGoodsListByType(Long mallId, int count) {
		List<Object> obs = new ArrayList<Object>();
		String sql = "select a.id,a.status,a.typeId,a.code,a.name,a.unit,a.price,a.suggest,a.description,a.createTime,a.onsale,a.sample,a.upload,b.bucketKey as imagePath,ifnull(c.c,0) as stock,IFNULL(e.amount,0) as totalSale from {TABLE} a left join (select a.*,b.bucketKey from "
				+ this.getRdsService().getTableName(CoreGoodsImg.class) + " a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.main=?) b on a.id=b.goodsId left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) c on a.code=c.goodsCode left join (select a.goodsCode,sum(a.amount) amount from "
				+ this.getRdsService().getTableName(CoreOrderGoods.class) + " a left join "
				+ this.getRdsService().getTableName(CoreOrder.class)
				+ " b on a.orderId=b.id where b.status!=? group by a.goodsCode) e on a.code=e.goodsCode where a.status=? and a.suggest=? and onsale=?";
		obs.add(true);
		obs.add(CoreOrder.STATUS_CANEL);
		obs.add(CoreGoods.STATUS_NORMAL);
		obs.add(true);
		obs.add(true);
		if (null != mallId) {
			sql = sql + " and a.typeId in (select id from " + this.getRdsService().getTableName(CoreMallType.class)
					+ " where mallId=?)";
			obs.add(mallId);
		}
		sql = sql + " order by e.amount desc,a.suggest desc,a.id desc limit 0," + count;
		List<CoreGoods> datas = this.getRdsService().gets(CoreGoods.class, sql, obs.toArray());
		return datas;
	}

	/**
	 * 根据商品类别ID获取商品列表
	 */
	public List<CoreGoods> getGoodsListByType(Long typeId) {
		String sql = "select a.id,a.status,a.typeId,a.code,a.name,a.unit,a.price,a.suggest,a.description,a.createTime,a.onsale,a.sample,a.upload,ifnull(b.c,0) as stock from {TABLE} a left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) b on a.code=b.goodsCode where a.typeId=? and a.status=? and onsale=? order by a.id desc";
		List<CoreGoods> datas = this.getRdsService().gets(CoreGoods.class, sql,
				new Object[] { typeId, CoreGoods.STATUS_NORMAL, true });
		return datas;
	}

	/**
	 * 获取商品列表
	 */
	public List<CoreGoods> getGoodsList() {
		String sql = "select a.id,a.status,a.typeId,a.code,a.name,a.unit,a.price,a.suggest,a.description,a.createTime,a.onsale,a.sample,a.upload,ifnull(b.c,0) as stock from {TABLE} a left join (select goodsCode,sum(num) as c from "
				+ this.getRdsService().getTableName(CoreStock.class)
				+ " group by goodsCode) b on a.code=b.goodsCode where a.status=? order by a.id desc";
		List<CoreGoods> datas = this.getRdsService().gets(CoreGoods.class, sql,
				new Object[] { CoreGoods.STATUS_NORMAL });
		return datas;
	}

	/**
	 * 根据商城ID获取商品类别及商品数量
	 */
	public List<CoreMallType> getTypeListByMallWithGoodsCount(Long mallId) {
		String sql = "select a.*,IFNULL(b.c,0) as goodsCount from {TABLE} a left join (select typeId,count(*) as c from "
				+ this.getRdsService().getTableName(CoreGoods.class)
				+ " where status=? group by typeId) b on a.id=b.typeId where a.mallId=? and a.valid=? order by a.orderIndex asc,goodsCount desc";
		List<CoreMallType> datas = this.getRdsService().gets(CoreMallType.class, sql,
				new Object[] { CoreGoods.STATUS_NORMAL, mallId, true });
		return datas;
	}

	/**
	 * 根据商品ID获取商品属性值
	 */
	public List<CoreGoodsMeta> getGoodsMetaListByGoods(Long goodsId) {
		String sql = "select a.*,b.name as typeMeta,c.value as typeMetaValue from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreMallTypeMeta.class) + " b on a.typeMetaId=b.id left join "
				+ this.getRdsService().getTableName(CoreMallTypeMetaValue.class)
				+ " c on a.typeMetaValueId=c.id where a.goodsId=? order by a.id asc";
		List<CoreGoodsMeta> datas = this.getRdsService().gets(CoreGoodsMeta.class, sql, new Object[] { goodsId });
		return datas;
	}

	/**
	 * 根据商品ID获取商品配图
	 */
	public List<CoreGoodsImg> getGoodsImgListByGoods(Long goodsId) {
		String sql = "select a.*,b.bucketKey as imgPath from {TABLE} a left join "
				+ this.getRdsService().getTableName(SystemFile.class)
				+ " b on a.fileId=b.id where a.goodsId=? order by a.main desc,a.id asc";
		List<CoreGoodsImg> datas = this.getRdsService().gets(CoreGoodsImg.class, sql, new Object[] { goodsId });
		return datas;
	}

	/**
	 * 判断商品编号是否重复
	 */
	private boolean isGoodsCodeRepeat(String code) {
		String sql = "select a.id from {TABLE} a where a.status=? and a.code=?";
		CoreGoods goods = this.getRdsService().get(CoreGoods.class, sql,
				new Object[] { CoreGoods.STATUS_NORMAL, code });
		if (null != goods) {
			return true;
		}
		return false;
	}

	/**
	 * 商品保存
	 */
	public void saveGoods(CoreGoods goods, List<CoreGoodsImg> imgs, List<CoreGoodsMeta> metas) {
		if (null == goods.getId() && this.isGoodsCodeRepeat(goods.getCode())) {
			throw new RuntimeException("商品编号重复");
		}
		goods.setStatus(CoreGoods.STATUS_NORMAL);
		if (null == goods.getCreateTime()) {
			goods.setCreateTime(DateUtil.getDateTimeString());
		}
		this.getRdsService().save(goods);
		for (CoreGoodsMeta data : metas) {
			data.setGoodsId(goods.getId());
			this.getRdsService().save(data);
		}
		for (CoreGoodsImg data : imgs) {
			data.setGoodsId(goods.getId());
			this.getRdsService().save(data);
		}
	}

	/**
	 * 修改商品状态
	 */
	public boolean updateGoodsStatus(Long id, int status) {
		String sql = "update {TABLE} set status=? where id=?";
		int count = this.getRdsService().update(CoreGoods.class, sql, new Object[] { status, id });
		return count == 0 ? false : true;
	}

	/**
	 * 将商品状态修改为历史
	 */
	public boolean changeGoodsToHistory(Long id) {
		String sql = "update {TABLE} set status=? where id=? and status=?";
		int count = this.getRdsService().update(CoreGoods.class, sql,
				new Object[] { CoreGoods.STATUS_HISTORY, id, CoreGoods.STATUS_NORMAL });
		return count == 0 ? false : true;
	}

	/**
	 * 获取最大的一个商品编号
	 */
	public int getMaxCode() {
		String date = DateUtil.getDateString();
		String sql = "select * from {TABLE} where createDate=?";
		CoreGoodsCode c = this.getRdsService().get(CoreGoodsCode.class, sql, new Object[] { date });
		if (null == c) {
			c = new CoreGoodsCode();
		}
		c.setNum(c.getNum() + 1);
		c.setCreateDate(date);
		this.getRdsService().save(c);
		return c.getNum();
	}

	/**
	 * 获取商品样品列表
	 */
	public List<CoreGoodsSample> getGoodsSampleListByStatus(int status) {
		String sql = "select a.*,b.mobile as userMobile,c.name as goodsName from {TABLE} a left join "
				+ this.getRdsService().getTableName(CoreUser.class) + " b on a.userId=b.id left join "
				+ this.getRdsService().getTableName(CoreGoods.class)
				+ " c on a.goodsId=c.id where a.status=? order by id desc";
		List<CoreGoodsSample> datas = this.getRdsService().gets(CoreGoodsSample.class, sql, new Object[] { status });
		return datas;
	}

	/**
	 * 获取用户申请中商品样品
	 */
	public CoreGoodsSample getUserApplyGoodsSample(Long userId, Long goodsId) {
		String sql = "select a.* from {TABLE} a where a.userId=? and a.goodsId=? and a.status=? order by id desc";
		CoreGoodsSample data = this.getRdsService().get(CoreGoodsSample.class, sql,
				new Object[] { userId, goodsId, CoreGoodsSample.STATUS_APPLY });
		return data;
	}

	/**
	 * 保存样品
	 */
	public void saveGoodsSample(CoreGoodsSample sample) {
		sample.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(sample);
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