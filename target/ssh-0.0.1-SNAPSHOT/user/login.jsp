<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <script>
  	$(function(){
  		$('#index_loginDialog').dialog({
  			title:'登录',
  			module:true,
  			closable:false,
  			modal:true,
  			buttons:[{
				text:'注册',
				iconCls:'icon-edit',
				handler:function(){
				 $('#index_regDialog').dialog('open');
				}
			},{
				text:'登录',
				iconCls:'icon-ok',
				handler:function(){
				$('#user_login_loginForm').submit();

			}
			}]
  		});
  		
  		
  		
  	// 登录 form 
  		$('#user_login_loginForm').form({
  			url:'${pageContext.request.contextPath}/userAction!login.action',
  		    onSubmit:function(){
  				var isValid = $(this).form('validate');
  				return isValid;    
  			},    
  			success : function(data) {
  					 var obj = jQuery.parseJSON(data);
  					 if(obj.success){
  						 $('#index_loginDialog').dialog('close'); 
  						 $.messager.show({
  								title:'消息',
  								msg:obj.msg,
  								showType:'slide'
  							});
  					 }else{
  						 $.messager.show({
								title:'消息',
								msg:obj.msg,
								showType:'slide'
							});
  					 }
  					 
  					 
  				
  			}
  		});
  		//回车键提交表单
  		$('#user_login_loginForm input').bind('keyup', function(event){
  			if (event.keyCode == '13'){
  				$('#user_login_loginForm').submit();
  			}
  		});
  	  	
  		//焦点
  		window.setTimeout(function(){
  			$('user_login_loginForm input[name=name]').focus();
  		},0);
  		
  		
  		
  	});
  	
	
  </script>
<!-- 登录 -->
	<div id="index_loginDialog" class="easyui-dialog" >
		<form id="user_login_loginForm" method="post">
		<table>
			<tr>
				<th>登录名:</th>
				<th><input name="name" class="easyui-validatebox" data-options="required:true,missingMessage:'登录用户名不能为空'"/></th>
			</tr>
			<tr>
				<th>密码:</th>
				<th><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'登录密码不能为空'" /></th>
			</tr>
		</table>
		</form>
	</div>
