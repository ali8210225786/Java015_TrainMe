<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>測試首頁</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@splidejs/splide@2.4.21/dist/css/themes/splide-sea-green.min.css">
    <link rel="stylesheet" href="./css/style_login.css">
    <link rel="stylesheet" href="./css/popup_t1.css">
    <link rel="stylesheet" href="./css/style_nav.css">   
    
    <style>
/* 	       @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@100;300;400;500;700;900&display=swap');  */
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            list-style: none;
            text-decoration: none;
        }
        /*教練推薦*/

        .hot_trainer{
            text-align: center; 
            display:block;
            margin: 20px 0px -10px;
        }
        .hot_trainer h3{
            font-size: 18px;
            text-align: center;
            color: #666;
            font-family: 'Noto Sans TC', sans-serif;
        }

        .splide{
            margin: auto;
            
        }
        .splide__slide{
            position: relative;
            /* width: 280px; */
            height: 320px;
            /* background-color: black; */
            /* background: linear-gradient(to bottom, rgba(0,0,0,0) 0%,rgba(0,0,0,0.65) 100%); */
            box-shadow: 0px 0px 5px 0.6px #999;
            
        }
        .splide__slide img{
            width: 100%;
            height: 100%;
            /* opacity: 0.5; */
        }

        .splide__slide .p_t{
            position: absolute;
            
            width: 100%;
            bottom: 0px;
            background-color: rgba(0,0,0,0.7);;
            padding: 7px 0px 10px;
            color: aliceblue;     
            font-family: 'Noto Sans TC', sans-serif;       
        }
        .t_name{
            font-size: 18px;
            font-weight: 700;

            text-align: center;
            margin: 8px;
        }
        .p_t span{
            display: flex;
            justify-content: space-around;
            /* padding: 5px; */
            font-size: 13px;
            margin: 0px 10px;
        }
        .p_t span i{
            margin: 0px 10px 0px 0px ;
        }

        /* <p class="t_name">教練名稱</p>
                    <p class="t_location">地點</p>
                    <p class="t_ltimes">20次課程媒合</p> */

        /*評價星星*/

        .p_t span:nth-child(3) p{
            /* vertical-align: middle; */
            margin: 6px 0px 0px -80px;
            font-size: 15px;
        }

        .ratings {
            position: relative;
            bottom: 20%;
            left: 4%;
            vertical-align: middle;
            display: inline-block;
            color: #b1b1b1;
            overflow: hidden;
        }
        .full-stars {
            position: absolute;
            left: 0;
            top: 0;
            white-space: nowrap;
            overflow: hidden;
            color: #fde16d;
        }
        .empty-stars:before, .full-stars:before {
            content:"\2605\2605\2605\2605\2605";
            font-size: 15pt;
        }
        .empty-stars:before {
            -webkit-text-stroke: 1px #848484;
        }
        .full-stars:before {
            -webkit-text-stroke: 1px orange;
        }
        /* Webkit-text-stroke is not supported on firefox or IE */

        /* Firefox */
        @-moz-document url-prefix() {
            .full-stars {
                color: #ECBE24;
            }
        }


        /*nav*/

        /* #navbar {
            overflow: hidden;
        }

        .sticky {
            position: fixed;
            top: 0;
            width: 100%;
        } */

        header{
            position: fixed; /* Make it stick/fixed */
            top: 0; /* Stay on top */
            width: 100%; /* Full width */
            transition: top 0.3s; /* Transition effect when sliding down (and up) */
            background-color: #fff;
            border-bottom: 1px solid  #eee;
            padding: 10px 100px 10px 100px;
            z-index: 1;
        }
        header ul{
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            justify-content: center;
            width: 100%;
            max-width: 1200px;
            margin: auto;
        }
        .items{
            display: inline-flex;
            padding: 0 25px;
        }
        .items li{
            font-size: 14px;
            margin: 0 20px;
            font-family: 'Noto Sans TC', sans-serif;
        }
        .items li a{
            color: #333;
            transition: .3s;
        }
        .items li a:hover{
            color: #59ddb8;
            box-shadow: 0 2px #21d4a7;
            padding-bottom:8px;
        }
        .logo{
            flex: 1;
        }
        .logo img{
            vertical-align: bottom;
            width: 200px;

        }
        .login_btn{
            padding: 10px 20px;
            border: none;
            border-radius: 10px;
            color: #fff;
            background-color: #59ddb8;
            cursor: pointer;
            font-family: 'Noto Sans TC', sans-serif;
        }
        .login_btn:hover{
            padding:9px 19px;
            color: rgb(5, 230, 192);
            background-color:#fff;
            border: 1px solid #59ddb8;
        }
        .slider{
            position: relative;
        }


        .banner{
            position: relative;
            background-image: url('./images/index/banner_192001.jpg');
            background-size: cover;
            background-position: center;
            height: 600px;
            width: 100vw;
            margin: auto;
            /* max-height: 600px; */
        }

        .slider .ad{
            width: 500px;
            position: absolute;
            left: 20%;
            top: 30%;
            color: #fff
            /* background-color: white; */
        }
        .slider h3{
            padding: 0 0 10px;
            font-size: 30px;
            line-height: 35px;
            color:#00ffb7;
            font-family: 'Noto Sans TC', sans-serif;
        }
        .slider p{
            padding: 3px;
            line-height: 22px;
            font-family: 'Noto Sans TC', sans-serif;
        }

        /*搜尋*/
        .search_trainer{
            position: absolute;
            top: 70%;
            width: 100%;
            display: flex;
            justify-content: center;
        }
        .search_trainer li{
            margin: 0px 10px;
        }
        .search_trainer select{
            width: 150px;
        }
        .search_trainer select,.search_input,.search_btn{
            padding: 15px 30px;
            border-radius: 10px;
            border: none;
            font-size: 16px;
            font-family: 'Noto Sans TC', sans-serif;
            text-align: center;
            vertical-align: baseline;
        }
        .search_trainer .search_input{
            width: 200px;
        }
        .search_trainer .search_btn{
            background-color: #59ddb8;
            color: #fff;
            /* font-weight: bold; */
            font-family: 'Noto Sans TC', sans-serif;
            cursor: pointer;
        }
        .search_trainer .search_btn:hover{
            padding: 14px 29px;
            color: rgb(5, 230, 192);
            background-color:#fff;
            border: 1px solid #59ddb8;
        }


        /* footer */

        footer{
            padding: 100px 0 0 0;
            background:#888
        }

        footer .container{
            display: flex;
            width: 1200px;
            margin: auto;
            font-family: 'Noto Sans TC', sans-serif;
        }
        .footer-item h3{
            font-size: 20px;
            color: #fff;
            border-bottom: 1px solid #fff;
            margin-bottom: .5em;
            padding-bottom: .5em;
        }
        .footer-item{
            width: 0;
            flex-grow: 1;
            margin: 0 20px;
        }
        .footer-item nav{
            display: flex;
            flex-direction: column;
 
        }
        .footer-item nav a{
            color: #fff;
            font-size: 14px;
            line-height: 1.2;
            text-decoration: none;
            padding: 5px 0;
        }
        .copyright{
            width: 100%;
            text-align: center;
            font-family:Verdana, Geneva, Tahoma, sans-serif;
            margin: 100px 0 0;
            padding: 10px 0px;
            background-color: #000;
            color: #fff;
        }

        /*其他區塊*/
        .other{
            display: flex;
            justify-content: space-around;
            margin: auto;
            width: 100%;
            max-width: 60%;
            height: 400px;
        }
        .about{
            width: 50%;
            margin: 0px 30px;
        }
        .about p{
            font-family:  'sans-seri','Noto Sans TC',;
            line-height: 1.7;
            font-size: 12px;
        }
        .about h3{
            font-family: 'Noto Sans TC', sans-serif;
            margin: 20px;
            text-align: center;
            color: #888;
        }


        /* @media screen and (min-width: 768px) {
            
        } */


        html .noscroll {
            position: fixed; 
            overflow-y: scroll;
            width: 100%;
        }

        html .fixWindow {
            position: fixed;
            width: 100%;
        }
    </style>
