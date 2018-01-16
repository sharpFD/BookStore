package com.wyg.servlet;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.util.AuthCode;
@WebServlet("/imageServlet")
public class getAuthCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 String authCode = AuthCode.getAuthCode();  
         
	        request.getSession().setAttribute("authCode", authCode); 
	          
	        try {  
	            //����ͼƬ  
	            ImageIO.write(AuthCode.getAuthImg(authCode), "JPEG", response.getOutputStream());  
	        } catch (IOException e){  
	            e.printStackTrace();  
	        }  
	    
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
