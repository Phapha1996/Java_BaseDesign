package org.fage.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 
 * @author Caizhf
 * @date 2017年4月21日下午8:57:31
 * @version v.0.1
 * <p>Description: 对BaseDao的基础实现</p>
 *
 */
public abstract class BaseDao<T> implements IBaseDao<T> {
	private static Log log = LogFactory.getLog(BaseDao.class);
	private Class<T> claz;
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	
	
	public BaseDao(){
		//获取泛型参数的具体类型
		this.claz = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	public void setSession(Session session){
		this.session = session;
	}
	//如果session没有被设置，那么直接返回线程内的session
	public Session getSession(){
		if(null==session)
			return sessionFactory.getCurrentSession();
		return this.session;
	}
	public Class<T> getClaz(){
		return this.claz;
	}
	public void setClaz(Class<T> claz){
		this.claz = claz;
	}
	
	
	@Override
	public Serializable save(T transitentInstance) {
		Serializable id = getSession().save(transitentInstance);
		log.info("-----------------save successful!!");
		return id;
	}
	
	
	@Override
	public void delete(T persistentInstance) {
		getSession().delete(persistentInstance);
		log.info("----------------delete successful!!");
	}
	
	
	@Override
	public void saveOrUpdate(T instance) {
		getSession().saveOrUpdate(instance);
	}
	
	
	@Override
	public T merge(T dedtachedInstance) {
		T obj = (T) getSession().merge(dedtachedInstance);
		log.info("-------------------merge successful!!");
		return obj;
	}
	
	
	@Override
	public T findById(Serializable id) {
		return getSession().get(claz, id);
	}
	
	
	@Override
	public void deleteById(Serializable id) {
		getSession().delete(findById(id));
	}
	
	/**
	 * 
	 * @param instance 事例
	 * @param enableLike 是否对字符串执行like操作
	 * @param excludeProperty 要排除的对比属性
	 * <p>Description:根据事例查询<p/>
	 */
	@Override
	public List<T> findByExample(T instance, boolean enableLike,
			String... excludeProperty) {
		Example exp = Example.create(instance);
		//遍历事例
		for(String ep:excludeProperty){
			exp.excludeProperty(ep);
		}
		//如果开启like
		if(enableLike)
			exp.enableLike();
		List<T> results = getSession().createCriteria(claz).add(exp).list();
		log.info("find by example successful" + results.size());
		return results;
	}
	
	/**
	 * 
	 * @param propertyName 属性名
	 * @param value 属性值
	 * <p>Description:根据单个属性查询<p/>
	 */
	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		List<T> results = getSession().createCriteria(claz).add(Restrictions.eq(propertyName, value)).list();
		log.info("find"+claz+"instance with property:" + propertyName + ",value:" + value);
		return results;
	}
	
	/**
	 * 
	 * @param propertyNames 属性名字集合 
	 * @param values 属性值集合
	 * <p>Description:根据多个属性查询<p/>
	 */
	@Override
	public List<T> findByProperties(String[] propertyNames, Object[] values) {
		log.info("---------------------findByProperties!!");
		Criteria criteria = getSession().createCriteria(claz);
		for(int i=0;i<propertyNames.length;i++){
			criteria.add(Restrictions.eq(propertyNames[i], values[i]));
		}
		return criteria.list();
	}
	
	/**
	 * @param pageNo
	 * @param pageSiz
	 * @param propertyName 属性名
	 * @param value 属性值
	 * <p>Description:根据单个属性查询，分页<p/>
	 */
	@Override
	public List<T> findByProperty(int pageNo, int pageSize, String propertyName,
			Object value) {
		log.info("find"+claz+"instance with property:" + propertyName + ",value:" + value);
		return getSession().createCriteria(claz).add(Restrictions.eq(propertyName, value))
		.setFirstResult((pageNo-1)*pageSize)
		.setMaxResults(pageSize).list();
	}
	
	/**
	 * 
	 * @param pageNo 页码从1开始
	 * @param pageSize 每页的记录数
	 * @param propertyNames 属性名集合
	 * @param values 属性值集合
	 * <p>Description:根据多个属性查询，带分页功能<p/>
	 */
	@Override
	public List<T> findByProperties(int pageNo, int pageSize,
			String[] propertyNames, Object[] values) {
		log.info("---------------------findByProperties!! pageList!!");
		Criteria criteria = getSession().createCriteria(claz);
		for(int i=0;i<propertyNames.length;i++){
			criteria.add(Restrictions.eq(propertyNames[i], values[i]));
		}
		criteria.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize);
		return criteria.list();
	}
	
	
	@Override
	public List<T> findAll() {
		log.info("-----------------------get all!!");
		return getSession().createCriteria(claz).list();
	}
	
	
	@Override
	public List<T> findAll(int pageNo, int pageSize) {
		log.info("---------------------get all pageList!!!");
		return getSession().createCriteria(claz).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
	}
	
	
}
