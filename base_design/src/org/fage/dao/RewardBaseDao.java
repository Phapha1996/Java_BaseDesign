package org.fage.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.query.Query;
/**
 * 
 * @author Caizhf
 * @date 2017年4月22日下午2:51:26
 * @version v.0.1
 * <p>Description: 多余的一个增强类,展示使用了query接口的用法</p>
 *
 */
public abstract class RewardBaseDao<T> extends BaseDao<T> {
	
	/**
	 * 
	 * @param hql 使用占位符的hql
	 * @param values 参数占位例如"???"
	 * <p>Description:获取HQL结果行数<p/>
	 */
	protected int getCount(String hql,final Object...values){
		final String sql = "select count(*) from " + hql;
		Query q = getSession().createQuery(sql);
		int i = 0;
		if(values!=null)
			for(Object o:values){
				q.setParameter(i, o);
				i++;
			}
		return ((Long)q.uniqueResult()).intValue();
	}
	
	/**
	 * 
	 * @param hql 根据几个参数进行占位符查询
	 * @param values 参数
	 * @return
	 * <p>Description:<p/>
	 */
	protected List query(String hql,Object... values){
		Query q = getSession().createQuery(hql);
		int i = 0;
		if(values!=null)
			for(Object o:values){
				q.setParameter(i,o);
				i++;
			}
		return q.list();
	}
	
	
	/**
	 * 
	 * @param hql 使用命名参数的hql
	 * @param propertyObject 封装了命名参数及值的bean对象
	 * <p>Description:<p/>
	 */
	protected List queryByParamBean(String hql,Object propertyObject){
		Query q = getSession().createQuery(hql);
		q.setProperties(propertyObject);
		return q.list();
	}
	
	/**
	 * 
	 * @param hql 使用了命名参数的hql
	 * @param map 封装了命名参数及值的map对象
	 * @return
	 * <p>Description:<p/>
	 */
	protected List query(String hql,Map map){
		Query q = getSession().createQuery(hql);
		q.setProperties(map);
		return q.list();
	}
	
	/**
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 * <p>Description:带分页功能的重载版本<p/>
	 */
	protected List query(final String hql,final int pageNo,final int pageSize,final Object...values){
		Query q = getSession().createQuery(hql);
		int i = 0;
		if(values!=null)
			for(Object o:values){
				q.setParameter(i, o);
			}
		q.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize);
		return q.list();
	}
	
	/**
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param propertyObject
	 * @return
	 * <p>Description:带分页的重载版本<p/>
	 */
	protected List queryByParanBean(String hql,int pageNo,int pageSize,Object propertyObject){
		Query q = getSession().createQuery(hql);
		q.setProperties(propertyObject);
		q.setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize);
		return q.list();
	}
	
	/**
	 * 
	 * 执行非查询类HQL，例如删除、增加、修改等等
	 * @param hql 使用占位符hql
	 * @param values 查询参数
	 * @return
	 * <p>Description:<p/>
	 */
	protected int execute(final String hql,final Object...values){
		Query q = getSession().createQuery(hql);
		
		if(null!=values)
		for(int i=0;i<values.length;i++){
			q.setParameter(i, values[i]);
		}
		return q.executeUpdate();
	}
	
	/**
	 * 执行非查询类hql
	 * @param hql 使用命名参数的hql
	 * @param propertyObject 封装了命名参数及值的bean对象
	 * @return
	 * <p>Description:<p/>
	 */
	protected int executeByParamBean(String hql,Object propertyObject){
		Query q = getSession().createQuery(hql);
		q.setProperties(propertyObject);
		return q.executeUpdate();
	}
	
	/**
	 * 执行非查询类HQL
	 * @param hql 使用命名参数的hq
	 * @param map 封装了命名参数及值的Map对象
	 * <p>Description:<p/>
	 */
	protected int execute(String hql,Map map){
		Query q = getSession().createQuery(hql);
		q.setProperties(map);
		return q.executeUpdate();
	}
	
	
	/**
	 * @param criterion
	 * <p>Description:可传入Criterion用于查询<p/>
	 */
	protected List<T> findByCriteria(Criterion...criterion){
		Criteria crit = getSession().createCriteria(getClaz());
		for(Criterion c:criterion){
			crit.add(c);
		}
		return crit.list();
	}
	
}
