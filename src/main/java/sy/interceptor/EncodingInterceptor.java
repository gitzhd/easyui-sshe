package sy.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class EncodingInterceptor  extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		 
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		ServletActionContext.getRequest().setCharacterEncoding("utf-8");
		return invocation.invoke();
	}

}
