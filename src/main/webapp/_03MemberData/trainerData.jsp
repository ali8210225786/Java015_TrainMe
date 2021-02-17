<!DOCTYPE html>
<html lang="en">
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>學員-帳號設定</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/style_st_info.css">
    <link rel="stylesheet" href="../css/style_st_account.css">
    <link rel="stylesheet" href="../css/style_nav.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
	
	<!-- 暫時代替側邊選單點擊變色的功能的CSS -->    
    <style>
        .aside ul li:nth-child(7) a{
                color: #21d4a7;
            }
    </style>
	
</head>
<body>

	  <!-- ============上方導覽列======================================================= -->
   <jsp:include page="/fragment/nav_tr.jsp"/>  

    <!-- 編輯資料 -->

    <div class="container">
        <!-- ============左側選單======================================================= -->
       <jsp:include page="/fragment/sidebar_tr.jsp"/>
	

<!--     <header id="navbar"> -->
<!--         <ul> -->
<!--             <li class="logo"> -->
<!--                 <a href="./0.index_navUpdate.html"><img src="../images/_03_MemberData/logo_black.png"></a> -->
<!--             </li> -->
<!--                 <div class="items"> -->
<!--                     <li><a href="#">關於我們</a></li> -->
<!--                     <li><a href="#">尋找教練</a></li> -->
<!--                     <li><a href="#">購物商城</a></li> -->
<!--                     <li><a href="#">運動論壇</a></li> -->
<!--                 </div> -->
<!--             <li> -->
<!--                 <button class="nav_btn" onclick="Show();">登入/註冊</button> -->
<!--             </li>      -->
<!--             <li class="member"> -->
<!--                 <a href="#"> -->
<!--                         <img src="../images/_03_MemberData/member.jpg"> -->
<!--                 </a> -->
<!--                 <ul class="dropdown"> -->
<!--                     <div class="triangle"></div> -->
<!--                     <div class="dropdown_box"> -->
<!--                         <li><a href="#">個人資料</a></li> -->
<!--                         <li><a href="#">我的課程</a></li> -->
<%--                         <li><a href="<c:url value='/_05_trainSchedule/timeOff.jsp' />">課程管理</a></li> --%>
<!--                         <li><a href="#">我的點數</a></li> -->
<!--                         <li><a href="#">訂單查詢</a></li> -->
<%--                         <li><a href="<c:url value='/_03MemberData/trainerData.jsp' />">帳號設定</a></li> --%>
<!--                         <hr> -->
<!--                         <li><a href="#">登出</a></li> -->
<!--                     </div> -->
<!--                 </ul> -->
<!--             </li> -->
       
<!--     </header> -->

<!--     編輯資料 -->

<!--     <div class="container"> -->
<!--         <div class="aside"> -->
<!--             <ul> -->
<!--                 <li><a href="#"><i class="fas fa-user-circle"></i>個人資料</a></li> -->
<!--                 <li><a href="#"><i class="far fa-list-alt"></i></i>我的課程</a></li> -->
<%--                 <li><a href="<c:url value='/_05_trainSchedule/timeOff.jsp' />"><i class="far fa-list-alt"></i></i>課程管理</a></li> --%>
<!--                 <li><a href="#"><i class="fas fa-coins"></i>我的點數</a></li> -->
<!--                 <li><a href="#"><i class="fas fa-shopping-cart"></i>訂單查詢</a></li> -->
<%--                 <li><a href="<c:url value='/_03MemberData/trainerData.jsp' />"><i class="fas fa-tools"></i>帳號設定</a></li> --%>
<!--             </ul> -->
<!--         </div> -->



        <div class="content">
            <div class="title"><h3>帳號設定</h3></div>
              

                   <!-- 說明 -->

                <div class="explain">
                    <p>您在 Train Me 服務使用的基本資訊</p>
                </div>
                <div class="setting_area">
                        <form action="/trainme/Tr_accountSet" method="post" enctype="multipart/form-data">
                            <div class="setting_box">
                                <label>姓名</label><p>${LoginOK.name}</p>
                            </div>
                            <div class="setting_box">
                                <label>Email</label><p>${LoginOK.email}</p>
                            </div>
                            <div class="setting_box">
                                <label>密碼</label><a href="<c:url value='/_03MemberData/passwordUpdate.jsp' />">修改密碼</a>
                            </div>
                            <div class="setting_box">
                                <label>生日</label><p>${LoginOK.birth}</p>
                            </div>
                            <div class="setting_box">
                                <label>健身房</label><p id="gym"></p>
                            </div>
                            <div class="setting_box">
                                <label>手機</label>
                                <input type="text" required name="phone" value="${LoginOK.phone}">
                            </div>
                            <div class="setting_box">
                                <label>顯示暱稱</label>
                                <input type="text" required name="nickname" value="${LoginOK.nickname}">
                            </div>

                            <div class="setting_box address">
                                <label>地址</label>
                                <select id="city"  name="city_id">
                                    <option>選擇縣市</option>
                            
                                </select>
                                
                                
                                <select id="area" name="area_id" placeholder="選擇地區">
                                   
                                </select>
                            
                                <input type="text" name="address" placeholder="地址" value="${LoginOK.address}">
                            </div>
                             <div class="setting_box">
                                <label>銀行帳戶</label>
                                <input type="text" required name="account" value="${LoginOK.bank_account}">
                            </div>

                            <div class="setting_box upload_file">
                                <label>上傳相片</label>
                                
                                <div class="upload_file_area">
                                    <div id="imgbox" class="ufl_box upload_imgbox">
                                    
                               		 <c:choose>
									    <c:when test="${empty  LoginOK.photo}">
									       <img id="photo"  src="../images/_03_MemberData/upimage.png">
									    </c:when>    
									    <c:otherwise>
									       <img id="photo"  src="/upload/${LoginOK.photo}">
									    </c:otherwise>
									</c:choose>
                                    
                                    
                                    </div>
                                    <div class="ufl_box">
                                        <p>從電腦中選取圖檔<br>
                                            最佳大小為 250 x 250 px</p>    
                                        <button type="button" class="upload_button">
                                            <label>
                                                <input type="file" accept="image/*" id="theFile" name="photo" /> <!-- accept : 限制上傳檔案類型 --> 	
                                                上傳檔案
                                            </label>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            
                            <div class="edit_box e_button">
                                <button class="save">更新</button>
                            </div>
                        </form>
                    </div>

                        
                    </div>
             </div>
        </div>
    
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>

