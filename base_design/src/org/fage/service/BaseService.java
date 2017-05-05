package org.fage.service;


import java.io.Serializable;

import org.fage.dao.IBaseDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * 
 * @author Caizhf
 * @date 2017年4月22日下午4:38:50
 * @version v.0.1
 * <p>Description: BaseService抽取</p>
 *
 */
@Transactional(propagation=Propagation.REQUIRED)
public abstract class BaseService<T> implements IBaseService<T>{
	protected IBaseDao<T> iBaseDao;

	public IBaseDao<T> getiBaseDao() {
		return iBaseDao;
	}

	public void setiBaseDao(IBaseDao<T> iBaseDao) {
		this.iBaseDao = iBaseDao;
	}

	@Override
	public void add(T entity) {
		iBaseDao.save(entity);
	}

	@Override
	public void delete(T entity) {
		iBaseDao.delete(entity);
	}

	@Override
	public void delete(Serializable id) {
		iBaseDao.deleteById(id);
	}

	@Override
	public void saveOrUpdate(T entity) {
		iBaseDao.saveOrUpdate(entity);
	}

	@Override
	public T findById(Serializable id) {
		return iBaseDao.findById(id);
	}
	
	
}
