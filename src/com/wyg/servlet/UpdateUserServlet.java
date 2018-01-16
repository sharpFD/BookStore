package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.pojo.User;
import com.wyg.util.Loaddata;
import com.wyg.util.WriteUtil;

public class UpdateUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("GBK");
		long getid=Loaddata.findUserByName(req.getParameter("name")).getId();
		String password=req.getParameter("password");
		String zip=req.getParameter("zip");
		String address=req.getParameter("address");
		String phone=req.getParameter("phone");
		String email=req.getParameter("email");
		User newUser=new User(getid, password, zip, address, phone, email);
		Loaddata.updateUser(newUser);
		WriteUtil.write(req, resp, "修改成功", "修改成功");
	}
}
