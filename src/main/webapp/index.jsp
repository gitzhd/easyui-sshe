<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="jslib/jquery-easyui-1.4.2/jquery.min.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jslib/jsUtil.js"></script>
<script type="text/javascript" src="jslib/jquery-easyui-1.4.2/locale/easyui-lang-zh_CN.js"></script>
<link rel="stylesheet" href="jslib/jquery-easyui-1.4.2/themes/default/easyui.css" type="text/css"/>
<link rel="stylesheet" href="jslib/jquery-easyui-1.4.2/themes/icon.css"/>
<script>
$(function(){
	$('#index_regDialog').dialog({
		title:'注册',
		module:true,
		//closed:true,
		modal:true,
		buttons:[{
					text:'注册',
					iconCls:'icon-edit',
					handler:function(){
				 	$('#index_regForm').submit();
					}
				}]
	}).dialog('close');
	
	
	// 注册 form 
	$('#index_regForm').form({
		url:'${pageContext.request.contextPath}/userAction!reg.action',
		onSubmit:function(){
			var isValid = $(this).form('validate');
			return isValid;    
		},
		success : function(data) {
			
				 var obj = jQuery.parseJSON(data);
				 
				 if(obj.success){
					 $('#index_regDialog').dialog('close'); 
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
	$('#index_regForm input').bind('keyup', function(event){
		if (event.keyCode == '13'){
			$('#index_regForm').submit();
		}
	});
	
	
});



</script>



<title>login</title>

</head>

<body class="easyui-layout">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>   
    <div data-options="region:'west',title:'West',split:true" style="width:200px;">
    	<jsp:include page="layout/leftmenu.jsp"></jsp:include>
    	</div>
    </div>   
    <div  data-options="region:'center',title:'后台管理系统'" >
    	<jsp:include page="layout/centerView.jsp"></jsp:include>
    </div>   
	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>
	
</body>

</html>