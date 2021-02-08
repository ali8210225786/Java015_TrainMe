package _04_Money.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import _03_memberData.dao.MemDataDao;
import _04_Money.dao.MoneyDao;
import _04_Money.model.MoneyBean;
import model.AreaBean;


@WebServlet("/MoneyController")
public class MoneyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		response.setContentType("application/json; charset=utf-8");
//		System.out.println("=============");
		String id = request.getParameter("id");
		String type = request.getParameter("type");
//		System.out.println(id + " , "+ type);
		
	
		MoneyDao dao = new MoneyDao();
		List<MoneyBean> list = dao.moneyList(Integer.parseInt(id),Integer.parseInt(type));


		
		try (PrintWriter out = response.getWriter()){
			
			String moneys = new Gson().toJson(list);
			
			out.write(moneys);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
