$(function (){
	var addCustomerResult = $("#addDishResult").val()
	if (addCustomerResult == "SUCCESS") {
		alert("添加成功")
		window.location.href = "/businessInfoPage"
	}
	
	$("#addDish").click(function () {
		window.location.href = "/addDish" 
	})
	
	$("#addDishButton").click(function () {
		$("#addDishForm").attr("action", "/addDish.do")
		$("#addDishForm").submit() 
	})
})