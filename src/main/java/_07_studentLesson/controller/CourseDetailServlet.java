package _07_studentLesson.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _01_register.model.StudentBean;
import _04_Money2.dao.MemPointDao;
import _04_Money2.model.CourseBean;
import _04_Money2.model.MoneyBean;
import _04_ShoppingCart.model.OrderBean;
import _04_ShoppingCart.model.OrderItemBean;
import _04_ShoppingCart.service.OrderService;
import _04_ShoppingCart.service.impl.OrderServiceImpl;

// 依照會員編號與訂單編號來讀取某筆訂單的所有資料，這些資料將封裝為一個OrderBean物件

@WebServlet("/_07_studentLesson/CourseDetail.do")
public class CourseDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession(false);
		MemPointDao mpd = new MemPointDao();
		StudentBean sb = (StudentBean) session.getAttribute("LoginOK");
		List<MoneyBean> mnb = mpd.getMoneyDetail(sb.getStNo(), sb.getType());
		List<CourseBean> cb=mpd.getCourseDetail(sb.getStNo());
		if (!(mnb == null)) {
			session.setAttribute("MoneyBean", mnb);
		}
		if (!(cb == null)) {
			session.setAttribute("CourseBean", cb);
		}

		response.sendRedirect("/trainme/_07_st_lesson/st_lesson.jsp");
		return;
	}
}