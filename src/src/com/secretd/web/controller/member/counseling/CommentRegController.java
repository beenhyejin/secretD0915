package src.com.secretd.web.controller.member.counseling;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.com.secretd.web.entity.Member;

@WebServlet("/member/comment-reg")
public class CommentRegController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String number = request.getParameter("number");
		String content = request.getParameter("content");
		String nickname = request.getParameter("nickname");

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "INSERT INTO `soonfacedb`.`Comment` (`number`, `text-number`, `content`, `date`, `member_id`) VALUES ((select ifnull(max(cast(number as unsigned)),0)+1 from soonfacedb.Comment as b),?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, "sist", "cclass");
			// Statement st = conn.createStatement();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, number);
			st.setString(2, content);
			st.setString(3, "2014-02-12");
			st.setString(4, "newlec");
			int result = st.executeUpdate();
			/* ������Ʈ �� row������ �˷���. st.executeUpdate */

			st.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("list");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Member m = null;

		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		String sql = "SELECT nickname from soonfacedb.Member where id=?";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, "sist", "cclass");
			// Statement st = conn.createStatement();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, "happ");
			ResultSet rs2 = st.executeQuery();

			if (rs2.next()) {
				m = new Member();
				m.setNickname(rs2.getString("nickname"));

			}

			rs2.close();
			st.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		response.sendRedirect("m");
		request.getRequestDispatcher("/WEB-INF/views/member/counseling/comment_reg.jsp").forward(request, response);
	}
}
