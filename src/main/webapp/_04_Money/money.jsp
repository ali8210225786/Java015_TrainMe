<!DOCTYPE HTML>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<p>HIHI</p>

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
// 	console.log(${LoginOK.stNo});
// 	console.log(${LoginOK.type});


	axios.get("/trainme/MoneyController", {
	    params: {
	    	id: ${LoginOK.stNo},
	    	type: ${LoginOK.type}
	    }}
	)
	.then(function (res) {
	    var moneys = res.data;
	    console.log(moneys);
	    for(money of moneys){
	    	
	    	 console.log(money.id);
	    	 console.log(money.time);
	    	 console.log(money.change_amount);
	    	 console.log(money.total_amount);
	    	 console.log('-------------------');
	

		    }
	
		 })



</script>

</body>
</html>