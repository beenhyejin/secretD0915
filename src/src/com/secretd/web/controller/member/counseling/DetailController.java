package src.com.secretd.web.controller.member.counseling;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.secretd.web.dao.CounselingDao;
import src.com.secretd.web.dao.jdbc.JdbcCounselingDao;

@WebServlet("/member/counseling/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*------------------ù ��� �����ֱ�-----------------------------   */

		String _number = request.getParameter("number");
		String number = "";
		if (_number != null && !_number.equals(""))
			number = _number;
		String hit = request.getParameter("hit");
		CounselingDao counselingdao = new JdbcCounselingDao();
		request.setAttribute("detail", counselingdao.get(number));

		/*---------------------��۸�� �����ֱ�--------------------------------   */

		request.setAttribute("list", counselingdao.getComment(number));

		/*---------------------��۴޶� �г��� ã����--------------------------------   */
		request.setAttribute("m", counselingdao.getNickname(number));
		int result2 = counselingdao.updateHit(number);
		System.out.println("���" + result2);
		request.getRequestDispatcher("/WEB-INF/views/member/counseling/detail.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String number = request.getParameter("number");
		String content = request.getParameter("content");
		String nickname = request.getParameter("nickname");

		System.out.println(number);
		System.out.println(content);
		System.out.println(nickname);

		CounselingDao counselingdao = new JdbcCounselingDao();
		int result = counselingdao.updateComment(number, content, nickname);

		response.sendRedirect("detail?number=" + number);
	}
}
