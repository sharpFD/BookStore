package com.wyg.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.wyg.pojo.Evaluate;
import com.wyg.util.Loaddata;

/**
 * Servlet implementation class EvaluateServlet
 */
@WebServlet("/EvaluateServlet")
public class EvaluateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("GBK");
		long userid=Long.parseLong(request.getParameter("userid"));
		long productid=Long.parseLong(request.getParameter("productid"));
		String content=request.getParameter("comments");
		String star=request.getParameter("star");
		String bookid=request.getParameter("bookid");
		HttpSession session=request.getSession();
		long orderid=(long) session.getAttribute("order");
		System.out.println("userid="+userid+"    productid="+productid+"  star="+star+"    orderid="+orderid);
		System.out.println(content);
		Evaluate evaluate=Loaddata.getUniqueEva(userid, productid, orderid);
		evaluate.setImages("");
		evaluate.setState(1);
		evaluate.setContent(content);
		evaluate.setStar(Integer.parseInt(star));
		Loaddata.updateEva(evaluate);
		response.sendRedirect("viewBook.jsp?id="+bookid);
	}
}
