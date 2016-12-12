<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<script>
$(function(){
	$('#yhgl_datagrid').datagrid({ 
		fit : true,
		border : false,
		pagination : true,
		rownumbers : true,
		sortName : 'name',
		sortOrder : 'desc',
		idField : 'id',
		pageSize : 5,
		pageList : [5,10,20,30,40,50],
	    url:'${pageContext.request.contextPath}/userAction!datagrid.action',    
	    columns:[[    
	        {field:'id',title:'编号',width:150},    
	        {field:'name',title:'登录名称',width:150},    
	        {field:'pwd',title:'密码',width:150},
	        {field:'createtime',title:'创建时间',width:150},
	        {field:'modifydatetime',title:'最后修改时间',width:150}
	    ]],
	    toolbar :[{
	    	text : '增加',
	    	iconCls: 'icon-add',
	    	handler: function(){
	    		append();
	    	}
	    },'-',{
	    	text : '删除',
	    	iconCls: 'icon-remove',
	    	handler: function(){
	    		remove();
	    	}
	    },'-',{
	    	text: '编辑',
	    	iconCls: 'icon-edit',
	    	handler:function(){
	    	 
	    	}
	    }] 
	    
	}); 
	
});

 function searchFun(){
	
	$('#yhgl_datagrid').datagrid('load',{
		name:$('#admin_yhgl_layout input[name=name]').val()
		
	});
}

function cleanFun(){
	//$('#admin_yhgl_layout input[name=name]').val()
	//$('#yhgl_datagrid').datagrid('load',{});
}
 
function append(){
	$('#admin_yhgl_addform input').val('');
	$('#admin_yhgl_addDialog').dialog('open');
	
} 
 
</script>

<div id="admin_yhgl_layout" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',title:'查询条件'" style="height:100px">
	用户名：<input name="name">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" 
	onClick="searchFun();">
		查询
	</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" 
	onClick="cleanFun();">
		清空
	</a>
	</div>
	<div data-options="region:'center'" data-options="iconCls:'icon-search'" 
	onClick="cleanFun();" style="height:100px">
		<table id="yhgl_datagrid"></table>
	</div>
	
	
</div>

 
<!-- <div id="admin_yhgl_toobar">
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">
		编辑
	</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-help'">
		帮助
</a> 
</div>-->

<!--  弹窗 -->
<div id="admin_yhgl_addDialog" class="easyui-dialog" data-options="closed:true,modal:true,title:'添加用户',buttons:[{
 		text:'增加',
 		iconCls:'icon-add',
 		handler:function(){
 			$('#admin_yhgl_addform').form('submit',{
 			url:'${pageContext.request.contextPath}/userAction!add.action',
 			onSubmit:function(){
			var isValid = $(this).form('validate');
			return isValid;    
		     },
		     success : function(data) {
			 var obj = jQuery.parseJSON(data);
 		 	if(obj.success){
					 $('#admin_yhgl_addDialog').dialog('close'); 
					 $('#yhgl_datagrid').datagrid('reload');
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
 			});
 		}
 		}]" style="width:500px;height:200px;">
 <form id="admin_yhgl_addform" method="post">
	<table>
		<tr>
			<th>编号</th>
			<td><input name="id"/></td>
			<th>登录名称</th>
			<td><input name="name"/></td>
		</tr>
			<tr>
			<th>密码</th>
			<td><input name="pwd" type="password" class="easyui-validatebox" data-options="required:true,missingMessage:'用户密码不能为空'"/></td>
			<th>创建时间</th>
			<td><input name="createdatetime" /></td>
		</tr>
			<tr>
			<th>最后修改时间</th>
			<td><input name="modifydatetime"/></td>
			<th></th>
			<td></td>
		</tr>
	
	</table>
	</form>
</div>

 