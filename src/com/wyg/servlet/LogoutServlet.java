package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wyg.pojo.ShopCart;
import com.wyg.util.PageJumpUtils;
import com.wyg.util.WriteUtil;

public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@SuppressWarnings("unused")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PageJumpUtils.setLogout(req, resp);
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("GBK");
		WriteUtil.write(req, resp, "登出成功","注销成功");
		ShopCart shopCart=(ShopCart) req.getSession().getAttribute("shopCart");
		shopCart=null;
	}
}
