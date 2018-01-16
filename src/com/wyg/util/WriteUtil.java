package com.wyg.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteUtil {
	
		public static void write(HttpServletRequest req,HttpServletResponse resp,String title,String alertcontent)
		{
			PrintWriter writer=null;
			try {
				writer = resp.getWriter();
				writer.println("<html>");
				writer.println("<title>"+title+"</title>");
				writer.println(" <script type=\"text/javascript\">");
				writer.println("alert(\""+alertcontent+"\");");
				switch (title) {
					case "注册成功":
						writer.println("window.location.href=\"login.jsp\";");
					break;
					
					case "登陆成功":
						//session.setAttribute("name",req.getParameter("name"));
						if(req.getParameter("name")!=null)
						{
							Cookie cookie=new Cookie("name",req.getParameter("name"));
							resp.addCookie(cookie);
							cookie.setMaxAge(-1);
							writer.println("window.location.href=\"index.jsp\";");
						}
						else {
							writer.println("window.location.href=\"register.jsp\";");
						}
						break;
						
					case "注册失败":
						writer.println("window.location.href=\"register.jsp\";");//注册失败，跳转重新注册
						break;
						
					case "登出成功":
						writer.println("window.location.href=\"index.jsp\";");	
						break;
						
					case "修改成功":
						writer.println("window.location.href=\"userinfo.jsp\";");
						break;
				default:
					writer.println("window.location.href=\"login.jsp\";");//登陆失败，跳转登陆
					break;
				}
				writer.println("</script>");
				writer.println(" </html>");
				if(writer!=null)writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
}