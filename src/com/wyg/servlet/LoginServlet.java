package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import com.wyg.mapper.UserMapper;
import com.wyg.pojo.ShopCart;
import com.wyg.pojo.User;
import com.wyg.util.MybatisSqlSessionFactory;
import com.wyg.util.WriteUtil;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
			
		}
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				req.setCharacterEncoding("UTF-8");
				resp.setCharacterEncoding("GBK");
				String username=req.getParameter("name");
				String password=req.getParameter("password");
				User user=new User(username, password);
				SqlSession session=MybatisSqlSessionFactory.openSession();
				UserMapper mapper=session.getMapper(UserMapper.class);
				User exist = mapper.isExist(user);
				if(exist==null)//没有用户，登陆失败
				{
					WriteUtil.write(req, resp, "用户名不存在", "用户名不存在");
				}
				else {	
					User login = mapper.Login(user);
					if(login==null) {// 密码错误
						WriteUtil.write(req, resp, "用户名或密码错误", "用户名或密码错误");
					}
					else		//登陆成功
					{
						WriteUtil.write(req, resp, "登陆成功", "登陆成功");
						ShopCart shopCart=new ShopCart();
						HttpSession getsession=req.getSession();
						User getUser=mapper.findUserByName(username);
						getsession.setAttribute("user", getUser);
						getsession.setAttribute("shopCart", shopCart);
						System.out.println("实例化shopCart对象");
					}
				}
		}
}
