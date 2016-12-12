/**
 * 自定义搜索
 */
 	var names; //定义数据  
      //开始函数  
      $(document).ready(function(){  
    		function log(event, data, formatted) {
    			$("<li>").html( !data ? "No match!" : "Selected: " + formatted).appendTo("#result");
    		}
    		
    		function formatItem(row) {
    			return row[0] + " (<strong>id: " + row[1] + "</strong>)";
    		}
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
       
       