</head>
<body>

<c:if test="${LoginOK == null}">
    <header id="navbar">
        <ul>
            <li class="logo">
                <a href="#"><img src="./images/index/logo_black.png"></a>
            </li>
            <div class="items">
                <li><a href="#">關於我們</a></li>
                <li><a href="#">尋找教練</a></li>
                <li><a href="#">購物商城</a></li>
                <li><a href="#">運動論壇</a></li>
            </div>
            <li>
                <button class="login_btn" onclick="Show();">登入/註冊</button>
            </li>            
    </header>
</c:if>

<c:if test="${LoginOK.type == 1}">
	<jsp:include page="/fragment/nav_st.jsp"/>  
</c:if>

<c:if test="${LoginOK.type == 2}">
	<jsp:include page="/fragment/nav_tr.jsp"/>  
</c:if>

    <div class="slider">
        <div class="banner">
        
        <div class="ad">
            <h3>健身教練與學員的<br>專屬媒合平台</h3>
            <p>教練課程價錢、時段公開透明<br>
            省去詢問時間，免於被健身業務打擾<br>
            透過評價系統，讓您篩選最適合自己的教練！
            </p>
        </div>
     
            <form class="search_trainer" action="#">
                <li>    
                    <select name="" class="lesson">
                        
                        <option value="0">課程種類</option>
                        <option value="1">增肌</option>
                        <option value="2">減脂</option>
                    </select>
                </li>
                <li>
                    
                    <select name="" class="location">
                        <option value="0">地點</option>
                        <option value="1">台北市</option>
                        <option value="2">新北市</option>
                    </select>
                </li>
                <li><input type="search" placeholder="輸入關鍵字" class="search_input">    </li>
                <li><button class="search_btn">搜尋教練</button>    </li>
            </form>
        </div>
    </div>

    <!-- 人氣教練 -->

    <span class="hot_trainer">
        <img src="data:image/png;base64,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"> 
        <h3>本月人氣教練</h3>
    </span>
    
    <div class="splide">
        <div class="splide__track">
            <ul class="splide__list">
                <div class="splide__slide">
                    <div class="p_t">
                        <p class="t_name">林瑪莉</p>
                        <span>
                            <p class="t_location"><i class="fas fa-map-marker-alt"></i>台北市</p>
                            <p class="t_ltimes"><i class="fas fa-user-friends"></i>20次課程媒合</p>
                        </span>
                        <span>
                            <div class="ratings">
                                <div class="empty-stars"></div>
                                <div class="full-stars" style="width:80%"></div>                            
                            </div>
                            <p>(18)</p>
                        </span>
                    </div>
                    <img src="./images/index/1.jpg">
                </div>

                <div class="splide__slide">
                    <div class="p_t">
                        <p class="t_name">林瑪莉</p>
                        <span>
                            <p class="t_location"><i class="fas fa-map-marker-alt"></i>台北市</p>
                            <p class="t_ltimes"><i class="fas fa-user-friends"></i>20次課程媒合</p>
                        </span>
                        <span>
                            <div class="ratings">
                                <div class="empty-stars"></div>
                                <div class="full-stars" style="width:80%"></div>                            
                            </div>
                            <p>(18)</p>
                        </span>
                    </div>
                    <img src="./images/index/2.png">
                </div>

                <div class="splide__slide">
                    <div class="p_t">
                        <p class="t_name">林瑪莉</p>
                        <span>
                            <p class="t_location"><i class="fas fa-map-marker-alt"></i>台北市</p>
                            <p class="t_ltimes"><i class="fas fa-user-friends"></i>20次課程媒合</p>
                        </span>
                        <span>
                            <div class="ratings">
                                <div class="empty-stars"></div>
                                <div class="full-stars" style="width:80%"></div>                            
                            </div>
                            <p>(18)</p>
                        </span>
                    </div>
                    <img src="./images/index/3.jpg">
                </div>
                
                <div class="splide__slide">
                    <div class="p_t">
                        <p class="t_name">林瑪莉</p>
                        <span>
                            <p class="t_location"><i class="fas fa-map-marker-alt"></i>台北市</p>
                            <p class="t_ltimes"><i class="fas fa-user-friends"></i>20次課程媒合</p>
                        </span>
                        <span>
                            <div class="ratings">
                                <div class="empty-stars"></div>
                                <div class="full-stars" style="width:80%"></div>                            
                            </div>
                            <p>(18)</p>
                        </span>
                    </div>
                    <img src="./images/index/1.jpg">
                </div>

                <div class="splide__slide">
                    <div class="p_t">
                        <p class="t_name">林瑪莉</p>
                        <span>
                            <p class="t_location"><i class="fas fa-map-marker-alt"></i>台北市</p>
                            <p class="t_ltimes"><i class="fas fa-user-friends"></i>20次課程媒合</p>
                        </span>
                        <span>
                            <div class="ratings">
                                <div class="empty-stars"></div>
                                <div class="full-stars" style="width:80%"></div>                            
                            </div>
                            <p>(18)</p>
                        </span>
                    </div>
                    <img src="./images/index/2.png">
                </div>

                <div class="splide__slide">
                    <div class="p_t">
                        <p class="t_name">林瑪莉</p>
                        <span>
                            <p class="t_location"><i class="fas fa-map-marker-alt"></i>台北市</p>
                            <p class="t_ltimes"><i class="fas fa-user-friends"></i>20次課程媒合</p>
                        </span>
                        <span>
                            <div class="ratings">
                                <div class="empty-stars"></div>
                                <div class="full-stars" style="width:80%"></div>                            
                            </div>
                            <p>(18)</p>
                        </span>
                    </div>
                    <img src="./images/index/3.jpg">
                </div>
                
            </ul>
        </div>
    </div>

