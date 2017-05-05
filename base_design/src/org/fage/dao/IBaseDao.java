package org.fage.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * 
 * @author Caizhf
 * @date 2017年4月21日下午8:18:13
 * @version v.0.1
 * <p>Description: 超Dao接口</p>
 *
 */
public interface IBaseDao<T> {
	/**
	 * 
	 * @param transitentInstance
	 * <p>Description:增加对象<p/>
	 */
	public Serializable save(T transitentInstance);
	/**
	 * 
	 * @param persistentInstance
	 * <p>Description:删除对象<p/>
	 */
	public void delete(T persistentInstance);
	/**
	 * 
	 * @param instance
	 * <p>Description:更新或者保存<p/>
	 */
	public void saveOrUpdate(T instance);
	/**
	 * 
	 * @param dedtachedInstance
	 * <p>Description:复制给定对象的状态到与之拥有相同id的持久对象，如果当前session没有该持久对象，则load一个，更新持久对象<p/>
	 * <p>如果没有id，则保存并且返回持久对象</p>
	 * <p>指定的这个对象，永远不会与session发生关联，这个和saveOrUpdate是不同的</p>
	 * <p>如果casecade="merge"，这个操作会级联到关联对象</p>
	 * <p>该方法是为了解决一个session有两个标识相同的实体的时候无法更新的问题</p>
	 */
	public T merge(T dedtachedInstance);
	/**
	 * 
	 * <p>Description:根据id查询<p/>
	 */
	public T findById(Serializable id);
	/**
	 * 
	 * @param id
	 * <p>Description:根据id删除<p/>
	 */
	public void deleteById(Serializable id);
	/**
	 * 
	 * @param instance 事例
	 * @param enableLike 是否对字符串执行like操作
	 * @param excludeProperty 要排除的对比属性
	 * <p>Description:根据事例查询<p/>
	 */
	public List<T> findByExample(T instance,boolean enableLike,String...excludeProperty);
	/**
	 * 
	 * @param propertyName 属性名
	 * @param value 属性值
	 * <p>Description:根据单个属性查询<p/>
	 */
	public List<T> findByProperty(String propertyName,Object value);
	/**
	 * 
	 * @param propertyNames 属性名字集合 
	 * @param values 属性值集合
	 * <p>Description:根据多个属性查询<p/>
	 */
	public List<T> findByProperties(String[] propertyNames,Object[] values);
	/**
	 * 
	 * @param pageNo 页码，从1开始
	 * @param pageSiz 每页的记录数
	 * @param propertyName 属性名
	 * @param value	属性值
	 * <p>Description:根据单个属性查询，带分页功能<p/>
	 */
	public List<T> findByProperty(int pageNo,int pageSize,String propertyName,Object value);
	/**
	 * 
	 * @param pageNo 页码从1开始
	 * @param pageSize 每页的记录数
	 * @param propertyNames 属性名集合
	 * @param values 属性值集合
	 * <p>Description:根据多个属性查询，带分页功能<p/>
	 */
	public List<T> findByProperties(int pageNo,int pageSize,String[] propertyNames,Object[] values);
	/**
	 * <p>Description:获取所有实例<p/>
	 */
	public List<T> findAll();
	/**
	 * 
	 * @param pageNo 页码，从1开始
	 * @param pageSize 每页的记录数
	 * @return
	 * <p>Description:<p/>
	 */
	public List<T> findAll(int pageNo,int pageSize);
	
	Session getSession();
}
