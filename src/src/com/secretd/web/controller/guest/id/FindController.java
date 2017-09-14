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

@WebServlet("/guest/id/find")
public class FindController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/guest/id/find.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String id = null;
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		System.out.println(name);
		System.out.println(email);

		MemberDao memberDao = new JdbcMemberDao();

		id = memberDao.get(name, email);
		System.out.println(id);

		if (!(id == null))	// id ���� ��
			response.sendRedirect("info?id=" + id);
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<script>alert('�Է� �Ͻ� ������ ��ġ�ϴ� ȸ���� ã�� ���� �����ϴ�.');history.go(-1);</script>");
		}
	}
}
