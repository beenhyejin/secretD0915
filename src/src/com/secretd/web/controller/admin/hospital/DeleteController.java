package src.com.secretd.web.controller.admin.hospital;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.com.secretd.web.dao.HospitalDao;
import src.com.secretd.web.dao.jdbc.JdbcHospitalDao;

@WebServlet("/admin/hospital/delete")
public class DeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int result = 0;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String h_id = request.getParameter("h_id");
		if (session.getAttribute("id") == null)
			out.write(
					"<script>alert('�α����� �ʿ��մϴ�');location.href='../../member/login?returnURL=../admin/hospital/edit?h_id="
							+ h_id + "';</script>");
		else {
			String[] sCheck = request.getParameterValues("check");
			HospitalDao dao = new JdbcHospitalDao();
			for (int i = 0; i < sCheck.length; i++) {
				System.out.println(sCheck[i]);
				result = dao.delete(sCheck[i]);
				if (result > 0)
					response.sendRedirect("list");
				else
					out.write("<script>alert('�˼��մϴ�. ������ �����Ͽ����ϴ�.');location.href='../admin/notice/detail?id=" + h_id
							+ "';</script>");

			}
		}
	}
}
