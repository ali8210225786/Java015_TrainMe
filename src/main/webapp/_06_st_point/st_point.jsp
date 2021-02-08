<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="st_point.css">
<title>st_point</title>
<style>
* {
	margin: 0px;
	padding: 0px;
	list-style: none;
}

.container {
	/* border: 1px solid red; */
	margin-top: 50px;
	margin-left: 7.5%;
	font-size: 30px;
	padding: 10px;
}

.container p {
	/* border: 1px solid red; */
	font-size: 22px;
	padding-top: 20px;
	color: #5b5b5b;
}

#add_point_button {
	border: 1px solid maroon;
	border-radius: 5px;
	padding: 5px;
	cursor: pointer;
	color: maroon;
	margin-left: 50px;
	font-size: 20px;
}

#point {
	font-family: Arial, Helvetica, sans-serif;
	border-collapse: collapse;
	width: 85%;
	margin: 0 auto;
	margin-top: 50px;
}

#point td, #point th {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

#point th {
	padding-top: 12px;
	padding-bottom: 12px;
	text-align: left;
	background-color: #6e6e6e;
	color: white;
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		我的點數
		<p>
			點數餘額 : ${MoneyBean.get((MoneyBean.size()-1)).total} <span
				id="add_point_button"><a href="st_add_point.html">我要儲值</a></span>
		</p>
	</div>
	<table id="point">
		<thead>
			<tr>
				<th scope="col">日期</th>
				<th scope="col">上課日期</th>
				<th scope="col">課程名稱</th>
				<th scope="col">教練</th>
				<th scope="col">類型</th>
				<th scope="col">點數</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach varStatus="i" begin="0" end="${MoneyBean.size()-1}">
				<tr>
					<td>${MoneyBean.get(i.current).changeTime}</td>
					
					<c:if test="${MoneyBean.get(i.current).changeAmount>0}">
						<td>-</td>
						<td>-</td>
						<td>-</td>
						<td>儲值點數</td>
					</c:if>
					
					<c:if test="${MoneyBean.get(i.current).changeAmount<0}">
						<c:forEach varStatus="j" begin="0" end="${CourseBean.size()-1}">
							<td>${CourseBean.get(j.current).date}</td>
							<td>${CourseBean.get(j.current).skill}</td>
							<td>${CourseBean.get(j.current).tainerName}</td>
							<td>上課扣點</td>
						</c:forEach>
					</c:if>
					
					<td>${MoneyBean.get(i.current).changeAmount}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!--     <script type="text/javascript"> -->
	<%-- //     	var obj=JSON.parse(${MoneyBean}); --%>
	<!-- //     	console.log(obj); -->
	<!--     </script> -->
</body>
</html>