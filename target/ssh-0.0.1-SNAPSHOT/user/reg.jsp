<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <!-- 注册  -->
	<div id="index_regDialog">
	<form id="index_regForm" method="post">
	
		<table>
			<tr>
				<th>登录名:</th>
				<th><input id="name" name="name"  class="easyui-validatebox" data-options="required:true,missingMessage:'登录用户名不能为空'" /></th>
			</tr>
			<tr>
				<th>密码:</th>
				<th><input id="pwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'用户密码不能为空'"/></th>
			</tr>
			<tr>
				<th>重复密码:</th>
				<th><input id="rePwd" name="rePwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'用户密码不能为空'" validType="eqPwd['#pwd']" /></th>
			</tr>
		</table>
	</form>
	</div>