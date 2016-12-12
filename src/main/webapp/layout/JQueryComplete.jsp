<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>jQuery 实现自动补全功能</title>  
       
     <link rel="stylesheet" type="text/css" href="<%=basePath%>jslib/jqueryAutoComplete/jquery.autocomplete.css">   
   <script type='text/javascript' src='<%=basePath%>jslib/jqueryAutoComplete/jquery-1.7.2.min.js'></script>
   
<%--      <script type="text/javascript" src="<%=basePath%>jslib/jqueryAutoComplete/jquery.js"></script>
 --%><script type='text/javascript' src='<%=basePath%>jslib/jqueryAutoComplete/jquery.bgiframe.min.js'></script>
<script type='text/javascript' src='<%=basePath%>jslib/jqueryAutoComplete/jquery.ajaxQueue.js'></script>
<script type='text/javascript' src='<%=basePath%>jslib/jqueryAutoComplete/thickbox-compressed.js'></script>
<script type='text/javascript' src='<%=basePath%>jslib/jqueryAutoComplete/jquery.autocomplete.min.js'></script>

<script type='text/javascript' src='<%=basePath%>jslib/jqueryAutoComplete/jquery.autocomplete.js'></script>


<link rel="stylesheet" type="text/css" href="<%=basePath%>jslib/jqueryAutoComplete/main.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>jslib/jqueryAutoComplete/thickbox.css" /> 
 
<%-- <script type='text/javascript' src='<%=basePath%>jslib/js2/jquery-1.8.2.min.js'></script>
<script type='text/javascript' src='<%=basePath%>jslib/js2/jquery.autocomplete.js'></script>
 --%>
    
     
      <script type="text/javascript">  
      var names; //定义数据  
      //开始函数  
      $(document).ready(function(){  
    		/* function log(event, data, formatted) {
    			$("<li>").html( !data ? "No match!" : "Selected: " + formatted).appendTo("#result");
    		}
    		 */
    		/* function formatItem(row) {
    			return row[0] + " (<strong>id: " + row[1] + "</strong>)";
    		} */
    		function formatResult(row) {
    			return row[0].replace(/(<.+?>)/gi, '');
    		} 
    	  
    	  
        $.ajax({  
          type:'POST',  
          contentType: "application/json",  
          url: "${pageContext.request.contextPath}/autoCompleteAction!getAutoComplteList.action",  
          dataType: "json", 
          async:false,
          success:function(data){  
           names = JSON.parse(data);  
          } 
          
        }); 
        
        
    	 $("#autocomplete").autocomplete(names, {
      		minChars: 0,
      		width: 310,
      		matchContains: "text",
      		autoFill: false,
      		formatItem: function(row, i, max) {
      			//return i + "/" + max + ": \"" + row[0] + "\" [" + row[0].brandId + "]";
      			return row.brandId + ": \"" + row.brandName;

      		},
      		formatMatch: function(row, i, max) {
      			  return row.brandName + " " + row.brandId;
      			 //return row[i];
      		},
      		formatResult: function(row,i,max) {
      			 return row.brandId+ ":" + row.brandName;
      		}
      		
      	});
    	
      });
        
      
       //搜索数据  
       function onSearch()  
       {  
          var brandName =($("#autocomplete").val().split(":"))[1]; 
          var brandId = ($("#autocomplete").val().split(":"))[0];
           $.ajax({  
                  type:'POST',  
                   contentType: "application/json",  
                    url: "${pageContext.request.contextPath}/autoCompleteAction!getAutoComplteListByName.action?brandName="+encodeURI(encodeURI(brandName)),
                  // url: "${pageContext.request.contextPath}/autoCompleteAction!getAutoComplteListByName.action?brandName="+brandName,  
                   dataType: "json",  
                 	 success:function(data){  
                 		 alert("chengg");
                   	 names = data;  
                  }  
                });  
       }  
    </script>  
    
    
    
    
 
  </head>  
  <body>  
     <input type="text" id="autocomplete" placeHolder="请输入搜索词" name="brandName"/>  
     <input type="button" name="btnSearch" onclick="onSearch();" value="确认"/>  
   
  </body>  
</html>  