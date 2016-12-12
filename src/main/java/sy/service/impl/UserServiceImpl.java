package sy.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.UserDaoI;
import sy.dao.impl.BaseDaoImpl;
import sy.model.DataGrid;
import sy.model.Tuser;
import sy.pageModel.User;
import sy.service.UserServiceI;
import sy.util.Encrypt;

@Service("userService")
public class UserServiceImpl implements UserServiceI {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	private BaseDaoImpl<Tuser> userDao;
	 
	public BaseDaoImpl<Tuser> getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(BaseDaoImpl<Tuser> userDao) {
		this.userDao = userDao;
	}

	public void save(User user) {
		 Tuser t = new Tuser();
		
		/* t.setName(user.getName());
		 t.setPwd(user.getPwd());*/
		 BeanUtils.copyProperties(user, t,new String[]{"pwd"});
		 t.setId(UUID.randomUUID().toString());
		 t.setPwd(Encrypt.e(user.getPwd()));
		 t.setCreatedatetime(new Date());
		 userDao.save(t);
		
	}

	@Override
	public User login(User user) {
	//	Tuser t =  (Tuser) userDao.get(" from Tuser t where t.name='"+user.getName()+"' and t.pwd='"+Encrypt.e(user.getPwd())+"'");
		//String hsql = " from Tuser t where t.name=? and t.pwd=? ";
	//	Tuser t = (Tuser) userDao.get(hsql, new Object[]{user.getName(),Encrypt.e(user.getPwd())});
		String hsql =" from Tuser t where t.name= :name and t.pwd= :pwd";
		Map<String,Object> params = new HashMap();
		params.put("name", user.getName());
		params.put("pwd", Encrypt.e(user.getPwd()));
		Tuser t = (Tuser) userDao.get(hsql, params);
		if(t!=null){
			return user;
		}else{
			return null;
		}
		
	}
	@Override
	public DataGrid dataGrid(User user) {
		 
		DataGrid dg = new DataGrid();
		String hsql = " from Tuser t";
		Map<String,Object> params = new HashMap<String,Object>();
		hsql = addWhere(user, hsql, params);
		logger.info("hsql1:"+hsql+user.getName());
		String totalHql = " select count(*) " + hsql;
		hsql = addOrder(user, hsql);
		logger.info("hsql2:"+hsql);
		List<Tuser> lt = userDao.findPage(hsql, params, user.getPage(), user.getRows());
		List<User> lu = new ArrayList<User>();
		changeModel(lt, lu);
		int pageCount = userDao.count(totalHql,params);
		logger.info("pageCount"+pageCount);
		dg.setTotal(pageCount);
		dg.setRows(lu);
		return dg;
	}
	private void changeModel(List<Tuser> lt, List<User> lu) {
		if(lt != null && lt.size()>0){
			for(Tuser t :lt){
				User u = new User();
				BeanUtils.copyProperties(t, u);
				lu.add(u);
			}
		}
	}
	private String addOrder(User user, String hsql) {
		if(user.getSort()!=null && user.getOrder()!=null){
			hsql +=" order by "+user.getSort()+" "+" "+user.getOrder();
		}
		return hsql;
	}
	private String addWhere(User user, String hsql, Map<String, Object> params) {
		if(user.getName()!=null && !user.getName().trim().equals("")){
			hsql +=" where t.name like :name";
			params.put("name","%%"+ user.getName().trim()+"%%");
		}
		return hsql;
	}
	 

}
