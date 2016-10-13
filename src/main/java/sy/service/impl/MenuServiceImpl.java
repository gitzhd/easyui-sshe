package sy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sy.dao.impl.BaseDaoImpl;
import sy.model.Tmenu;
import sy.pageModel.Menu;
import sy.service.MenuServiceI;
@Service("menuService")
public class MenuServiceImpl implements MenuServiceI {
	
	
	private Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private BaseDaoImpl<Tmenu> menuDao;
	
	public BaseDaoImpl<Tmenu> getMenuDao() {
		return menuDao;
	}
	@Autowired
	public void setMenuDao(BaseDaoImpl<Tmenu> menuDao) {
		this.menuDao = menuDao;
	}
	@Override
	public List<Menu> getTree(String id) {
		List<Menu> ml = new ArrayList<Menu>();
		Map<String,Object> params = new HashMap<String,Object>();
		String hsql = null;
		logger.info("id=="+id);
		if(id==null || id.equals("")){
			hsql = "from Tmenu t where t.tmenu is null";
		}else{
			hsql = "from Tmenu t where t.tmenu.id= :id";
			params.put("id",id);
		}
		logger.info("hsql="+hsql);
		List<Tmenu> l = menuDao.find(hsql, params);
		if(l!=null && l.size()>0){
		for(Tmenu t : l){
			Menu m = new Menu();
			Map<String,Object> attributes = new HashMap<String,Object>();
			
			BeanUtils.copyProperties(t, m);
			attributes.put("url", t.getUrl());
			m.setAttributes(attributes);
			
			Set<Tmenu> sets = t.getTmenus();
			if(sets!=null && !sets.isEmpty()){
				m.setState("closed");
			}else{
				m.setState("open");
			}
			
			ml.add(m);
		}
		}
		
		return  ml;
	}
	
 
}
