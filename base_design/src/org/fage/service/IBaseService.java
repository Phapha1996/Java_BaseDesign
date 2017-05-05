package org.fage.service;

import java.io.Serializable;
/**
 * 
 * @author Caizhf
 * @date 2017年4月22日下午4:24:27
 * @version v.0.1
 * <p>Description: 超类service提取，基础的增删改查应该是业务里面所需要的</p>
 *
 */
public interface IBaseService<T> {
	/**
	 * 
	 * @param entity
	 * <p>Description:增加一个实体<p/>
	 */
	public void add(T entity);
	/**
	 * 	
	 * @param entity
	 * <p>Description:根据实体删除<p/>
	 */
	public void delete(T entity);
	/**
	 * 
	 * @param id
	 * <p>Description:根据id删除<p/>
	 */
	public void delete(Serializable id);
	/**
	 * 
	 * @param entity
	 * <p>Description:根据实体增加或者修改<p/>
	 */
	public void saveOrUpdate(T entity);
	/**
	 * 
	 * @param id
	 * <p>Description:根据id查找实体<p/>
	 */
	public T findById(Serializable id);
}
