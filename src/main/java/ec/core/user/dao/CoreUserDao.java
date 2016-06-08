package ec.core.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserAddressData;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.model.CoreUserMaterial;
import ec.system.file.model.SystemFile;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Component
public class CoreUserDao {

	/**
	 * 获取用户列表
	 */
	public List<CoreUser> getUserList() {
		String sql = "select * from {TABLE} where status=? order by id desc";
		List<CoreUser> datas = this.getRdsService().gets(CoreUser.class, sql, new Object[] { CoreUser.STATUS_NORMAL });
		return datas;
	}

	/**
	 * 获取管理员列表
	 */
	public List<CoreUser> getAdminUserList() {
		String sql = "select * from {TABLE} where status=? and adminRank>? order by id desc";
		List<CoreUser> datas = this.getRdsService().gets(CoreUser.class, sql,
				new Object[] { CoreUser.STATUS_NORMAL, 0 });
		return datas;
	}

	/**
	 * 通过ID查询用户
	 */
	public CoreUser getUserById(Long id) {
		String sql = "select * from {TABLE} where status=? and id=?";
		CoreUser user = this.getRdsService().get(CoreUser.class, sql, new Object[] { CoreUser.STATUS_NORMAL, id });
		return user;
	}

	/**
	 * 通过手机号查询用户
	 */
	public CoreUser getUserByMobile(String mobile) {
		String sql = "select * from {TABLE} where status=? and mobile=?";
		CoreUser user = this.getRdsService().get(CoreUser.class, sql, new Object[] { CoreUser.STATUS_NORMAL, mobile });
		return user;
	}

	/**
	 * 创建用户
	 */
	public void createUser(CoreUser user) {
		user.setStatus(CoreUser.STATUS_NORMAL);
		user.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(user);
	}

	/**
	 * 保存用户信息
	 */
	public boolean updateUser(CoreUser user) {
		int c = this.getRdsService().save(user);
		return c > 0 ? true : false;
	}

	/**
	 * 根据ID获取地址信息
	 */
	public CoreUserAddress getAddressByUserAndId(Long userId, Long id) {
		String sql = "select a.* from {TABLE} a where a.userId=? and a.id=?";
		CoreUserAddress data = this.getRdsService().get(CoreUserAddress.class, sql, new Object[] { userId, id });
		return data;
	}

	/**
	 * 根据用户获取素材信息
	 */
	public List<CoreUserMaterial> getMaterialListByUser(Long userId) {
		String sql = "select a.* from {TABLE} a where a.valid=? and a.userId=? order by a.id desc";
		List<CoreUserMaterial> datas = this.getRdsService().gets(CoreUserMaterial.class, sql,
				new Object[] { true, userId });
		return datas;
	}

	/**
	 * 根据用户获取地址信息
	 */
	public List<CoreUserAddress> getAddressListByUser(Long userId) {
		String sql = "select a.* from {TABLE} a where a.valid=? and a.userId=? order by a.id desc";
		List<CoreUserAddress> datas = this.getRdsService().gets(CoreUserAddress.class, sql,
				new Object[] { true, userId });
		return datas;
	}

	/**
	 * 根据ID删除地址信息
	 */
	public boolean deleteAddressByUserAndId(Long userId, Long id) {
		String sql = "update {TABLE} set valid=? where userId=? and id=?";
		int c = this.getRdsService().update(CoreUserAddress.class, sql, new Object[] { false, userId, id });
		return c > 0 ? true : false;
	}

	/**
	 * 保存素材
	 */
	public void saveMaterial(Long userId, CoreUserMaterial data) {
		data.setValid(true);
		data.setUserId(userId);
		data.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(data);
	}

	/**
	 * 保存地址信息
	 */
	public void saveAddress(Long userId, CoreUserAddress data) {
		data.setValid(true);
		data.setUserId(userId);
		data.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(data);
	}

	/**
	 * 更新地址信息
	 */
	public void updateAddress(Long userId, CoreUserAddress data) {
		CoreUserAddress db = this.getAddressByUserAndId(userId, data.getId());
		if (null == db) {
			return;
		}
		data.setValid(db.isValid());
		data.setUserId(db.getUserId());
		data.setCreateTime(db.getCreateTime());
		this.getRdsService().save(data);
	}

	/**
	 * 获取收货地址数据
	 */
	public List<CoreUserAddressData> getAddressData(Long pid) {
		String sql = "select * from {TABLE} where pid=? and valid=?";
		List<CoreUserAddressData> datas = this.getRdsService().gets(CoreUserAddressData.class, sql,
				new Object[] { pid, true });
		return datas;
	}

	/**
	 * 根据ID获取素材信息
	 */
	public CoreUserMaterial getMaterialByUserAndId(Long userId, Long id) {
		String sql = "select a.*,b.bucketKey as filePath from {TABLE} a left join "
				+ this.getRdsService().getTableName(SystemFile.class) + " b on a.fileId=b.id where a.userId=? and a.id=?";
		CoreUserMaterial data = this.getRdsService().get(CoreUserMaterial.class, sql, new Object[] { userId, id });
		return data;
	}

	/**
	 * 根据ID获取发票信息
	 */
	public CoreUserInvoice getInvoiceByUserAndId(Long userId, Long id) {
		String sql = "select a.* from {TABLE} a where a.userId=? and a.id=?";
		CoreUserInvoice data = this.getRdsService().get(CoreUserInvoice.class, sql, new Object[] { userId, id });
		return data;
	}

	/**
	 * 根据用户获取发票信息
	 */
	public List<CoreUserInvoice> getInvoiceListByUser(Long userId) {
		String sql = "select a.* from {TABLE} a where a.valid=? and a.userId=? order by a.id desc";
		List<CoreUserInvoice> datas = this.getRdsService().gets(CoreUserInvoice.class, sql,
				new Object[] { true, userId });
		return datas;
	}

	/**
	 * 根据ID删除发票信息
	 */
	public boolean deleteInvoiceByUserAndId(Long userId, Long id) {
		String sql = "update {TABLE} set valid=? where userId=? and id=?";
		int c = this.getRdsService().update(CoreUserInvoice.class, sql, new Object[] { false, userId, id });
		return c > 0 ? true : false;
	}

	/**
	 * 保存发票信息
	 */
	public void saveInvoice(Long userId, CoreUserInvoice data) {
		data.setValid(true);
		data.setUserId(userId);
		data.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(data);
	}

	/**
	 * 更新发票信息
	 */
	public void updateInvoice(Long userId, CoreUserInvoice data) {
		CoreUserInvoice db = this.getInvoiceByUserAndId(userId, data.getId());
		if (null == db) {
			return;
		}
		data.setValid(db.isValid());
		data.setUserId(db.getUserId());
		data.setCreateTime(db.getCreateTime());
		this.getRdsService().save(data);
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