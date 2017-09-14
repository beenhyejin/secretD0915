package src.com.secretd.web.controller.guest.id;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.secretd.web.dao.MemberDao;
import src.com.secretd.web.dao.jdbc.JdbcMemberDao;

@WebServlet("/guest/id/find-pw")
public class FindPwController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/guest/id/find-pw.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String pwd = null;
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		MemberDao memberDao = new JdbcMemberDao();

		pwd = memberDao.get(id, name, email);
		System.out.println(id);

		if (!(pwd == null))
			response.sendRedirect("pwdSearch_ok?id_check=" + id + "&pwd_check=" + pwd);
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<script>alert('�Է� �Ͻ� ������ ��ġ�ϴ� ȸ���� ã�� ���� �����ϴ�.');</script>");
		}
	}
}
