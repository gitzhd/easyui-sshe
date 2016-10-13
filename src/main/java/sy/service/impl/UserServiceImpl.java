package sy.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.UserDaoI;
import sy.dao.impl.BaseDaoImpl;
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

}
