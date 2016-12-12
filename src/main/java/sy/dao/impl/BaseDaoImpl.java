package sy.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import sy.dao.BaseDaoI;
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Serializable save(T o) {
		
		return this.sessionFactory.getCurrentSession().save(o);
	}
	
	private Session getCurrentSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	
	@Override
	public T get(String hsql) {
		
		Query q= this.getCurrentSession().createQuery(hsql);
		List<T> l =q.list();
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
	}
	@Override
	public T get(String hsql, Object[] params) {
		Query q = this.getCurrentSession().createQuery(hsql);
		if(params!=null && params.length>0){
			for(int i=0;i<params.length;i++){
				q.setParameter(i, params[i]);

			}
		}
		List<T> l =q.list();
		if(l!=null&&l.size()>0){
			return l.get(0);
		}
		return null;
	}
	@Override
	public T get(String hsql, Map<String, Object> params) {
		Query q= this.getCurrentSession().createQuery(hsql);
		if(params!=null && !params.isEmpty()){
			for(String key : params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		List<T> list = q.list();
		if(list!=null && list.size()>0){
			return   list.get(0);
		}
		return null;
	}
	 
	
	public void delete(T t) {
		
		this.getCurrentSession().delete(t);
		
	}
	@Override
	public void update(T t) {
		this.getCurrentSession().update(t);
		
	}
	@Override
	public void saveOrUpdate(T t) {
		this.getCurrentSession().saveOrUpdate(t);
	}
	@Override
	public List<T> findList(String hsql) {
		Query q = this.getCurrentSession().createQuery(hsql);
		return q.list();
	}
	@Override
	public List<T> find(String hsql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hsql);
		if(params != null && !params.isEmpty()){
		for(String key : params.keySet()){
			q.setParameter(key, params.get(key));
		}
	}
		
		return q.list();
	}
	@Override
	public List<T> findPage(String hsql, Map<String, Object> params, Integer page,
			Integer rows) {
		Query q = this.getCurrentSession().createQuery(hsql);
		if(params != null && !params.isEmpty()){
			for(String key : params.keySet()){
			q.setParameter(key, params.get(key));
			}
		}
		
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
		
	}
	@Override
	public List<T> findPage(String hsql, Integer page, Integer rows) {
		Query q = this.getCurrentSession().createQuery(hsql);
		
		return q.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}
	@Override
	public Integer count(String hsql) {
		Query q = this.getCurrentSession().createQuery(hsql);
		
 		return (int)((long)q.uniqueResult());
	}
	@Override
	public Integer count(String hsql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hsql);
		if(params != null && !params.isEmpty()){
			for(String key : params.keySet()){
			q.setParameter(key, params.get(key));
			}
		}
 		return (int)((long)q.uniqueResult());
	}
	
	/**
	   * 获取list<map>集合
	   * 自定义SQL 语句，返回结果为list<map>
	   * @param Hql
	   * @return
	   * @author 
	   */
	  @SuppressWarnings("unchecked")
	  public List<Map<String, Object>> findListMapBySql(String sql) {
	    try {
	    	
	      List<Map<String, Object>> list = this.getCurrentSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
	      return list;
	    } catch (Exception e) {
	      //log.error("获取list<map>集合出现异常:" + e);
	      e.printStackTrace();
	      return null;
	    }  
	  }
	

}
