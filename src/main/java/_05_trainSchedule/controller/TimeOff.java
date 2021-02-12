package _05_trainSchedule.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import _00_init.util.JsonResponseService;
import _01_register.model.TrainerBean;
import _05_trainSchedule.model.CloseHour;
import _05_trainSchedule.model.TimeOffBean;
import _05_trainSchedule.service.TimeOffService;

/**
 * Servlet implementation class TimeOff
 */
@WebServlet("/TimeOff")
public class TimeOff extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		TrainerBean tb = (TrainerBean) session.getAttribute("LoginOK");
		String json = request.getParameter("data");
		
		CloseHour closeHour = (new Gson()).fromJson(json, CloseHour.class);
		
		TimeOffService tos = new TimeOffService();
		
		tos.deleteTimeOff(closeHour.getDateBegin(), closeHour.getDateEnd(), tb.getTrNo());
		
		for(String hour : closeHour.getCloseHour()) {
			String[] closeHourArr = hour.split("_");
			System.out.println(closeHourArr[0]+"===>"+closeHourArr[1]);
			Date date = null;
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dateX = sdf.parse(closeHourArr[0]);
				date = new Date(dateX.getTime());
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			TimeOffBean tob = new TimeOffBean(null, tb.getTrNo(), date, Integer.parseInt(closeHourArr[1]));
			
			
			tos.saveTimeOff(tob);			
		}
		
		JsonResponseService.responseMessage(response, "okkk");
		
		
		
		

		

	}

}
