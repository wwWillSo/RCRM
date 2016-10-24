$(function () {
	
	//用于提示登录异常
	var loginResult = $("#loginResult").val()
	if (loginResult == "FAIL") {
		alert("用户或密码输入错误")
		window.location.href="/";
	}
	
	var registResult = $("#registResult").val()
	if (registResult == "FAIL") {
		alert("注册失败")
		window.location.href="/";
	}
	
	$(".registFormContainer").hide() 
	
	$(".goto-regist").click(function () {
		$(".loginFormContainer").hide("slow")
		$(".registFormContainer").show("slow") 
		
//		alert($("#loginForm").children("div").children("#username").val())
//		alert($("#registForm").children("div").children("#username").val())
	})
	
	$(".goto-login").click(function () {
		$(".registFormContainer").hide("slow")
		$(".loginFormContainer").show("slow") 
	})
	
	$(".login-submit").click(function () {
		var username = $("#loginForm").children("div").children("#username").val() 
		var password = $("#loginForm").children("div").children("#password").val()
		
		$.getJSON("/api/public/passwordTransformer/" + password, [], function(jsonData) {
			password = jsonData.password 
			
//			var jsonObj = {
//					"username" : username,
//					"password" : password
//				}
//			
//			$.ajax({
//				url : "/api/public/userLogin",
//				type : "POST",
//				dataType : "json",
//				contentType:"application/json",
//				data : JSON.stringify(jsonObj),
//				success : function (jsonData) {
//					alert(jsonData.result) 
//				}
//			})
			
			$("#loginForm").children("div").children("#password").val(password)
			$("#loginForm").attr("action", "/api/public/userLogin")
			$("#loginForm").submit() 
		})
		
	})
	
	$(".regist-submit").click(function () {
		var username = $("#registForm").children("div").children("#username").val() 
		var password = $("#registForm").children("div").children("#password").val()
		
		$.getJSON("/api/public/passwordTransformer/" + password, [], function(jsonData) {
			password = jsonData.password 
			
//			var jsonObj = {
//					"username" : username,
//					"password" : password
//				}
//			
//			$.ajax({
//				url : "/api/public/userRegist",
//				type : "POST",
//				dataType : "json",
//				contentType:"application/json",
//				data : JSON.stringify(jsonObj),
//				success : function (jsonData) {
//					alert(jsonData.result) 
//				}
//			})
			
			$("#registForm").children("div").children("#password").val(password)
			$("#registForm").attr("action", "/api/public/userRegist")
			$("#registForm").submit()
		})
		
	})
})