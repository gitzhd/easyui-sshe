package sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.BaseDaoI;
import sy.model.Tmenu;
import sy.model.Tuser;
import sy.service.RepairServiceI;
import sy.util.Encrypt;
@Service("repairService")
public class RepairServiceImpl implements RepairServiceI {
	
	
	
	private BaseDaoI<Tmenu> repairDao;
	private BaseDaoI<Tmenu> menuDao;
	private BaseDaoI<Tuser>	userDao;
	
 
	public BaseDaoI<Tmenu> getMenuDao() {
		return menuDao;
	}
 
	@Autowired
	public void setMenuDao(BaseDaoI<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}

 
	public BaseDaoI<Tuser> getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(BaseDaoI<Tuser> userDao) {
		this.userDao = userDao;
	}

	
	public void repairMenu() {
		 Tmenu root = new Tmenu();
		 root.setId("0");
		 root.setText("首页");
		 root.setUrl("https://");
		 menuDao.saveOrUpdate(root);
		 
		 Tmenu xtgl = new Tmenu();
		 xtgl.setId("1");
		 xtgl.setTmenu(root);
		 xtgl.setText("系统管理");
		 xtgl.setUrl("");
		 menuDao.saveOrUpdate(xtgl);
		 	
		 //用户管理
		 Tmenu yhgl = new Tmenu();
		 yhgl.setId("2");
		 yhgl.setTmenu(xtgl);
		 yhgl.setText("用户管理");
		 yhgl.setUrl("/admin/yhgl.jsp");
		 menuDao.saveOrUpdate(yhgl);
		 
		 //角色管理 权限管理
		 Tmenu qxgl = new Tmenu();
		 qxgl.setId("3");
		 qxgl.setTmenu(xtgl);
		 qxgl.setText("权限管理");
		 qxgl.setUrl("");
		 menuDao.saveOrUpdate(qxgl);
		 //菜单管理
		 Tmenu cdgl = new Tmenu();
		 cdgl.setId("4");
		 cdgl.setTmenu(xtgl);
		 cdgl.setText("菜单管理");
		 cdgl.setUrl("");
		 menuDao.saveOrUpdate(cdgl);
		 // bug 管理 
		 Tmenu bggl = new Tmenu();
		 bggl.setId("5");
		 bggl.setTmenu(xtgl);
		 bggl.setText("BUG管理");
		 bggl.setUrl("");
		 menuDao.saveOrUpdate(bggl);
		 
	}
	
	public void repairUser(){
		Map<String,Object> m = new HashMap<String, Object>();
		m.put("name", "admin");
		Tuser t = userDao.get(" from Tuser t where t.name=:name and t.id !='0'",m);
		if(t!=null){
			t.setName("namechange");
			//userDao.saveOrUpdate(t);
		}
		Tuser user = new Tuser();
		user.setId("0");
		user.setName("admin");
		user.setPwd(Encrypt.e("admin"));
		user.setModifydatetime(new Date());
		userDao.saveOrUpdate(user);
		
		
	}

	@Override
	public void repair() {
		this.repairMenu();
		this.repairUser();
		
	}
		
}