<!-- 登入/註冊 -->
<jsp:include page="/fragment/login.jsp"/> 

<!-- <!-- 登入 --> -->

<!--     遮罩 -->
<!--     <div id="overlay" class="hide" onclick="Hide();"></div> -->

<!--     彈出框 -->
<!--     <div id="popup_form" class="popup_box hide"> -->

<!--         <div id="sform_tr" class="tr signupform"> -->
<!--             <h2>教練註冊</h2> -->
<!--             <form id="register" class="form"  method="POST" -->
<%-- 					action="<c:url value='/Train_RegisterServletMP_new.do'  />" --%>
<!-- 					enctype="multipart/form-data"> -->
<!--                 <div class="wrap"> -->
<!--                     <div class="box"> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_name">姓名</label><br> -->
<%--                             <input type="text" name="tr_name" class="input_field" placeholder="請輸入您的姓名" required value="${param.tr_name}"> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_email">e-mail</label><br> -->
<%--                             <input type="email" name="tr_email" class="input_field" placeholder="請輸入您的e-mail信箱" required value="${param.tr_email}"> --%>
<%--                         	<label style = "font-size: 1px; color: red;">${MsgMap.tr_emailError} ${MsgMap.tr_emailExists}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_password">密碼</label><br> -->
<!--                             <input type="text" name="tr_password" class="input_field" placeholder="請輸入您的密碼" required> -->
<%--                        		<label style = "font-size: 1px; color: red;">${MsgMap.tr_passwordError}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_passwordcheck">確認密碼</label><br> -->
<!--                             <input type="text" name="tr_passwordcheck" class="input_field" placeholder="請再次輸入您的密碼" -->
<!--                                 required> -->
<%--                        		<label style = "font-size: 1px; color: red;">${MsgMap.tr_passwordCheckError}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_phone">手機</label><br> -->
<%--                             <input type="text" name="tr_phone" class="input_field" placeholder="請輸入您的手機" required value="${param.tr_phone}"> --%>
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="group"> -->
                            
