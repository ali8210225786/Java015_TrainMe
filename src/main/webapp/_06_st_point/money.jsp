<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js">
// 	console.log(${LoginOK.stNo});
// 	console.log(${LoginOK.type});
	axios.get("/trainme/MoneyController", {
	    params: {
	    	id : 1,
	    	type : 1
	    }})
	.then(function (res) {
	    var moneys = res.data;
	    console.log(moneys);
	    for(money of moneys){
	    	
	    	 console.log(money);
	
// 	        var option = document.createElement("option");
// 	        option.value = area.id;
// 	        option.innerHTML = area.name;
// 	        areaList.appendChild(option);
		    }
	
		 })



</script>

</body>
</html>