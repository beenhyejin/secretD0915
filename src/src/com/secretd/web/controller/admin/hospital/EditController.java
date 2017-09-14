package src.com.secretd.web.controller.admin.hospital;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.com.secretd.web.dao.HospitalDao;
import src.com.secretd.web.dao.jdbc.JdbcHospitalDao;
import src.com.secretd.web.entity.Hospital;

@WebServlet("/admin/hospital/edit")
public class EditController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  _h_id = request.getParameter("h_id"); 
		   String h_id="";
		   if(_h_id != null && !_h_id.equals(""))
				h_id=_h_id;
			Hospital h= null;
			//-------------DB(dao)------------------
			HospitalDao hospitalDao = new JdbcHospitalDao();
			h=hospitalDao.get(h_id);	
			request.setAttribute("detail",h);
	        request.getRequestDispatcher("/WEB-INF/views/admin/hospital/edit.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		   response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			HttpSession session = request.getSession();
			if(session.getAttribute("id") == null)
				out.write("<script>alert('�α����� �ʿ��մϴ�');location.href='../../member/login?returnURL=../admin/hospital/edit?id="+id+"';</script>");
			else {		
				String path = "/upload";
				ServletContext context = request.getServletContext();
				path = context.getRealPath(path);
				MultipartRequest req = new MultipartRequest(request, path, 1024*10124*1000,"UTF-8",new DefaultFileRenamePolicy());//lib -> cos.jar
			  
			   String h_id = req.getParameter("h_id");
			   String subject = req.getParameter("subject");
			   String name= req.getParameter("name");
			   String address= req.getParameter("address"); 
			   String phone_number = req.getFilesystemName("phone_number");

			   //-------------------DB(dao)-----------------------------------
			   HospitalDao hospitalDao = new JdbcHospitalDao();
			   int result = hospitalDao.edit(h_id,subject,name,address,phone_number);
			   if(result>0)
				   response.sendRedirect("list");
			   else {
				   out.write("<script>alert('�˼��մϴ�. ������ ������ ������ϴ�.');location.href='edit?h_id="+h_id+"';</script>");
			   }
			}
	}
}
