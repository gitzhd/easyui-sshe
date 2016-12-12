<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <div class="easyui-panel" data-options="title:'功能菜单',border:false,fit:true">
    		 <div class="easyui-accordion" data-options="fit:true,border:false">
    			<div title="系统菜单" data-options="iconCls:'icon-save'" style="overflow:outo;padding:10px">
    				<ul id="tree" class="easyui-tree" data-options="url:'${pageContext.request.contextPath}/menuAction!tree.action',
    				 lines:true,
    				 onClick:function(node){
    				 	if (node.attributes.url) {
    				 		 
							var url = '${pageContext.request.contextPath}' + node.attributes.url;
							addTab({
								title : node.text,
								closable : true,
								href : url,
							});
						} 
    				 }
    				">   
					</ul> 
    			</div>
    			 <div title="title2" data-options="iconCls:'icon-edit'" style="overflow:outo;padding:10px">
    			
    			</div>
    		 </div>
    		</div>