/**
	验证密码功能
	
**/
$.extend($.fn.validatebox.defaults.rules,{
	eqPwd : {
		validator : function(value,param){
			return value == $(param[0]).val();
		},
		message : '输入密码不一致',
	}
})
