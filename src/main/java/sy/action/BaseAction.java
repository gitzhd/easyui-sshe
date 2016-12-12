package sy.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction extends ActionSupport {
	 protected HttpServletRequest request;
	 protected HttpServletResponse response;
	 
	public void writeJson(Object object){
		
		String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
	
}
