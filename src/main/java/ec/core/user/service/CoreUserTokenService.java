package ec.core.user.service;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.core.user.dao.CoreUserTokenDao;
import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserToken;
import framework.data.data.DataUtil;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Service
@Transactional
public class CoreUserTokenService {

	/**
	 * 
	 */
	public static final String aesKey = "ec";

	/**
	 * 
	 */
	public static final int expiresIn = 2592000;

	/**
	 * 根据Token获取用户
	 */
	public CoreUser getUserByToken(String token) {
		CoreUser user = this.getCoreUserTokenDao().getUserByToken(token);
		return user;
	}

	/**
	 * 创建Token
	 */
	public CoreUserToken createToken(Long userId, String ip) {
		CoreUserToken token = new CoreUserToken();
		token.setCreaterIp(ip);
		token.setUserId(userId);
		token.setExpiresIn(expiresIn);
		token.setToken(this.createTokenStr(userId));
		this.getCoreUserTokenDao().createToken(token);
		return token;
	}

	/**
	 * 创建TokenStr
	 */
	private String createTokenStr(Long userId) {
		String tokenStr = DataUtil.aesEncrypt(userId.toString() + String.valueOf(new Date().getTime()), aesKey);
		return tokenStr;
	}

	/**
	 * 删除用户Tokens
	 */
	public void deleteUserTokens(Long userId, String excludeToken) {
		this.getCoreUserTokenDao().deleteUserTokens(userId, excludeToken);
	}

	/**
	 * 
	 */
	@Resource
	private CoreUserTokenDao coreUserTokenDao;

	public CoreUserTokenDao getCoreUserTokenDao() {
		return coreUserTokenDao;
	}

	public void setCoreUserTokenDao(CoreUserTokenDao coreUserTokenDao) {
		this.coreUserTokenDao = coreUserTokenDao;
	}
}