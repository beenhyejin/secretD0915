package src.com.secretd.web.controller.member.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.com.secretd.web.dao.MyLetterDao;
import src.com.secretd.web.dao.jdbc.JdbcMyLetterDao;

@WebServlet("/member/mypage/letter-reg")
public class LetterRegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Object _memberId = session.getAttribute("id");

		System.out.println(_memberId);
		if (_memberId == null) // 1. �α��� �� ���� ���ٸ� �ϴ� �α��� �Ϸ�����
			response.sendRedirect("../../login?returnURL=home");
		else {

			String memberId = _memberId.toString();
			MyLetterDao myletterdao = new JdbcMyLetterDao();

			request.setAttribute("num", myletterdao.getnum());
			request.setAttribute("id", memberId);

			request.getRequestDispatcher("/WEB-INF/views/member/mypage/letterReg.jsp").forward(request, response); // �̾
																														// ���
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int number = 0;
		String id = request.getParameter("id");
		String num = request.getParameter("num");

		number = Integer.parseInt(num);
		System.out.println(number);
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		MyLetterDao myletterdao = new JdbcMyLetterDao();
		int result = myletterdao.insert(id, number, title, content);

		response.sendRedirect("list");
	}
}