//     // 非正方形圖檔置中待解決or是否要有裁切功能
//     function doFirst(){
//     document.getElementById('theFile').onchange = fileChange;
//     }
//     function fileChange(){
//     let file = document.getElementById('theFile').files[0];
//     let readFile = new FileReader();
//     readFile.readAsDataURL(file);
//     readFile.addEventListener('load',function(){
//         let image = document.getElementById('image');
//         let imgbox = document.getElementById('imgbox');
//         image.src = this.result;
//         image.style.maxWidth = '150px';
//         image.style.maxHeight = '150px';

//         imgbox.style.backgroundImage = 'none';
//         });
//     }
//     window.addEventListener('load',doFirst);
    
    
    var cityList = document.getElementById('city');
    var areaList = document.getElementById('area');
    var cityId = ${LoginOK.city_id};

 // 縣市列表
    axios.get("/trainme/CityList")
    .then(function (res) {
        var citys = res.data;
        for(city of citys){
			
            var option = document.createElement("option");
          
            option.value = city.id;
            option.innerHTML = city.name;
            cityList.appendChild(option);
        }
       // 顯示資料庫內cityId對應的縣市
        cityList.value = cityId;
       
    })
    
        
    axios.get("/trainme/AreaList", {
    	    params: {
    	    	cityId: cityId
    	    }})
	    .then(function (res) {
	        var areas = res.data;
// 	        console.log(area);
	        for(area of areas){

            var option = document.createElement("option");
            option.value = area.id;
            option.innerHTML = area.name;
            areaList.appendChild(option);
        }
	    // 顯示資料庫內area_id對應的行政區
        areaList.value = ${LoginOK.area_id};
//         console.log(areaList.value);

    })
        

   
	// 當選擇縣市時出現對應行政區
    cityList.addEventListener("change",function (e) {
    	
		const cityId = e.target.value;
		
        var areaList = document.getElementById('area');
        if(areaList.firstChild){
        	areaList.innerHTML = "";
        }

        getAreaList(cityId);
        
    })
    
// 行政區列表
    function getAreaList(cityId) {
		
        axios.get("/trainme/AreaList", {
    	    params: {
    	    	cityId: cityId
    	    }})
	    .then(function (res) {
	        var areas = res.data;
	        console.log(area);
	        for(area of areas){

	            var option = document.createElement("option");
	            option.value = area.id;
	            option.innerHTML = area.name;
	            areaList.appendChild(option);
      	    }

   		 })
	}

 //  顯示資料庫內gym_id對應的健身房
    	axios.get("/trainme/Gymlist")
        .then(function (res) {   //若有抓到資料則執行這個function
          var gyms = res.data;
	        console.log(gyms.id);
	        console.log(${LoginOK.gymId});
	        console.log(gyms.id == ${LoginOK.gymId});
	        for(gym of gyms){
		        if(gym.id == ${LoginOK.gymId}){
		        	console.log('ok');
			        let tr_gym = document.getElementById('gym');
			        tr_gym.innerHTML = gym.name
	       		 }
	        	
	        }
       	 })
// 照片處理
	
    const theFile = document.getElementById('theFile');


    
    theFile.addEventListener('change',function () {
//     	console.log('ok');
        showImg(theFile);
  
    })

    function showImg(imgFile) {
        var file = imgFile.files[0];
       
            // 建立FileReader物件
            var fr = new FileReader();
            
            let photo = document.getElementById('photo');
            //註冊load事件
            fr.addEventListener('load', function(e) {
                 photo.src = e.target.result;                 
            });
            //readAsDataURL去讀 file 把檔案轉成 URL
            fr.readAsDataURL(file);
            
    }
             
    
</script>
</body>

</html>