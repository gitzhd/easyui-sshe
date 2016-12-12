package sy.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import sy.dao.impl.BaseDaoImpl;
import sy.model.Tmenu;
import sy.service.AutoCompleteServiceI;

@Service("autoComplete")
public class AutoCompleteServiceImpl  implements AutoCompleteServiceI{
	
	private Logger logger = Logger.getLogger(MenuServiceImpl.class);
	private BaseDaoImpl autoCompleteDao;
	
	 
	public BaseDaoImpl getAutoCompleteDao() {
		return autoCompleteDao;
	}
	@Autowired
	public void setAutoCompleteDao(BaseDaoImpl autoCompleteDao) {
		this.autoCompleteDao = autoCompleteDao;
	}

	@Override
	public String findBrandInfoBybrandName(String brandName) {
		JSONArray jsonArray=null;  
        String sql=" select *  from shop_brand t  where 1=1 ";  
        if(brandName!=null && !"".equals(brandName))  
        {  
            sql+=" and t.brandName like '%"+brandName+"%' ";  
        }  
        System.out.println(sql);  
        List<Map> objList=autoCompleteDao.findListMapBySql(sql);
        if (objList!=null&&objList.size()>0) {  
            jsonArray=new JSONArray();  
            for (int i = 0; i < objList.size(); i++) {  
            	Map map= objList.get(i);  
            	if(map.containsKey("brandName")&& map.containsKey("brandId")){
             		Map brandMap = new HashMap();
             		brandMap.put("brandId", map.get("brandId").toString());
             		brandMap.put("brandName", map.get("brandName").toString());
             		jsonArray.add(brandMap);	
             	}
            }  
        }  
        return jsonArray.toString();  
	}
	
	 /** 
     * 获取自动补全功能数据 
     */  
    public String getBrandList(){  
		 JSONArray jsonArray=null;  
         String sql=" select *  from shop_brand t  where 1=1 ";
         System.out.println(sql);  
         List<Map> objList=autoCompleteDao.findListMapBySql(sql);
         if (objList!=null&&objList.size()>0) {  
             jsonArray=new JSONArray();  
             for (int i = 0; i < objList.size(); i++) {  
             	Map map= objList.get(i);  
             	if(map.containsKey("brandName")&& map.containsKey("brandId")){
             		Map brandMap = new HashMap();
             		brandMap.put("brandId", map.get("brandId").toString());
             		brandMap.put("brandName", map.get("brandName").toString());
             		jsonArray.add(brandMap);	
             	}
                 
             }  
         }  
         return jsonArray.toString();  
    }  
	
}