<!--                                 <label for="gymId">所屬健身房</label><br> -->
<!-- 								<select name="gymId" id="gymList"></select>  -->
<!-- <!--                            <input type="text" name="gymId" class="input_field " placeholder="請輸入您所屬的健身房/工作室" required> --> -->
                            
<!--                         </div> -->
<!--                         <div class="group"> -->
                            
<!--                                 <label for="gympassword">健身房驗證碼</label><br> -->
<!--                                 <input type="text" name="gympassword" class="input_field " placeholder="請輸入健身房驗證碼" required> -->
<%--                             	<label style = "font-size: 1px; color: red;">${MsgMap.tr_errorverification}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_id">身分證字號</label><br> -->
<!--                             <input type="text" name="tr_id" class="input_field" placeholder="請輸入您的身分證字號" required> -->
<%--                             <label style = "font-size: 1px; color: red;">${MsgMap.tr_idNumberExists}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="tr_birthday">生日</label><br> -->
<!--                             <input name="tr_birthday" type="date" class="input_field birthday" required> -->
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <div class="radio"> -->
<!--                                 <label for="">性別</label><br> -->
<!--                                 <input type="radio" name="tr_sex" value="man" checked="checked" /> 男 -->
<!--                                 <input type="radio" name="tr_sex" value="woman" /> 女 -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <button type="submit" class="btn">註冊</button> -->
<!--                 <div class="ask_login"> -->
<!--                     <p>已有帳號？<span class="span-btn" onclick="login()">點我登入</span></p> -->
<!--                 </div> -->
<!--             </form> -->

