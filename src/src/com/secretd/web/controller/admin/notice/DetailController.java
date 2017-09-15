package src.com.secretd.web.controller.admin.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.secretd.web.dao.NoticeDao;
import src.com.secretd.web.dao.jdbc.JdbcNoticeDao;
import src.com.secretd.web.entity.Notice;

@WebServlet("/admin/notice/detail")
public class DetailController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String _id = request.getParameter("id");
		String id = "";
		if (_id != null && !_id.equals(""))
			id = _id;
		// Model
		NoticeDao dao = new JdbcNoticeDao();
		Notice n = dao.get(id);
		dao.getHitsCountup(n.getHit(),id);
		request.setAttribute("detail", n);
		request.getRequestDispatcher("/WEB-INF/views/admin/notice/detail.jsp").forward(request, response);
	}
}
