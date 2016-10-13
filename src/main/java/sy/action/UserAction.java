package sy.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;

import sy.model.Tuser;
import sy.pageModel.MyJson;
import sy.pageModel.User;
import sy.service.UserServiceI;

@Namespace("/")
@Action("userAction")
public class UserAction extends BaseAction implements ModelDriven<User>{
	private static final Logger logger = Logger.getLogger(UserAction.class);
	private UserServiceI userService;
	User user = new User();
	
 

 
	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	public void reg() throws IOException{
		
		 
		
		MyJson json = new MyJson();
		try {
			userService.save(user);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			json.setMsg(e.getMessage().toString());
		}
		super.writeJson(json);
	}

	public void login(){
		User u=userService.login(user);
		MyJson j = new MyJson();
		
		if(u !=null){
			j.setSuccess(true);
			j.setMsg("登录成功");
		}else{
			
			j.setMsg("登录失败，用户名或密码失败");
		}
		super.writeJson(j);
	}
	@Override
	public User getModel() {
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 
}
