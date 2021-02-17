package _02_login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.hibernate.sql.Select;

import _00_init.util.GlobalService;
import _01_register.model.MemberBean;
import _01_register.model.StudentBean;
import _01_register.model.TrainerBean;
import _01_register.service.MemberService;
import _01_register.service.impl.MemberServiceImpl;
import _04_Money2.dao.MemPointDao;
import _04_Money2.model.CourseBean;
import _04_Money2.model.MoneyBean;
import mail.MailDao;

@WebServlet("/tr-login.do")
public class LoginServlet_new extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isValid(String val) {
		return val != null && val.trim().length() > 0;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 定義存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();

		// 將errorMsgMap放入request物件內，識別字串為 "MsgMap"
		request.setAttribute("MsgMap", errorMsg);
		// 1. 讀取使用者輸入資料
		String email = request.getParameter("lgEmail");
		String password = request.getParameter("pswd");

		// 2. 進行必要的資料轉換
		// 無
		// 3. 檢查使用者輸入資料
		// 如果 userId 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (!isValid(email)) {
			errorMsg.put("EmailEmptyError", "信箱必須輸入");
		}
		// 如果 password 欄位為空白，放一個錯誤訊息到 errorMsgMap 之內
		if (!isValid(password)) {
			errorMsg.put("PasswordEmptyError", "密碼欄必須輸入");
		}

		// 如果 errorMsgMap 不是空的，表示有錯誤，交棒給login.jsp
		if (!errorMsg.isEmpty()) {
			errorResponse(request, response, errorMsg);
			return;
		}

		// 4. 進行 Business Logic 運算

		MemberService service = new MemberServiceImpl();
		MailDao mail = new MailDao();
// 		將密碼加密兩次，以便與存放在表格內的密碼比對
//		password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
		MemberBean mb = null;
		StudentBean sb = null;
		TrainerBean tb = null;
		try {
			// 判斷帳號密碼是否存在
			mb = service.checkIdPassword(email, password);

		
			// 若有找到帳號密碼
			if (mb != null) {

				// 判斷會員類別

				if (mb instanceof TrainerBean | mb instanceof StudentBean) {
					int type = (mb instanceof TrainerBean) ? 2 : 1;

					if (mail.checkPass(type, email)) {

						// 學生 - 跳轉頁面
//						if (type == 1) {
//
//							sb = service.selectStudent(email);
						
						
					// 學生 - 跳轉頁面
						if(type == 1) {	
							
							sb = (StudentBean) mb;
							session.setAttribute("LoginOK", sb);
							
							MemPointDao mpd = new MemPointDao();
							List<MoneyBean> mnb = mpd.getMoneyDetail(sb.getStNo(), sb.getType());
							List<CourseBean> cb=mpd.getCourseDetail(sb.getStNo());
							int name = 0;
							if (!(mnb == null)) {
								session.setAttribute("MoneyBean", mnb);
							}
							if (!(cb == null)) {
								session.setAttribute("CourseBean", cb);
							}

							response.sendRedirect("/trainme/index.jsp");
						}
						// 教練 - 跳轉頁面
//						if (type == 2) {
//							tb = (TrainerBean) mb;
					// 教練 - 跳轉頁面
						if(type == 2) {

							tb = (TrainerBean) mb;

							session.setAttribute("LoginOK", tb);
						
							
							response.sendRedirect("/trainme/index.jsp");
						}
						return;
					} else {
						errorMsg.put("noPass", "帳號尚未通過信箱驗證唷~");
						errorResponse(request, response, errorMsg);
						return;

					}
				}

				// 是否以經過信箱驗證

				// OK, 登入成功, 將mb物件放入Session範圍內，識別字串為"LoginOK"

			}

			// NG, 登入失敗, userid與密碼的組合錯誤，放相關的錯誤訊息到 errorMsgMap 之內
			errorMsg.put("LoginError", "該帳號不存在或密碼錯誤");
			errorResponse(request, response, errorMsg);
			return;

		} catch (RuntimeException ex) {
			errorMsg.put("LoginError", ex.getMessage());
			errorResponse(request, response, errorMsg);
			return;
		}

	}

	// 有錯誤時，回傳回原本頁面
	private void errorResponse(HttpServletRequest request, HttpServletResponse response, Map<String, String> errorMsg)
			throws ServletException, IOException {
		errorMsg.put("from", "logIn");
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}
}
