package _04_Money2.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

import _01_register.model.StudentBean;
import _01_register.service.MemberService;
import _01_register.service.impl.MemberServiceImpl;
import _04_Money2.dao.MemPointDao;
import _04_Money2.model.MoneyBean;
import mail.SendingEmail;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/trainme/_06_st_point/Student_CreditCardServlet.do")
public class Student_CreditCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 設定密碼欄位必須由小寫字母、數字資料組合而成，且長度不能小於六個字元
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z]).{6,})";
	private Pattern patternPsw = null;
	private Matcher matcherPsw = null;

	// 設定Email欄位必須必須包含@符號；必須包含點；點和@之間必須有字元
	private static final String Email_PATTERN = "^\\w{1,63}@[a-zA-Z0-9]{2,63}\\.[a-zA-Z]{2,63}(\\.[a-zA-Z]{2,63})?$";
	private Pattern patternMail = null;
	private Matcher matcherMail = null;

	private boolean isValid(String val) {
		return val != null && val.trim().length() > 0;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();

		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession(false);
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息
		StudentBean sb = (StudentBean) session.getAttribute("LoginOK");
		// 1. 讀取使用者輸入資料
		String cardNo1 = request.getParameter("CardNo1");
		String cardNo2 = request.getParameter("CardNo2");
		String cardNo3 = request.getParameter("CardNo3");
		String cardNo4 = request.getParameter("CardNo4");
		String expiryMonth = request.getParameter("ExpiryMonth");
		String expiryYear = request.getParameter("ExpiryYear");
		String cardCVV = request.getParameter("CardCVV");
		String name = request.getParameter("Name");
		String phone = request.getParameter("Tel");
		String email = request.getParameter("Email");
		String zip = request.getParameter("ZIP");
		String address = request.getParameter("Address");
		String money = request.getParameter("money");
		System.out.println(money);
		System.out.println(cardNo1);
		// 2. 進行必要的資料轉換
		// 無
		// 3. 檢查使用者輸入資料
		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
//				if ((cardNo1+cardNo2+cardNo3+cardNo4).trim().length()<16) {
//					errorMsg.put("cardNoError", "必須輸入有效信用卡卡號");
//				}
//				if (isValid(cardNo1+cardNo2+cardNo3+cardNo4)) {
//					errorMsg.put("cardNoEmptyError", "信用卡卡號必須輸入");
//				}
		if (!isValid(expiryMonth)) {
			errorMsg.put("expiryMonthEmptyError", "有效月份必須輸入");
		}
		if (!isValid(expiryYear)) {
			errorMsg.put("expiryYearEmptyError", "有效年限必須輸入");
		}
		if (!isValid(cardCVV)) {
			errorMsg.put("cardCVVEmptyError", "背面後三碼必須輸入");
		}
		if (!isValid(name)) {
			errorMsg.put("nameEmptyError", "持卡人姓名必須輸入");
		}
		if (!isValid(phone)) {
			errorMsg.put("phoneEmptyError", "手機號碼必須輸入");
		}
		if (!isValid(email)) {
			errorMsg.put("EmailEmptyError", "信箱必須輸入");
		}
		if (!isValid(zip)) {
			errorMsg.put("zipEmptyError", "郵遞區號必須輸入");
		}
		if (!isValid(address)) {
			errorMsg.put("addressEmptyError", "帳單地址必須輸入");
		}

		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒給login.jsp
		if (!errorMsg.isEmpty()) {
			errorResponse(request, response, errorMsg);
			return;
		}

//		// 取出HTTP multipart request內所有的parts
//		Collection<Part> parts = request.getParts();
////		GlobalService.exploreParts(parts, request);
//		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
//		if (parts != null) { // 如果這是一個上傳資料的表單
//			for (Part p : parts) {
//				String fldName = p.getName();
//				String value = request.getParameter(fldName);
//
//				// 1. 讀取使用者輸入資料，進行必要的資料轉換
//				if (p.getContentType() == null) {
//					if (fldName.equals("st_name")) {
//						name = value;
//					} else if (fldName.equals("st_phone")) {
//						phone = value;
//					} else if (fldName.equals("st_birthday")) {
//						try {
//							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//							java.util.Date date = simpleDateFormat.parse(value);
//							birthday = new Date(date.getTime());
//
//						} catch (ParseException e) {
//							e.printStackTrace();
//						}
//					} else if (fldName.equals("st_email")) {
//						email = value;
//					} else if (fldName.equals("st_password")) {
//						password = value;
//					} else if (fldName.equals("st_passwordcheck")) {
//						passwordcheck = value;
//					} else if (fldName.equals("st_id")) {
//						id = value;
//					} else if (fldName.equals("st_sex")) {
//						sex = value;
//					}
//
//				}
//			}
//
//			// 3. 檢查使用者輸入資料
//			if (name == null || name.trim().length() == 0) {
//				errorMsg.put("errorName", "姓名必須輸入");
//			}
//			if (phone == null || phone.trim().length() == 0) {
//				errorMsg.put("errorPhone", "電話必須輸入");
//			}
//			if (birthday == null) {
//				errorMsg.put("errorbirthday", "生日必須輸入");
//			}
//			if (email == null || email.trim().length() == 0) {
//				errorMsg.put("errorEmail", "電子郵件欄必須輸入");
//			}
//			if (password == null || password.trim().length() == 0) {
//				errorMsg.put("errorPasswordEmpty", "密碼必須輸入");
//			}
//			if (passwordcheck == null || passwordcheck.trim().length() == 0) {
//				errorMsg.put("errorPpasswordCcheckEmpty", "密碼確認必須輸入");
//			}
//			if (id == null || id.trim().length() == 0) {
//				errorMsg.put("errorId", "身分證必須輸入");
//			}
//
//			// 如果沒有錯誤
//			if (errorMsg.isEmpty()) {
//				// 檢查密碼格式
//				patternPsw = Pattern.compile(PASSWORD_PATTERN);
//				matcherPsw = patternPsw.matcher(password);
//				if (!matcherPsw.matches()) {
//					errorMsg.put("st_passwordError", "密碼至少含有一個小寫字母，且長度不能小於六個字元");
//				}
//
//				// 檢查Email格式
//				patternMail = Pattern.compile(Email_PATTERN);
//				matcherMail = patternMail.matcher(email);
//				if (!matcherMail.matches()) {
//					errorMsg.put("st_emailError", "必須包含@符號，必須包含點，點和@之間必須有字元");
//				}
//
//				// 檢查密碼欄位和密碼確認欄位是否一致
//				if (!password.equals(passwordcheck)) {
//					errorMsg.put("st_passwordCheckError", "密碼欄位並須和密碼確認一致");
//				}
//			}
//			// 如果有錯誤
//			if (!errorMsg.isEmpty()) {
//				errorResponse(request, response, errorMsg);
//				return;
//			}
//			try {
//				MemberService service = new MemberServiceImpl();
//				
//				// 檢查信箱是否已經存在
//				if (service.idExists(email)) {
//					errorMsg.put("st_errorIdDup", "此信箱已存在，請換新信箱");
//					errorResponse(request, response, errorMsg);
//					return;
//				}
//				
//
//				// 檢查身分證是否已經存在
//				if (service.idNumberExists(id)) {
//					errorMsg.put("st_idNumberExists", "此身分證已經使用過囉");
//					errorResponse(request, response, errorMsg);
//					return;
//				}
//				
//				
//				
////=============================================================================================密碼加密					
//					// 為了配合Hibernate的版本。
		// 要在此加密，不要在 dao.saveMember(mem)進行加密
////				password = GlobalService.getMD5Endocing(
////						GlobalService.encryptString(password));
//
//					// 將所有會員資料封裝到StudentBean(類別的)物件
//
//					
//
//					StudentBean stdent = new StudentBean(null, null, name, phone, email, birthday, password, id, sex, null, null, myHash);
		
//	String value=session.getParameter(name);
		int id = 0;
		int targetId = sb.getStNo();
		int type = sb.getType();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		java.sql.Date changeTime = new java.sql.Date(date.getTime());
		int changeAmount = Integer.parseInt(money);
		int total = 0;
		System.out.println(targetId);
		MemPointDao mp = new MemPointDao();
		int size = mp.getMoneyDetail(sb.getStNo(), sb.getType()).size();
		total = mp.getMoneyDetail(sb.getStNo(), sb.getType()).get(size - 1).getTotal() + changeAmount;
		MoneyBean mb = new MoneyBean(id, targetId, type, changeTime, changeAmount, total);
		int n = mp.saveMoney(mb);
		if (n == 1) {
//=============================================================================================註冊成功後導入頁面	

			response.sendRedirect("/trainme/index.jsp");
			return;
		} else {
			errorMsg.put("errorIdDup", "新增此筆資料有誤(RegisterServlet)");
		}

////					// 呼叫StudentService的saveStudent方法
//					int n = service.saveStudent(stdent);
//					if (n == 1) {
////=============================================================================================註冊成功後導入頁面	
//						SendingEmail se = new SendingEmail(1 ,email, myHash);
//						se.sendMail();
//						msgOK.put("InsertOK", "<Font color='red'>新增成功，請開始使用本系統</Font>");
//						response.sendRedirect("/trainme/_02_login/login_success.jsp");
//						return;
//					} else {
//						errorMsg.put("errorIdDup", "新增此筆資料有誤(RegisterServlet)");
//					}
//				
//
//				// 5.依照 Business Logic 運算結果來挑選適當的畫面
//				
		// 若有錯誤訊息
//				if (!errorMsg.isEmpty()) {
//					errorResponse(request, response, errorMsg);
//					return;
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//				errorMsg.put("errorIdDup", e.getMessage());
//				errorResponse(request, response, errorMsg);
//			}
//		}
	}

	private void MoneyBean(int id, int type, Date changeTime, Object object, Object object2) {
		// TODO Auto-generated method stub

	}

	// 當有錯誤時的處理
	private void errorResponse(HttpServletRequest request, HttpServletResponse response, Map<String, String> errorMsg)
			throws ServletException, IOException {
		errorMsg.put("from", "st_checkout");
		System.out.println("準備要轉跳頁面");
		RequestDispatcher rd = request.getRequestDispatcher("/_06_st_point/st_checkout.jsp");
		rd.forward(request, response);
	}

}