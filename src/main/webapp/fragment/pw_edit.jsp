<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <div class="content">
            <div class="title"><h3>帳號設定 > 修改密碼</h3></div>
              

                <div class="edit_area">
                        <div class="edit_box">
                            <label>輸入你的舊密碼</label><br>
                            <input type="password"  id="opswd" name="opswd" required>
                        </div>
                        <div class="edit_box">
                            <label>輸入新的密碼</label><br>
                            <input type="password"  id="opswd" name="npswd1" required>
                        </div>
                        <div class="edit_box">
                            <label>再一次輸入新的密碼</label><br>
                            <input type="password"  id="opswd" name="npswd2" required>
                        </div>
                        <div class="edit_box e_button">
                            <button class="cancel" onclick=history.go(-1)>取消</button>
                            <button class="save" id="saveBtn">儲存</button>
                        </div>
                    </div>
             </div>