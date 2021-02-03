package _03_memberData.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import _01_register.model.MemberBean;
import _01_register.model.StudentBean;
import _01_register.model.TrainerBean;
import _03_memberData.dao.MemDataDao;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/St_accountSet")
public class St_accountSet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		// 要登入後才能使用的功能，要使用request.getSession(false);
		HttpSession session = request.getSession(false);

		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
		StudentBean sb = (StudentBean) session.getAttribute("LoginOK");

		// 取出使用者的memberId，後面的Cookie會用到

		Integer city_id = null;
		Integer area_id = null;
		String address = null;
		String photo = null;
		String nickname = null;

		Collection<Part> parts = request.getParts();
//		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				// 1. 讀取使用者輸入資料，進行必要的資料轉換
				if (p.getContentType() == null) {
					if (fldName.equals("city_id")) {
						city_id = Integer.parseInt(value);
					} else if (fldName.equals("area_id")) {
						area_id = Integer.parseInt(value);
					} else if (fldName.equals("address")) {
						address = value;
					} else if (fldName.equals("nickname")) {
						nickname = value;
					}
				}

			}
		}
		
		sb.setCity_id(city_id);
		sb.setArea_id(area_id);
		sb.setAddress(address);
		sb.setNickname(nickname);
		
		MemDataDao memDataDao = new MemDataDao();
		
		int i = memDataDao.updateStudentData(sb);
		
		System.out.println(i);


		response.sendRedirect("/_03MemberData/studentData.jsp");
		return;
	}

}
