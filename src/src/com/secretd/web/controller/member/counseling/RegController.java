package src.com.secretd.web.controller.member.counseling;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/counseling/reg")
public class RegController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/counseling/reg.jsp").forward(request, response);
	}

/*	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	
	String id =request.getParameter("id");
	String title=request.getParameter("title");
	String content = request.getParameter("content");
	
	  String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
      String sql = "insert into Notice (id, title,content,writerId) values ((select IFNULL(MAX(CAST(id as unsigned)),0)+1 from Notice as b) ,?,?,?)";
      try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection conn = DriverManager.getConnection(url, "sist", "cclass");
         // Statement st = conn.createStatement();
         PreparedStatement st = conn.prepareStatement(sql);
         st.setString(1, title);
         st.setString(2, content);
         st.setString(3, "newlec");
         int result = st.executeUpdate();
        ������Ʈ �� row������ �˷���. st.executeUpdate
         st.close();
         conn.close();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      response.sendRedirect("list");
      sendRedirect�׳� ���������� ���� ��. ����� ������ߵǴϱ�
	
   super.doPost(request, response);
	}*/
}
