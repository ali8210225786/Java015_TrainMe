<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="aside">
	<ul id="menu">
	
        <li class="mypage_title">我的主頁</li>
        <li><a id="a" href="#"><i class="fas fa-user-circle"></i>個人資料</a></li>
        <li><a href="#"><i class="fas fa-user-circle"></i>學員列表</a></li>
        <li><a href="<c:url value='/_05_trainSchedule/timeOff.jsp' />"><i class="far fa-list-alt"></i></i>課程管理</a></li>
        <li><a href="#"><i class="fas fa-coins"></i>帳戶查詢</a></li>
        <li><a href="#"><i class="fas fa-shopping-cart"></i>訂單查詢</a></li>
        <li><a id="a" href="<c:url value='/_03MemberData/trainerData.jsp' />"><i class="fas fa-tools"></i>帳號設定</a></li>
	</ul>
</div>