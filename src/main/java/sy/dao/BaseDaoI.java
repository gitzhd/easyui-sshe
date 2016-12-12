package sy.dao;

import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {
	
	public java.io.Serializable save(T o);
	public T get(String hsql);
	
	public T get(String hsql,Object[] params);
	
	public T get(String hsql, Map<String,Object> params);
	//删除
	public void delete(T t);
	public void update(T t);
	public void saveOrUpdate(T t);
	public List<T> findList(String hsql);
	public List<T> find(String hsql,Map<String,Object> params);
	public List<T> findPage(String hsql,Map<String,Object> params,Integer page,Integer rows);
	public List<T> findPage(String hsql,Integer page,Integer rows);
	public Integer count(String hsql);
	public Integer count(String hsql,Map<String,Object> params);
	//纯 sql  执行 
	public List<Map<String, Object>> findListMapBySql(String sql);

}
