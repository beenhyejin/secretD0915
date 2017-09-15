package src.com.secretd.web.controller.admin.hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.com.secretd.web.dao.HospitalDao;
import src.com.secretd.web.dao.jdbc.JdbcHospitalDao;
import src.com.secretd.web.entity.Hospital;

@WebServlet("/admin/hospital/list")
public class ListController extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*int page = 1;
	String _page = request.getParameter("p");
	if (_page != null && !_page.equals(""))
		page = Integer.parseInt(_page);
	String _query = request.getParameter("name");
	String query = "";
	if (_query != null && !_query.equals(""))
		query = _query;
	List<Hospital> list = null;
	int count = 0;
	
	HospitalDao hospitalDao = new JdbcHospitalDao();
	list = hospitalDao.getList(page, query);
	count = hospitalDao.getCount();*/
	String _query = request.getParameter("name");
	   String _page = request.getParameter("p");
	   String _subject =request.getParameter("sub");
	   String _addr = request.getParameter("addr");
	   
	   String query = "";
	   String subject="";
	   String addr="";
	   int page = 1;
	   
	   List<Hospital> list = null;
	   int count = 0;
	   
	   if (_page != null && !_page.equals(""))
	         page = Integer.parseInt(_page);
	   if (_query != null && !_query.equals(""))
	      query = _query;
	   if (_addr != null && !_addr.equals(""))
	      addr = _addr;   
	   if (_subject != null && !_subject.equals(""))
	      subject = _subject;

	   
	   HospitalDao hospitalDao = new JdbcHospitalDao();
	   
	   list = hospitalDao.getList(query,subject,addr,page);
	   count = hospitalDao.getCount(query,subject,addr);
	
	request.setAttribute("list", list);
	request.setAttribute("count", count);
	request.getRequestDispatcher("/WEB-INF/views/admin/hospital/list.jsp").forward(request, response);
}

@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int result =0;
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	PrintWriter out = response.getWriter();
	HttpSession session = request.getSession();
	if(session.getAttribute("id") == null)
		out.write("<script>alert('로그인이 필요합니다');location.href='../../member/login?returnURL=../admin/hospital/list;</script>");
	else{
		String[] sCheck = request.getParameterValues("check");
		HospitalDao dao = new JdbcHospitalDao();
		for(int i=0;i<sCheck.length;i++) {
			System.out.println(sCheck[i]);
			result = dao.delete(sCheck[i]);
		if(result>0)
			out.write("<script>alert('병원삭제 성공!');location.href='list';</script>");	
		else
			out.write("<script>alert('죄송합니다. 삭제에 실패하였습니다.');location.href='../admin/hospital/list';</script>");
		}
	}
	}
}
