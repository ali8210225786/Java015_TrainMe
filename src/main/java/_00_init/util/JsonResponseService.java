package _00_init.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class JsonResponseService {
	
	public static void responseMessage(HttpServletResponse response, String message) {
		try (PrintWriter out = response.getWriter()){
			//把list轉成Json檔
			String st = new Gson().toJson(message);
			// 回傳回瀏覽器
			out.write(st);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void responseData(HttpServletResponse response, List<?> data) {
		try (PrintWriter out = response.getWriter()){
			//把list轉成Json檔
			String st = new Gson().toJson(data);
			// 回傳回瀏覽器
			out.write(st);
			out.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
