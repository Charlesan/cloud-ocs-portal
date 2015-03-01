package com.cloud.ocs.portal.core.user.service;

import com.cloud.ocs.portal.common.bean.User;
import com.cloud.ocs.portal.core.user.constant.LoginStatus;

/**
 * 系统用户service接口
 * @author Wang Chao
 *
 * @date 2014-12-10 下午7:35:42
 *
 */
public interface UserService {
	public User findUserByAccount(String accountId);
	public User updateLoginDate(User user);
	public LoginStatus checkLogin(String accountId, String accountPassword);
}
