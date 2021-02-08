package _01_register.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.gson.Gson;


import _01_register.model.StudentBean;
import _01_register.model.TrainerBean;
import _01_register.service.MemberService;
import _01_register.service.impl.MemberServiceImpl;
import mail.SendingEmail;
import model.GymBean;


@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/Train_RegisterServletMP_new.do")

public class Train_RegisterServletMP_new extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 設定密碼欄位必須由小寫字母、數字資料組合而成，且長度不能小於六個字元
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z]).{6,})";
	private Pattern patternPsw = null;
	private Matcher matcherPsw = null;

	// 設定Email欄位必須必須包含@符號；必須包含點；點和@之間必須有字元
	private static final String Email_PATTERN = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
	private Pattern patternMail = null;
	private Matcher matcherMail = null;
	
	 

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		

		
		
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();

		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		String name = "";
		String phone = "";
		Date birthday = null;
		String email = "";
		String password = "";
		String passwordcheck = "";
		String id = "";
		String sex = "";
		Integer gymId = null;
		Integer gympassword = null;
		String myHash = "";
		
		Random theRandom = new Random();
		theRandom.nextInt(999999);
		myHash = DigestUtils.md5Hex("" +	theRandom);
		

		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();
//		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);
//				

				// 1. 讀取使用者輸入資料，進行必要的資料轉換
				if (p.getContentType() == null) {
					if (fldName.equals("tr_name")) {
						name = value;
					} else if (fldName.equals("tr_phone")) {
						phone = value;
					} else if (fldName.equals("tr_birthday")) {
						try {
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
							java.util.Date date = simpleDateFormat.parse(value);
							birthday = new Date(date.getTime());

						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else if (fldName.equals("tr_email")) {
						email = value;
					} else if (fldName.equals("tr_password")) {
						password = value;
					} else if (fldName.equals("tr_passwordcheck")) {
						passwordcheck = value;
					} else if (fldName.equals("tr_id")) {
						id = value;
					} else if (fldName.equals("tr_sex")) {
						sex = value;
					}  else if (fldName.equals("gymId")) {
//						gymId =  value;
						gymId =  Integer.valueOf(value);
						
					} else if (fldName.equals("gympassword")) {
						gympassword = Integer.valueOf(value);
					}

				}
			}

			// 3. 檢查使用者輸入資料
			if (name == null || name.trim().length() == 0) {
				errorMsg.put("errorName", "姓名必須輸入");  
			}
			if (phone == null || phone.trim().length() == 0) {
				errorMsg.put("errorPhone", "電話必須輸入");
			}
			if (birthday == null) {
				errorMsg.put("errorbirthday", "生日必須輸入");
			}
			if (email == null || email.trim().length() == 0) {
				errorMsg.put("errorEmail", "電子郵件欄必須輸入");
			}
			if (password == null || password.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼必須輸入");
			}
			if (passwordcheck == null || passwordcheck.trim().length() == 0) {
				errorMsg.put("errorPpasswordCcheckEmpty", "密碼確認必須輸入");
			}
			if (id == null || id.trim().length() == 0) {
				errorMsg.put("errorId", "身分證必須輸入");
			}
			

			// 如果沒有錯誤
			if (errorMsg.isEmpty()) {
				// 檢查密碼格式
				patternPsw = Pattern.compile(PASSWORD_PATTERN);
				matcherPsw = patternPsw.matcher(password);
				if (!matcherPsw.matches()) {
					errorMsg.put("tr_passwordError", "密碼至少含有一個小寫字母、且長度不能小於六個字元");
				}

				// 檢查Email格式
				patternMail = Pattern.compile(Email_PATTERN);
				matcherMail = patternMail.matcher(email);
				if (!matcherMail.matches()) {
					errorMsg.put("tr_emailError", "必須包含@符號，必須包含點，點和@之間必須有字元");
					
				}

				// 檢查密碼欄位和密碼確認欄位是否一致
				if (!password.equals(passwordcheck)) {
					errorMsg.put("tr_passwordCheckError", "密碼欄位並須和密碼確認一致");
				}
			}
			
			// 如果有錯誤
			if (!errorMsg.isEmpty()) {
				errorResponse(request, response, errorMsg);
				return;
			}
			
			
			try {
				MemberService service = new MemberServiceImpl();
				
				// 檢查信箱是否已經存在
				if (service.idExists(email)) {
					errorMsg.put("tr_emailExists", "此信箱已存在，請換新信箱");
					errorResponse(request, response, errorMsg);
					return;
				}
				
				// 檢查身分證是否已經存在
				if (service.idNumberExists(id)) {
					errorMsg.put("tr_idNumberExists", "此身分證已經使用過囉");
					errorResponse(request, response, errorMsg);
					return;
				}
				
				
				// 檢查健身房驗證碼是否正確
				if(service.checkverification(gymId) != gympassword) {
					errorMsg.put("tr_errorverification", "驗證碼錯誤");
					errorResponse(request, response, errorMsg);
					return;
				}



					// 將所有會員資料封裝到StudentBean(類別的)物件
					TrainerBean trainer = new TrainerBean(null, null, name, phone, email, birthday, password, id, sex, gymId, null, null, myHash);
					// 呼叫StudentService的saveTrainer方法
					int n = service.saveTrainer(trainer);
					if (n == 1) {
						SendingEmail se = new SendingEmail(2,email, myHash);
						se.sendMail();
//=============================================================================================註冊成功後導入頁面	
						msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
						response.sendRedirect("/dmot/_01_tr_register/register_success.jsp");
						return;
					} else {
						errorMsg.put("errorIdDup", "新增此筆資料有誤(RegisterServlet)");
					}
				
				
				

				// 5.依照 Business Logic 運算結果來挑選適當的畫面
					
				// 若有錯誤訊息	
				if (!errorMsg.isEmpty()) {
					errorResponse(request, response, errorMsg);
					return;
				}

			} catch (Exception e) {
				e.printStackTrace();
				errorMsg.put("errorIdDup", e.getMessage());
				errorResponse(request, response, errorMsg);
			}
		}
	}
	
	// 當有錯誤時的處理
	private void errorResponse(HttpServletRequest request, HttpServletResponse response, Map<String, String> errorMsg)
			throws ServletException, IOException {
		errorMsg.put("from", "tr_signUp");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}


}