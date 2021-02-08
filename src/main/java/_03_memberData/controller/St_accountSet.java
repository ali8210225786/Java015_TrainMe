package _03_memberData.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 文字資料轉內碼
		request.setCharacterEncoding("UTF-8");
		// 先取出session物件
		// 要登入後才能使用的功能，要使用request.getSession(false);
		HttpSession session = request.getSession(false);

		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
		StudentBean sb = (StudentBean) session.getAttribute("LoginOK");

		// 取出使用者的memberId，後面的Cookie會用到

		Integer city_id = null;
		Integer area_id = null;
		String address = null;
		String phone = null;
		String nickname = null;
		Part photo = null;
		String photoPath = null;

		Collection<Part> parts = request.getParts();
//		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
				if(fldName.equals("photo")) {
					String filename = getSubmittedFileName(p);
					write(p,filename, sb);
					photoPath = "profile_photo/student/" + sb.getStNo() + "/"+ filename;
				}

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
					} else if (fldName.equals("phone")) {
						phone = value;
					} 
				}

			}
		}
		
		sb.setCity_id(city_id);
		sb.setArea_id(area_id);
		sb.setAddress(address);
		sb.setNickname(nickname);
		sb.setPhone(phone);
		sb.setPhoto(photoPath);
		
		MemDataDao memDataDao = new MemDataDao();
		
		int i = memDataDao.updateStudentData(sb);
		
		System.out.println(i);


		response.sendRedirect("/trainme/_03MemberData/studentData.jsp");
		return;
	}
	
	
	private String getSubmittedFileName(Part part) {
		String header = part.getHeader("Content-Disposition");
		Matcher matcher = fileNameRegex.matcher(header);
		matcher.find();
		
		String filename = matcher.group(1);
		if(filename.contains("\\")) {
			return filename.substring(filename.lastIndexOf("\\") + 1);
		}
		return filename;
	}
	
	private void write(Part photo,String filename, StudentBean sb) {  
		System.out.println(filename);
		int id = sb.getStNo();			 
		String folderPath = String.format("C:/_Java015-TrainMe/_workspace_Test/Trainme/src/main/webapp/images/profile_photo/student/%s", id);
		File theDir = new File(folderPath);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		
		try (InputStream in = photo.getInputStream();
			 OutputStream out = new FileOutputStream(folderPath + "/" + filename)){
			
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
