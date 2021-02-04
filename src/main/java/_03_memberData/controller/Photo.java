package _03_memberData.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
@WebServlet("/Photo")
public class Photo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private final Pattern fileNameRegex = Pattern.compile("filename=\"(.*)\"");

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Part photo = request.getPart("photo");
		String filename = getSubmittedFileName(photo);
		write(photo,filename);
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
	
	private void write(Part photo,String filename) {
		
		try (InputStream in = photo.getInputStream();
			 OutputStream out = new FileOutputStream(
				String.format("C:\\_Java015-TrainMe\\_workspace_Test\\Trainme\\data\\images\\%s", filename))){
			
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
