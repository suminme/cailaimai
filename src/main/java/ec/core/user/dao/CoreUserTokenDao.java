package ec.core.user.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import ec.core.user.model.CoreUser;
import ec.core.user.model.CoreUserToken;
import framework.data.date.DateUtil;
import framework.service.rds.RdsService;

/**
 * @author SUMIN
 * @version 0.1
 * @since 2016-05-23
 */
@Component
public class CoreUserTokenDao {

	/**
	 * 创建Token
	 */
	public void createToken(CoreUserToken token) {
		token.setValid(true);
		token.setCreateTime(DateUtil.getDateTimeString());
		this.getRdsService().save(token);
	}

	/**
	 * 根据Token获取用户
	 */
	public CoreUser getUserByToken(String token) {
		String sql = "select * from {TABLE} where status=? and id in (select userId from "
				+ this.getRdsService().getTableName(CoreUserToken.class)
				+ " where valid=? and token=? and TIMESTAMPADD(SECOND,expiresIn,createTime)>?)";
		CoreUser user = this.getRdsService().get(CoreUser.class, sql,
				new Object[] { CoreUser.STATUS_NORMAL, true, token, DateUtil.getDateTimeString() });
		return user;
	}

	/**
	 * 删除用户Tokens
	 */
	public void deleteUserTokens(Long userId, String excludeToken) {
		String sql = "update {TABLE} set valid=? where valid=? and userId=? and token!=?";
		if (null == excludeToken || "".equals(excludeToken)) {
			excludeToken = "-1";
		}
		this.getRdsService().update(CoreUserToken.class, sql, new Object[] { false, true, userId, excludeToken });
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