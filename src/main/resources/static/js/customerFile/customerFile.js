$(function (){
	var addCustomerResult = $("#addCustomerResult").val()
	if (addCustomerResult == "SUCCESS") {
		alert("添加成功")
		window.location.href = "/customerFilePage"
	}
	
	$("#addCustomer").click(function () {
		window.location.href = "/addCustomer" 
	})
	
	$("#addCustomerButton").click(function () {
		$("#addCustomerForm").attr("action", "/addCustomer.do")
		$("#addCustomerForm").submit() 
	})
})