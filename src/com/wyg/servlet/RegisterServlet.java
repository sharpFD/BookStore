package com.wyg.servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.wyg.mapper.UserMapper;
import com.wyg.pojo.User;
import com.wyg.util.MybatisSqlSessionFactory;
import com.wyg.util.WriteUtil;

public class RegisterServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("GBK");
		if(req.getParameter("password")==null){		//AJAX判断用户是否存在
			Writer writer=resp.getWriter();
				User user=new User(req.getParameter("name"),null);
				SqlSession session=MybatisSqlSessionFactory.openSession();
				UserMapper mapper=session.getMapper(UserMapper.class);
				User exist = mapper.isExist(user);
				if(exist==null)
					writer.write("yes");
				else
					writer.write("no");
				session.close();
				if(writer!=null) writer.close();
		}
		else
		{
		String permit=req.getParameter("permit");
		if(permit==null) WriteUtil.write(req, resp, "请先阅读 用户注册协议", "请先阅读 用户注册协议");
		else{
		String username=req.getParameter("name");
		String password=req.getParameter("password");
		String zip=req.getParameter("zip");
		String address=req.getParameter("address");
		boolean isNum = req.getParameter("telephone").matches("[0-9]+");
		if(!isNum) {
			WriteUtil.write(req, resp, "注册失败","电话只能是数字");
		}
		else
		{
		String phone=req.getParameter("telephone");
		String email=req.getParameter("email");
		SqlSession session=MybatisSqlSessionFactory.openSession();
		UserMapper mapper=session.getMapper(UserMapper.class);
		User user=new User(username, password, zip, address, phone, email);
		User exist = mapper.isExist(user);
		if(exist!=null)//用户存在，注册失败
		{
			WriteUtil.write(req, resp, "注册失败", "用户已存在!");
			session.close();
		}
		else {	//用户不存在，注册成功
			WriteUtil.write(req, resp, "注册成功", "注册成功!");
			mapper.insertUser(user);
			session.commit();
			session.close();
		}
		}
	}}}
}
