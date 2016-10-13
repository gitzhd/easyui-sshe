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

<!-- tabs 方法 -->
<script type="text/javascript">
	function addTab(opts){
		vat t = $('#index_centerTabs');
		if(t.tabs('exists',opts.title)){
			//已存在的 被选中
			t.tabs('select',opts.title);
		}else{
			t.tabs('add',opts);
		}
		
		
	}
	
</script>

<title>login</title>

</head>

<body class="easyui-layout">
    <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>   
    <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>   
    <div data-options="region:'west',title:'West',split:true" style="width:100px;">
    	  <div class="easyui-panel" data-options="title:'功能菜单',border:false,fit:true">
    		 <div class="easyui-accordion" data-options="fit:true,border:false">
    			<div title="系统菜单" data-options="iconCls:'icon-save'" style="overflow:outo;padding:10px">
    				<ul id="tree" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/menuAction!tree.action',
    				 lines:true,
    				 onClick:function(node){
    				 	if(node.attributes.url){
    				 		var url = '${pageContext.request.contextPath}'+node.attributes.url;
    				 		addTab({title:node.text,href:url,closable:true});
    				 	}
    				 }
    				">   
					</ul> 
    			</div>
    			 <div title="title2" data-options="iconCls:'icon-edit'" style="overflow:outo;padding:10px">
    			
    			</div>
    		 </div>
    		</div>
    	</div>
    </div>   
    <div data-options="region:'center',title:'后台管理系统'" class="easyui-tabs"  style="padding:5px;background:#eee;">
    		<div title="首页">   
        		tab1    
   		 	</div>	
    	   
    </div>   
	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>
	
</body>

</html>