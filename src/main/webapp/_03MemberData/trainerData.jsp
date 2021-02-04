<!DOCTYPE HTML>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<title>教練個人資料</title>

  <style>
    #bigbox{
        border: 5px rgb(103, 202, 153) dashed;
        border-radius: 30px;
        width: 800px;
        min-height: 1000px;
        margin: 0 auto;
        text-align: center;
    }
    #photo{
        height: 150px;
        width: 150px;
        border: 3px rgb(0, 0, 0) solid;
        border-radius: 50%;
        margin: 30px;
        text-align: center;
        /* 出現手指按紐 */
        cursor:pointer
        
    }
    #theFile{         
        display: none;
    }
    .memberDataBox{
        margin: 10px;
    }
    .memberDataForm{
        /* border: 3px rgb(251, 255, 16) solid; */
        width: 100%; /* 表單寬度 */
        line-height: 40px; /* 間距 */
    }
  
  .memberDataLabel{
    /* border: 3px rgb(255, 24, 24) solid; */
    width: 100px;
    display: inline-block;
  
  }
  .mdiDiv{
    /* border: 3px rgb(24, 255, 128) solid; */
    min-width: 200px;
    display: inline-block;
  }
  .mdiInput{
    height: 20px;
    border-radius: 20px;
    outline:none;
  }
  #sex{
      border: 2px rgb(0, 0, 0) solid;
      width: 165px;
      height: 25px;
      padding: 0 0 0 70px;
  }
  #submitBt{
      margin: 50px;
      width: 80px;
      height: 30px;
      border-radius: 20px;
  }


</style>
</head>
<body>
<div id="bigbox">
    <h1>教練個人資料</h1>

    <form action="/trainme/Photo" method="post"  enctype="multipart/form-data">

        <!-- 用label包住<input>時，按label就等投於按input -->
        <label>
            <img src="./images/1.jpg" alt="" id="photo" >
            <input type="file" accept="image/*" id="theFile" name="photo" class="fileInput"  value="" />
            <!-- accept : 限制上傳檔案類型 -->
             <input type="submit" id="submitBt" />
        </label>
    </form>
        
        <div class="memberDataBox">
        
        	<div class="stData">
                <p>姓名 :  <label>${LoginOK.name}</label></p>
                <p>Email : <label>${LoginOK.email}</label></p>
                <p>密碼 :  <label><a href="">修改密碼</a></label></p>
                <p>生日 :  <label>${LoginOK.birth}</label></p>
            </div>
        
        
            <!-- 表單內有夾帶檔案要使用，enctype="multipart/form-data" 要求不進行URL編碼，避免破壞檔案組成結構 -->
            <form action="/trainme/Tr_accountSet" method="post" class="memberDataForm" enctype="multipart/form-data">
            																		

                    <div class="memberDataBox">
                        <div class="memberDataLabel"><label for="txt_nickname">暱 稱：</label></div>
                        <div class="mdiDiv"><input type="text" class="mdiInput" id="txt_nickname" name="nickname" value="${LoginOK.nickname}" /></div>
                    </div>
                    <div class="memberDataBox">
                        <div class="memberDataLabel"> <label for="txt_phone">電 話：</label></div>
                        <div class="mdiDiv"> <input type="text" class="mdiInput"  id="txt_phone" name="phone" required="required" value="${LoginOK.phone}" /></div>
                    </div>
                    
                    <div class="memberDataBox">
                        <div class="memberDataLabel"> <label >地 址 : </label></div>
                        <div class="mdiDiv"> 
                             <select  id="city" name="city_id" class="mdiInput"  style=" width: 80px; height: 28px;">
                            	  <option value="0">請選擇</option>
                             </select>
                             <select  id="area" name="area_id" class="mdiInput" style="width: 80px; height: 28px;" >                              
                             </select>
                            <input type="text" class="mdiInput"  id="txt_add" name="address" value="${LoginOK.address}"  />
                        </div>
                    </div>
                   
                    <div class="memberDataBox">
                        <div class="memberDataLabel"><label for="txt_bank_account">帳 戶：</label></div>
                        <div class="mdiDiv"><input type="text" class="mdiInput" id="txt_bank_account" name=bank_account value="${LoginOK.bank_account}" /></div>
                    </div>
                        
                    <input type="submit" id="submitBt" />
                   
            </form>
            
        </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script>
    //送出表單
    let submitBt = document.getElementById('submitBt');
    submitBt.addEventListener('click',function () {
        let form =document.forms[0];
        form.submit();
    })

    //更換大頭照
    let theFile = document.getElementById('theFile');
    theFile.addEventListener('change',function () {
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

    var cityList = document.getElementById('city');

    axios.get("/trainme/CityList")
    .then(function (res) {
        var citys = res.data;
        console.log(citys);
        for(city of citys){

            var option = document.createElement("option");
            option.value = city.id;
            option.innerHTML = city.name;
            cityList.appendChild(option);
        }

    })

    cityList.addEventListener("change",function (e) {
    	
		const cityId = e.target.value;
		
        var areaList = document.getElementById('area');
        if(areaList.firstChild){
//        		 console.log(areaList.firstChild);
//             areaList.removeChild(areaList.firstChild);
        	areaList.innerHTML = "";
        }

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
        
    })
    
    
    
    

  

</script>
  
</body>
</html>

</body>
</html>