package ec.core.user.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.core.user.dao.CoreUserDao;
import ec.core.user.exception.CoreUserExistException;
import ec.core.user.exception.CoreUserNotExistException;
import ec.core.user.exception.CoreUserValidateException;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserAddress;
import ec.core.user.model.CoreUserAddressData;
import ec.core.user.model.CoreUserInvoice;
import ec.core.user.model.CoreUserMaterial;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
@Transactional
public class CoreUserService {

	/**
	 * 获取用户列表
	 */
	public List<CoreUser> getUserList() {
		List<CoreUser> datas = this.getCoreUserDao().getUserList();
		return datas;
	}

	/**
	 * 获取管理员列表
	 */
	public List<CoreUser> getAdminUserList() {
		List<CoreUser> datas = this.getCoreUserDao().getAdminUserList();
		return datas;
	}

	/**
	 * 通过ID查询用户
	 */
	public CoreUser getUserById(Long id) {
		CoreUser user = this.getCoreUserDao().getUserById(id);
		return user;
	}

	/**
	 * 通过手机号查询用户
	 */
	public CoreUser getUserByMobile(String mobile) {
		CoreUser user = this.getCoreUserDao().getUserByMobile(mobile);
		return user;
	}

	/**
	 * 根据用户名密码获取用户
	 */
	public CoreUser getAndCheckUserByUsernameAndPassword(String username, String password)
			throws CoreUserNotExistException, CoreUserValidateException {
		CoreUser user = this.getUserByMobile(username);
		if (null == user) {
			throw new CoreUserNotExistException();
		}
		if (!user.getPassword().equals(password)) {
			throw new CoreUserValidateException();
		}
		return user;
	}

	/**
	 * 创建用户
	 */
	public void createUser(CoreUser user) throws CoreUserExistException {
		if (null != user.getMobile() && null != this.getUserByMobile(user.getMobile())) {
			throw new CoreUserExistException();
		}
		this.getCoreUserDao().createUser(user);
	}

	/**
	 * 更新用户账户信息
	 */
	public void updateUserProfile(Long userId, CoreUser user) {
		CoreUser db = this.getUserById(userId);
		db.setName(user.getName());
		db.setEmail(user.getEmail());
		this.getCoreUserDao().updateUser(db);
	}

	/**
	 * 更新用户安全信息
	 */
	public void updateUserSecurity(Long userId, String oldPassword, String password, String currentToken)
			throws CoreUserValidateException {
		CoreUser db = this.getUserById(userId);
		if (!db.getPassword().equals(oldPassword)) {
			throw new CoreUserValidateException();
		}
		db.setPassword(password);
		boolean success = this.getCoreUserDao().updateUser(db);
		if (success) {
			this.getCoreUserTokenService().deleteUserTokens(userId, currentToken);
		}
	}

	/**
	 * 重置密码
	 */
	public void resetPassword(String mobile, String password) throws RuntimeException {
		CoreUser db = this.getUserByMobile(mobile);
		db.setPassword(password);
		boolean success = this.getCoreUserDao().updateUser(db);
		if (success) {
			this.getCoreUserTokenService().deleteUserTokens(db.getId(), null);
		}
	}

	/**
	 * 根据ID获取地址信息
	 */
	public CoreUserAddress getAddressByUserAndId(Long userId, Long id) {
		CoreUserAddress data = this.getCoreUserDao().getAddressByUserAndId(userId, id);
		return data;
	}
	
	/**
	 * 根据用户获取素材信息
	 */
	public List<CoreUserMaterial> getMaterialListByUser(Long userId) {
		List<CoreUserMaterial> datas = this.getCoreUserDao().getMaterialListByUser(userId);
		return datas;
	}

	/**
	 * 根据用户获取地址信息
	 */
	public List<CoreUserAddress> getAddressListByUser(Long userId) {
		List<CoreUserAddress> datas = this.getCoreUserDao().getAddressListByUser(userId);
		return datas;
	}

	/**
	 * 根据ID删除地址信息
	 */
	public boolean deleteAddressByUserAndId(Long userId, Long id) {
		return this.getCoreUserDao().deleteAddressByUserAndId(userId, id);
	}

	/**
	 * 保存素材
	 */
	public void saveMaterial(Long userId, CoreUserMaterial data) {
		this.getCoreUserDao().saveMaterial(userId, data);
	}

	/**
	 * 保存地址信息
	 */
	public void saveAddress(Long userId, CoreUserAddress data) {
		this.getCoreUserDao().saveAddress(userId, data);
	}

	/**
	 * 更新地址信息
	 */
	public void updateAddress(Long userId, CoreUserAddress data) {
		this.getCoreUserDao().updateAddress(userId, data);
	}

	/**
	 * 获取收货地址数据
	 */
	public List<CoreUserAddressData> getAddressData(Long pid) {
		List<CoreUserAddressData> datas = this.getCoreUserDao().getAddressData(pid);
		return datas;
	}

	/**
	 * 根据ID获取发票信息
	 */
	public CoreUserInvoice getInvoiceByUserAndId(Long userId, Long id) {
		CoreUserInvoice data = this.getCoreUserDao().getInvoiceByUserAndId(userId, id);
		return data;
	}

	/**
	 * 根据ID获取发票信息
	 */
	public CoreUserMaterial getMaterialByUserAndId(Long userId, Long id) {
		CoreUserMaterial data = this.getCoreUserDao().getMaterialByUserAndId(userId, id);
		return data;
	}

	/**
	 * 根据用户获取发票信息
	 */
	public List<CoreUserInvoice> getInvoiceListByUser(Long userId) {
		List<CoreUserInvoice> datas = this.getCoreUserDao().getInvoiceListByUser(userId);
		return datas;
	}

	/**
	 * 根据ID删除发票信息
	 */
	public boolean deleteInvoiceByUserAndId(Long userId, Long id) {
		return this.getCoreUserDao().deleteInvoiceByUserAndId(userId, id);
	}

	/**
	 * 保存发票信息
	 */
	public void saveInvoice(Long userId, CoreUserInvoice data) {
		this.getCoreUserDao().saveInvoice(userId, data);
	}

	/**
	 * 更新发票信息
	 */
	public void updateInvoice(Long userId, CoreUserInvoice data) {
		this.getCoreUserDao().updateInvoice(userId, data);
	}

	/**
	 * 
	 */
	@Resource
	private CoreUserDao coreUserDao;

	/**
	 * 
	 */
	@Resource
	private CoreUserTokenService coreUserTokenService;

	public CoreUserDao getCoreUserDao() {
		return coreUserDao;
	}

	public void setCoreUserDao(CoreUserDao coreUserDao) {
		this.coreUserDao = coreUserDao;
	}

	public CoreUserTokenService getCoreUserTokenService() {
		return coreUserTokenService;
	}

	public void setCoreUserTokenService(CoreUserTokenService coreUserTokenService) {
		this.coreUserTokenService = coreUserTokenService;
	}
}