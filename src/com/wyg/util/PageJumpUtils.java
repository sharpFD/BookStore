package com.wyg.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.pojo.User;

public class PageJumpUtils {
	private static  List<Integer> allBookId=new ArrayList<>();
		public static boolean isLogin(HttpServletRequest request)
		{
			Cookie[] cookies=request.getCookies();
			boolean isLogin=false;
			if(cookies!=null){
			for(Cookie c:cookies)
			{
				String name=c.getName();
				String value=c.getValue();
				System.out.println("name="+name+"value="+value);
				if(name.equals("name")&&value!=null)
				{
					isLogin=true;  			//登陆了就不为空
				}
			}
			}
			return isLogin;
		}
		
		public static User getUserByreq(HttpServletRequest request)
		{
			Cookie[] cookies=request.getCookies();
			String getname=null;
			for(Cookie c:cookies)
			{
				String name=c.getName();
				if(name.equals("name"))
				{
					getname=c.getValue();		//登陆了就不为空
				}
			}
			return Loaddata.findUserByName(getname);
		}
		
		public static void setLogout(HttpServletRequest request,HttpServletResponse response)
		{
			Cookie cookie=new Cookie("name",null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		public static void addBookId(long id)
		{
			System.out.println("添加"+id);
			allBookId.add((int) id);
			System.out.println("size="+allBookId.size());
			System.out.println(allBookId.hashCode());
		}
		
		public static List<Integer> getAllBookId()
		{
			return allBookId;
		}
		
		public static void main(String[] args) {
			System.out.println(allBookId.size());
		}
}
