package org.fage.dao.impl;

import org.fage.dao.BaseDao;
import org.fage.dao.in.UserDao;
import org.fage.domain.User;
import org.springframework.stereotype.Repository;
/**
 * 
 * @author Caizhf
 * @date 2017年4月22日下午3:00:15
 * @version v.0.1
 * <p>Description: 继承并且实现超类接口</p>
 *
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao{}
