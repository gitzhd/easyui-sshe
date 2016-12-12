<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <!-- tabs 方法 -->
<script type="text/javascript">
function addTab(opts) {
	console.info(opts.title);
	var t = $('#index_centerTabs');
	if (t.tabs('exists', opts.title)) {
		 
		t.tabs('select', opts.title);
	} else {
		t.tabs('add', opts);
	}
}
	
</script>
<div id="index_centerTabs" class="easyui-tabs" data-options="fit:true,border:false">
    		
    			<div  data-options="closable:true" title="首页">   
    			        			 <jsp:include page="JQueryComplete.jsp"></jsp:include>
    			
   		 		</div>	
</div>