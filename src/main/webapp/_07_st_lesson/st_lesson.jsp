<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>學員-我的課程</title>

<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous" />
<link rel="stylesheet" href="../css/style_st_info.css">
<link rel="stylesheet" href="../css/style_st_lesson.css">
<link rel="stylesheet" href="../css/style_nav.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">

<!-- 暫時代替側邊選單點擊變色的功能的CSS -->
<style>
.aside ul li:nth-child(3) a {
	color: #21d4a7;
}
</style>
</head>
<body>

	<!-- ============上方導覽列======================================================= -->
	<jsp:include page="/fragment/nav_st.jsp"/>
	<!-- <header id="navbar">
		<ul>
			<li class="logo"><a href="./0.index_navUpdate.html"><img
					src="../images/logo_black.png"></a></li>
			<div class="items">
				<li><a href="#">關於我們</a></li>
				<li><a href="#">尋找教練</a></li>
				<li><a href="#">購物商城</a></li>
				<li><a href="#">運動論壇</a></li>
			</div>
			<li> -->
				<!-- <button class="nav_btn" onclick="Show();">登入/註冊</button> -->
			<!-- </li>
			<li class="member"><a href="#"> <img
					src="../images/member.jpg">
			</a>
				<ul class="dropdown">
					<div class="triangle"></div>
					<div class="dropdown_box">
						<li><a href="./st_info.html">個人資料</a></li>
						<li><a href="<c:url value='/_07_studentLesson/CourseDetail.do' />">我的課程</a></li>
						<li><a href="<c:url value='/_04_Money2/PointDetail.do' />">我的點數</a></li>
						<li><a href="#">訂單查詢</a></li>
						<li><a href="./st_info_account.html">帳號設定</a></li>
						<hr>
						<li><a href="#">登出</a></li>
					</div>
				</ul></li>
	</header> -->



	<div class="container">

		<!-- ============左側選單======================================================= -->
<!-- 
		<div class="aside">
			<ul id="menu">
				<li class="mypage_title">我的主頁</li>
				<li><a id="a" href="./st_info.html"><i
						class="fas fa-user-circle"></i>個人資料</a></li>
				<li><a id="a" href="<c:url value='/_07_studentLesson/CourseDetail.do' />"><i
						class="far fa-list-alt"></i></i>我的課程</a></li>
				<li><a id="a" href="<c:url value='/_04_Money2/PointDetail.do' />"><i
						class="fas fa-coins"></i>我的點數</a></li>
				<li><a id="a" href="#"><i class="fas fa-shopping-cart"></i>訂單查詢</a></li>
				<li><a id="a" href="./st_info_account.html"><i
						class="fas fa-tools"></i>帳號設定</a></li>
			</ul>
		</div> -->
		<jsp:include page="/fragment/sidebar_st.jsp"/>
		<!-- ============右側網頁======================================================= -->


		<!-- ============個人資料======================================================= -->

		<div class="content">
			<div class="title">
				<h3>我的課程</h3>
			</div>

			<!-- ============即將開始的課程======================================================= -->

			<div class="info_wrap t lesson">

				<ul class="nav nav-pills mb-3 pb-3 border-bottom" id="pills-tab"
					role="tablist">
					<li class="nav-item mr-4" role="presentation"><a
						class="nav-link li_color active" id="pills-wait-tab"
						data-toggle="pill" href="#pills-wait" role="tab"
						aria-controls="pills-wait" aria-selected="true">等待同意</a></li>
					<li class="nav-item mr-4" role="presentation"><a
						class="nav-link li_color" id="pills-next-tab" data-toggle="pill"
						href="#pills-next" role="tab" aria-controls="pills-next"
						aria-selected="false">即將上課</a></li>
					<li class="nav-item " role="presentation"><a
						class="nav-link li_color" id="pills-history-tab"
						data-toggle="pill" href="#pills-history" role="tab"
						aria-controls="pills-history" aria-selected="false">歷史課程</a></li>
				</ul>

				<div class="tab-content mt-3" id="pills-tabContent">
					<!-- 等待同意 -->
					<div class="tab-pane fade show active" id="pills-wait"
						role="tabpanel" aria-labelledby="pills-wait-tab">
						<table class="table border">
							<thead class="thead-light">
								<tr>
									<th scope="col" class="class_name">課程名稱</th>
									<th scope="col">教練</th>
									<th scope="col">上課時間</th>
									<th scope="col">扣點</th>
									<th scope="col">取消課程</th>
								</tr>
							</thead>
							<tbody class="tr_height">
								<c:forEach varStatus="i" begin="0" end="${CourseBean.size()-1}">
									<c:if test="${CourseBean.get(i.current).isAllowed==0}">
										<td class="class_name">${CourseBean.get(i.current).skill}</td>
										<td>${CourseBean.get(i.current).tainerName}</td>
										<td>${CourseBean.get(i.current).date}</td>
										<td>${CourseBean.get(i.current).price}</td>
										<td><a href="#">取消</a></td>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 即將上課 -->
					<div class="tab-pane fade" id="pills-next" role="tabpanel"
						aria-labelledby="pills-next-tab">
						<table class="table border">
							<thead class="thead-light">
								<tr class="my-auto">
									<th scope="col" class="class_name">課程名稱</th>
									<th scope="col">教練</th>
									<th scope="col">上課時間</th>
									<th scope="col">扣點</th>
									<th scope="col">取消課程</th>
								</tr>
							</thead>
							<tbody class="tr_height">
							<c:forEach varStatus="i" begin="0" end="${CourseBean.size()-1}">
							<c:if test="${CourseBean.get(i.current).isAllowed==1&&CourseBean.get(i.current).isExecuted==0}">
								<tr>
									<td class="class_name">${CourseBean.get(i.current).skill}</td>
									<td>${CourseBean.get(i.current).tainerName}</td>
									<td>${CourseBean.get(i.current).date}</td>
									<td>${CourseBean.get(i.current).price}</td>
									<td><a href="#">取消</a></td>
								</tr>
								
								</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- 歷史課程 -->
					<div class="tab-pane fade" id="pills-history" role="tabpanel"
						aria-labelledby="pills-history-tab">
						<table class="table border">
							<thead class="thead-light">
								<tr class="my-auto">
									<th scope="col" class="class_name">課程名稱</th>
									<th scope="col">教練</th>
									<th scope="col">上課時間</th>
									<th scope="col">扣點</th>
									<th scope="col">上課日誌</th>
								</tr>
							</thead>
							<tbody class="tr_height">
								<c:forEach varStatus="i" begin="0" end="${CourseBean.size()-1}">
							<c:if test="${CourseBean.get(i.current).isAllowed==1&&CourseBean.get(i.current).isExecuted==1}">
								<tr>
									<td class="class_name">${CourseBean.get(i.current).skill}</td>
									<td>${CourseBean.get(i.current).tainerName}</td>
									<td>${CourseBean.get(i.current).date}</td>
									<td>${CourseBean.get(i.current).price}</td>
									<td><a href="#"><i class="fas fa-book"></i></a></td>
								</tr>
								
							</c:if>
						</c:forEach>
							</tbody>
						</table>
					</div>
				</div>







			</div>




		</div>
</body>



<script src="./js/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
	crossorigin="anonymous"></script>
<script>
	//選單點選變色(待補)

	//頁籤

	$('#pills-tab a').on('click', function(event) {
		event.preventDefault()
		$(this).tab('show')
	})
</script>


</html>