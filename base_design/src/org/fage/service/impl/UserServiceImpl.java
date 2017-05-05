package org.fage.service.impl;

import javax.annotation.Resource;

import org.fage.dao.IBaseDao;
import org.fage.dao.in.UserDao;
import org.fage.domain.User;
import org.fage.service.BaseService;
import org.fage.service.in.UserService;
import org.springframework.stereotype.Service;
/**
 * 
 * @author Caizhf
 * @date 2017年4月22日下午4:47:07
 * @version v.0.1
 * <p>Description: </p>
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService{
	private UserDao userDao;
	//引用指的相同的userDao，这样既不损失新增加的方法，也不丢失继承下来的方法
	@Resource(name="userDao")
	public void setUserDao(UserDao userDao) {
		super.setiBaseDao(userDao);
		this.userDao = userDao;
	}
}