<!--         </div> -->

<!--         <div id="lf" class="loginform"> -->
<%--             <form id="login" class="login_area" action="<c:url value='/tr-login.do' />" method="POST"> --%>
<!--                 <div class="login_logo"> -->
<!--                     <img src="images/index/logo_black.png" alt="Train Me" title="Train Me"> -->
<!--                 </div> -->

<!--                 <h2>歡迎回來!</h2> -->

<!--                 <div class="group"> -->
<!--                     <label for="lgEmail">帳號</label><br> -->
<!--                     <input type="email" name="lgEmail" class="input_field" placeholder="E-mail信箱" required> -->
<%--                		<label style = "font-size: 1px; color: red;">${MsgMap.AccountEmptyError}</label> --%>
<!--                 </div> -->
<!--                 <div class="group"> -->
<!--                     <label for="pswd">密碼</label><br> -->
<!--                     <input type="password" name="pswd" class="input_field" placeholder="密碼" required> -->
<%--                		<label style = "font-size: 1px; color: red;" >${MsgMap.LoginError}${MsgMap.noPass}</label> --%>
<!--                 </div> -->
<!--                 <div class="check"> -->
<!--                     <input type="checkbox" class="check_box"> 記住密碼</span> -->
<!--                 </div> -->
<!--                 <div class="btn_group"> -->
<!--                     <button type="submit" class="btn">登入</button> -->
<!--                 </div> -->
<!--             </form> -->
<!--             <div class="ask_signup"> -->
<!--                 <div class="ask_area"> -->
<!--                     <p>還不是會員嗎？</p> -->
<!--                     <div class="imgbox"> -->
<!--                         <img -->
<!--                             src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAGQAAABkCAYAAABw4pVUAAAABmJLR0QA/wD/AP+gvaeTAAAE5UlEQVR4nO2dy4scVRTGvxMk48YYxSiCA+4CoiRb8Q9QMcYnEoKr4GPhHyHuE10I4t6VScCVC8VgFm4CLty6GR9jwmTMjM7ERRSdz0VXa6Wmq+q+Tt+T7vODhim6zrnn3O8+q6prAMdxHMdxHMdxHCcKki+Q3CR5neTJ2vEsPSSv8n9+qR3PHQvJ50leI/kryRcz/NxGhp+XmliukTyR6ueOheR6qx43Sa4k+skWhORKE8OUn1L8lOBArYIB7LX+PgLglVqBNGUfqVj+f9QU5GLn+M0qUUx4q3N8oUoUNSF5lOReZ8R5LMFP1pBVKo6FgOTlTkWcTfCRK8i5jouvY30sDCRPdyrjBsm7I30kC0LyYGcyJ8nTcVksECUqJFOQ7AaxcOQOGZmCZA+ZCwczJ9VUQXLLXWhyWmqGID6Z98H9Y3nwTjlDkJ87poszmZNcJXmR5G7z+Yzk0Qj7FU6u1k7ZiLD9oWW3FmgjvH0xcZ0Jl25y81ahCWqr21JJbpNcjfBzoqmkHZKnIuye5eS62DrJZyLsTjWVuEnyuVC7ln2RvIvTtJA+zkf6uksrztJllsy7KE0r62OnWmDKaOUtBQIbnEhFJLsMi2jlXfNqrzMDF8QYLogxZgpCi+vrFtbj6yMpbkaurwdWGhyb+DKSqr7+j807OW5Grq81BOFASwqJb8i+FLF5x9Zr2zBqfR0bWECigy1pLL4x+9h4BuKMynss7pIFRZ0fkOhgSxorb8y+KSO7B8XmHXr+vs3LLGdtuhue2PPHILkL4J6er3cBHBpxcXPE/nEA3wG4v/PdbwCOich6YJwq9WRx2dtXmcC4GCH2H2C/GABwH4BzAf5VsdhDiq/MOgz2IBG5N8TJMvUQbXJ7oCrLKIhpXBBjuCDGcEGM4YIYQ/0edugy1tqdxTksv2fiPcQYLogxXBBjuCDGcEGM4YIYwwUxhvo+xNr+IpSxuLX2Kd5DjFFNEJIbQ/egl5W5P/7fYg3AQ9MDkk/Be2xVQa4AeLJ1/E2tQFrsjZ+iS80W+VXFsvv4vXYANQW5BGCrYvmz+LF2ANUEEZFbAD6pVX4P39YOoPYk+iGAvyrH0Obz2gFUFURE1gB8XDOGFlsAvqwdxCxBbg6cr/EjzvcAXFXwG8tHzTCqRVC9zhLk0oDhF8nh9CAi2wBeB/BPad8RbAB4X7mMtHrl5KUs2zMe0L5B8pEZ50c9BT5Q7tvc/zKYebBH8uXQOFPzZmS9do1XOXn0f6f5fNpnFBvYSLlnSP4ZXJVleDcmxpy8GVGvyaQENuLvCZJX8uo4iD9IvmMl7ykmXxxA8gCApwG8CuBMYmhjPCoiye/n1cgbMCpIy/dhTH5Io8HDIhL81qEuWnnX3hiO8aCibxMvTu5SQhDNfcsDmfaavlXyLiGI5r5FsxXn+p7rfi0Y5qyvx32/UWI51UPyCksz7+weIiLfAziGyfvSd5vPeQDHRST3/3lo9pCsIUsr7yJ3DJufEr9WwlcH05O6Rt7WV1mW5xAVXBBjLLMgmkvqZJZZEJM9xPRjniRvAUj631QB/A3goIiYekjPbA8heQh6YgCTFeZhRf9JmBUE8xlSzA1byy6IuYl92QXxHhKBC2IMF8QYPocYw3uIMTSv9E5xQSLwIcsYPmQZYx6t15wgjuM4juM4juM48+ZfIpziDTRhQqYAAAAASUVORK5CYII="> -->
<!--                     </div> -->
<!--                     <div class="btn_group"> -->
<!--                         <button type="button" class="btn ask_btn" id="registerbtn" onclick="register_st()">註冊學員</button> -->
<!--                     </div> -->
<!--                     <p><br><br>您是已有健身房驗證碼的教練嗎？</p> -->
<!--                     <div class="imgbox"> -->
<!--                         <img -->
<!--                             src="data:image/png;base64,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"> -->
<!--                     </div> -->
<!--                     <div class="btn_group"> -->
<!--                         <button type="button" class="btn ask_btn" id="registerbtn" onclick="register_tr()">註冊教練</button> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->

