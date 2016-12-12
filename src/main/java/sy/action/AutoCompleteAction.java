package sy.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import sy.service.AutoCompleteServiceI;


@Namespace("/")
@Action("autoCompleteAction")
public class AutoCompleteAction extends BaseAction{
	private static final Logger logger = Logger.getLogger(UserAction.class);
	
	private String brandName;
	private AutoCompleteServiceI autoCompleteService;
	
	public AutoCompleteServiceI getAutoCompleteService() {
		return autoCompleteService;
	}
	@Autowired
	public void setAutoCompleteService(AutoCompleteServiceI autoCompleteService) {
		this.autoCompleteService = autoCompleteService;
	}
	
	
	 /** 
     * 获取自动补全功能数据 
     */  
    public String getAutoComplteList(){  
        try {  
            String jsonValue=autoCompleteService.getBrandList();
            System.out.println(jsonValue);  
             //response.getWriter().write(jsonValue);
           super.writeJson(jsonValue);
              
        } catch (Exception e) {  
            System.err.println("自动补全出错！");  
        }  
        return null;  
    }  
	
	
	//获取品牌类型
	public String getAutoComplteListByName() {
		// String brandName=request.getParameter("brandName");  
	      	
	        try {  
	            if (brandName!=null&&!"".equals(brandName)) {  
	            	 brandName=java.net.URLDecoder.decode(brandName, "utf-8");  
	             
	            String jsonValue=  autoCompleteService.findBrandInfoBybrandName(brandName);
	            System.out.println(jsonValue);  
	            //response.getWriter().write(jsonValue); 
	            super.writeJson(jsonValue);
	            } 
	            //具体业务代码自己根据需要写  
	        } catch (Exception e) {  
	            System.err.println("按条件查询出错！");  
	        }  
	          
	        return null;  
	    }
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}  
	
	
	
}
