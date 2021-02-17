package _05_trainSchedule.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import _01_register.model.TrainerBean;
import _05_trainSchedule.model.CloseHour;
import _05_trainSchedule.service.TimeOffService;


@WebServlet("/TimeOff/get")
public class TimeOffList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		HttpSession session = request.getSession();
		TrainerBean tb = (TrainerBean) session.getAttribute("LoginOK");
		String dateBegin = request.getParameter("dateBegin");
		String dateEnd = request.getParameter("dateEnd");
//		System.out.println(dateBegin + dateEnd  + tb.getTrNo());
			
		TimeOffService timeOffService = new TimeOffService();
		List<String> list = timeOffService.queryTimeOffList(dateBegin, dateEnd, tb.getTrNo());
		
		try (PrintWriter out = response.getWriter()){
			
			String timeOffList = new Gson().toJson(list);
		
			out.write(timeOffList);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