<!--         <div id="sform_st" class="st signupform"> -->
<!--             <h2>學員註冊</h2> -->
<%--             <form id="register" class="form" method="POST" action="<c:url value='/Student_RegisterServletMP_new.do' />" enctype="multipart/form-data"> --%>
<!--                 <div class="wrap"> -->

<!--                     <div class="box"> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_name">姓名</label><br> -->
<%--                             <input type="text" name="st_name" class="input_field" placeholder="請輸入您的姓名" required value="${param.st_name}" > --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_email">e-mail</label><br> -->
<%--                             <input type="email" name="st_email" class="input_field" placeholder="請輸入您的e-mail信箱" required value="${param.st_email}"> --%>
<%--                        		<label style = "font-size: 1px; color: red;">${MsgMap.st_emailError} ${MsgMap.st_errorIdDup}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_password">密碼</label><br> -->
<!--                             <input type="text" name="st_password" class="input_field" placeholder="請輸入您的密碼" required > -->
<%--                         	<label style = "font-size: 1px; color: red;">${MsgMap.st_passwordError}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_passwordcheck">確認密碼</label><br> -->
<!--                             <input type="text" name="st_passwordcheck" class="input_field" placeholder="請再次輸入您的密碼"  -->
<!--                                 required> -->
<%--                             <label style = "font-size: 1px; color: red;">${MsgMap.st_passwordCheckError}</label> --%>
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="box"> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_phone">手機</label><br> -->
<%--                             <input type="text" name="st_phone" class="input_field" placeholder="請輸入您的手機" required value="${param.st_phone}"> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_id">身分證字號</label><br> -->
<!--                             <input type="text" name="st_id" class="input_field" placeholder="請輸入您的身分證字號" required> -->
<%--                             <label style = "font-size: 1px; color: red;">${MsgMap.st_idNumberExists}</label> --%>
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="st_birthday">生日</label><br> -->
<!--                             <input name="st_birthday" type="date" class="input_field birthday" required> -->
<!--                         </div> -->
<!--                         <div class="group"> -->
<!--                             <label for="">性別</label><br> -->
<!--                             <div class="radio"> -->
<!--                                 <input type="radio" name="st_sex" value="man" checked="checked" /> 男 -->
<!--                                 <input type="radio" name="st_sex" value="woman" /> 女 -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <button type="submit" class="btn">註冊</button> -->
<!--                 <div class="ask_login"> -->
<!--                     <p>已有帳號？<span class="span-btn" onclick="login()">點我登入</span></p> -->
<!--                 </div> -->
<!--             </form> -->

