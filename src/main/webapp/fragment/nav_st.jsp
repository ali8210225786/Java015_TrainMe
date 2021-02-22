<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>導覽列</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/style_nav.css">
    <link rel="stylesheet" href="../css/style_login.css">
    <header id="navbar">
        <ul>
            <li class="logo">
                <a href="<c:url value='/index.jsp' />"><img src="${pageContext.request.contextPath}/images/index/logo_black.png"></a>
<!--                 <a href="#"><img src="../images/index/logo_black.png"></a> -->
            </li>
                <div class="items">
                    <li><a href="#">關於我們</a></li>
                    <li><a href="#">尋找教練</a></li>
                    <li><a href="#">購物商城</a></li>
                    <li><a href="#">運動論壇</a></li>
                </div>
            <li>
                <button class="nav_btn" id="logout_btn" >登出</button>
            </li>    

       
            <li class="member">
                <a href="#">
                        <img src="${pageContext.request.contextPath}/images/index/member.jpg">
                </a>
                <ul class="dropdown">
                    <div class="triangle"></div>
                    <div class="dropdown_box">
                        <li><a href="#">個人資料</a></li>
                        <li><a href="<c:url value='/_07_studentLesson/CourseDetail.do' />">我的課程</a></li>
                        <li><a href="<c:url value='/_04_Money2/PointDetail.do' />">我的點數</a></li>
                        <li><a href="#">訂單查詢</a></li>
                        <li><a href="<c:url value='/_03MemberData/studentData.jsp' />">帳號設定</a></li>
                        <hr>
                        <li><a href="#">登出</a></li>
                    </div>
                </ul>
            </li>
       
        
       
       
    </header>
    <script>

	// 導覽列
        // 往下捲動再往上時顯示導覽列
        var prevScrollpos = window.pageYOffset;
        window.onscroll = function() {
        var currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("navbar").style.top = "0";
        } else {
            document.getElementById("navbar").style.top = "-90px";
        }
        prevScrollpos = currentScrollPos;
        } 
        

        var logout_btn = document.getElementById('logout_btn');
        logout_btn.addEventListener('click',function () {
		window.location.href="<c:url value='/Logout' />";		
        })

</script>