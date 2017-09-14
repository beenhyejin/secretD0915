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

@WebServlet("/guest/check-id")
public class IdCheckController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String check_id = request.getParameter("id");
		MemberDao dao = new JdbcMemberDao();
		boolean checkIdresult = dao.duplicateIdCheck(check_id);
		if (!checkIdresult)
			out.write("<script>alert('��밡���� ���̵��Դϴ�.');history.go(-1);self.close();</script>");
		else
			out.write("<script>alert('���̵� �ߺ��Ǿ����ϴ�');history.go(-1);self.close();</script>");
	}
}