<!--         </div> -->



<!--     </div> -->
<!--     </div> -->

    <!-- 其他區塊 -->
    <div class="other">
        <div class="about">
            <h3>為什麼要找私人健身教練？</h3>
            <p>
                　　私人健身教練會客製化學員的教練課程，依學員時間、身體狀態安排課程，並視情況調整進度，彈性更高、學習更能建立信心，許多教練也會為學生客製化個人的增肌減脂飲食菜單及課後的自主訓練菜單。
許多人會想看書或看影片在家自己練，但常因此忽略細節，甚至因為出力不當造成運動傷害，以至於喪失信心與興趣。私人健身教練採「平衡訓練，以全身為訓練範圍，在過程中重量搭配、角度位置、技術，到安全措施全部包辦。</p>
        </div>
        <div class="about">
            <h3>小型健身房收費與特點</h3>
            <p>
                > 收費方式：以上課次數、課程項目計費<br>
                > 教練費用：單堂教練費用700-3000元，團體課程費用300-1000元<br>
                > 使用限制：預約制或有課程時才能使用<br>
                > 優點：使用人數較少，小班制團體課品質相對有保障，對於想嘗試健身但比較害羞的群體來說也是很好的選擇<br>
                > 缺點：營業據點較少，團體課的多樣性比較低
            </p>
        </div>       
    </div>
    <!-- footer -->

    <footer>
        <div class="container">
            <div class="footer-item">
                <h3>本站地圖</h3>
                <nav>
                    <a href="#">關於我們</a>
                    <a href="#">搜尋教練</a>
                    <a href="#">購物商城</a>
                    <a href="#">運動論壇</a>
                </nav>
            </div>

            <div class="footer-item">
                <h3>本站地圖</h3>
                <nav>
                    <a href="#">關於我們</a>
                    <a href="#">搜尋教練</a>
                    <a href="#">購物商城</a>
                    <a href="#">運動論壇</a>
                </nav>
            </div>

            <div class="footer-item">
                <h3>本站地圖</h3>
                <nav>
                    <a href="#">關於我們</a>
                    <a href="#">搜尋教練</a>
                    <a href="#">購物商城</a>
                    <a href="#">運動論壇</a>
                </nav>
            </div>
            
        </div>
        <div class="copyright">
            Copyright &copy; 2021 Train Me
        </div>
    </footer>

	<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>			 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/@splidejs/splide@2.4.21/dist/js/splide.min.js"></script>
    <script>
   

