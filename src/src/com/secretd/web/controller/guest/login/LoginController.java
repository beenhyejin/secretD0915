package src.com.secretd.web.controller.guest.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.secretd.web.dao.MemberDao;
import src.com.secretd.web.dao.jdbc.JdbcMemberDao;
import src.com.secretd.web.entity.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		MemberDao memberDao = new JdbcMemberDao();
		Member member = memberDao.get(id);

		if (member == null) {
			out.println("<script language='javascript'>");
			out.println("alert('사용자 아이디 또는 비밀번호가 올바르지 않습니다.');history.go(-1);");
			out.println("</script>");
			out.flush();
		} else if (!member.getPwd().equals(pwd)) {  
			out.println("<script language='javascript'>");
			out.println("alert('사용자 아이디 또는 비밀번호가 올바르지 않습니다.');history.go(-1)");
			out.println("</script>");
			out.flush();
			//response.sendRedirect("login?error=2");
		} else {
			request.getSession().setAttribute("id", id);
			String url = request.getParameter("returnURL");
			if (url != null)
				response.sendRedirect(url);
			else
				response.sendRedirect("index");

		}
	}
}