// 導覽列

        var prevScrollpos = window.pageYOffset;
        window.onscroll = function() {
        var currentScrollPos = window.pageYOffset;
        if (prevScrollpos > currentScrollPos) {
            document.getElementById("navbar").style.top = "0";
        } else {
            document.getElementById("navbar").style.top = "-60px";
        }
        prevScrollpos = currentScrollPos;
        } 


// 登入註冊
        // 顯示登入框
        function Show() {
            document.getElementById('popup_form').classList.remove('hide');
            document.getElementById('overlay').classList.remove('hide');
            $("#popup_form").css("display", "block");
            document.documentElement.style.overflow = 'hidden';//電腦端禁止滑動
            document.body.style.overflow = 'hidden';//手機端禁止滑動
            login();
        }
        // 隱藏登入框
        function Hide() {
            document.getElementById('popup_form').classList.add('hide');
            document.getElementById('overlay').classList.add('hide');
            $("#popup_form").css("display", "none");
            document.documentElement.style.overflow = 'auto';//電腦端可以滑動
            document.body.style.overflow = 'auto';//手機端可以滑動
        }

        var a = document.getElementById('popup_form');

        var x = document.getElementById("lf");
        var y = document.getElementById("sform_st");
        var z = document.getElementById("sform_tr");

        // 註冊學員
        function register_st() {
            x.style.left = "-800px";
            y.style.left = "0px";
            z.style.left = "-800px";
        }

        // 註冊教練
        function register_tr() {
            x.style.left = "800px";
            y.style.left = "800px";
            z.style.left = "0px";
            a.style.height = "560px";

            // 外框與註冊教練頁的滑動速度
            document.getElementById("popup_form").classList.add('transition');
            document.getElementById("sform_tr").classList.add('transition');
        }

        // 回到登入
        function login() {
            x.style.left = "0px";
            y.style.left = "800px";
            z.style.left = "-800px";
            a.style.height = "500px";
            document.getElementById("popup_form").classList.remove('transition'); 
        }


// 教練推薦

        document.addEventListener( 'DOMContentLoaded', function () {
            new Splide( '.splide', {
            width: '1200px',
            // fixedWidth: '12rem',
            // fixedHeight: '8rem',
            padding: {
                left:20,
                right:20,
            },               
            perPage: 4,
            perMove: 1,
            gap: 40,
            rewind : true,
            pagination :false,
            // arrowPath: 'M339.7,10l-65.1,65.3L611.3,500L274.6,924.7l65.1,65.3l385.7-490.2L339.7,10z'
        } ).mount();
        } );
        
        // 解決有錯誤資料時跳轉頁面問題
	    <c:if test="${MsgMap.from != null}">
		    console.log( "${MsgMap.from}");
		   
		    
			
		     if("${MsgMap.from}" == "tr_signUp"){
		    	 console.log( "${MsgMap.from}");
		    	 Show();
		    	 register_tr();
		     }
		     if("${MsgMap.from}" =="st_signUp"){
		    	
		    	 Show();
		    	 register_st();
		     }
		     if("${MsgMap.from}" == "logIn"){
			    	
		    	 Show();
		     }
	   	
	    </c:if>
        
        
        
        
        
        // 找到 select 標籤
        var gymList = document.getElementById("gymList");

       // 開始尋找健身房資料的servlet去抓資料，會返回一個promise(承諾)
        axios.get("/trainme/Gymlist")
        .then(function (res) {   //若有抓到資料則執行這個function
          var gyms = res.data; // gyms 為一個物件陣列
  		console.log(gyms);
  		for(gym of gyms){
 	 		console.log(gym);
  			var option = document.createElement("option");
  			option.value = gym.id;
  			option.innerHTML = gym.name;
  			gymList.appendChild(option);
  		}
        });

      </script>

</body>
</html>