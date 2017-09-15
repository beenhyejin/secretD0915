package src.com.secretd.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.com.secretd.web.dao.NoticeDao;
import src.com.secretd.web.entity.Notice;

public class JdbcNoticeDao implements NoticeDao{

	public List<Notice> getList(int page, String query) {
		List<Notice> list = null;
		String sql = "SELECT * FROM Notice WHERE title like ? order by regDate DESC limit ?,10";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		int offset = (page - 1) * 10;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, offset);

			ResultSet rs = st.executeQuery();

			list = new ArrayList<>();

			while (rs.next()) {
				Notice n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setWriterId(rs.getString("WRITERID"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setRegDate(rs.getDate("REGDATE"));

				list.add(n);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public int getCount() {
		int count = 0;
		String sqlCount = "SELECT COUNT(id) as count FROM Notice";

		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "soonface", "2014");

			// ?��?��
			Statement stCount = con.createStatement();

			// 결과 �??��?���?
			ResultSet rsCount = stCount.executeQuery(sqlCount);

			rsCount.next();

			// Model
			count = rsCount.getInt("count");

			// 결과 ?��?��?���?

			rsCount.close();
			stCount.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	public Notice get(String id) {

		// ------------------출력-----------------
		Notice n = null;
		String sql = "SELECT * FROM Notice WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			// ?��?��
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			// 결과 �??��?���?
			ResultSet rs = st.executeQuery();
			// 결과 ?��?��?���?
			if (rs.next()) {
				n = new Notice();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("TITLE"));
				n.setHit(rs.getInt("HIT"));
				n.setContent(rs.getString("CONTENT"));
				n.setRegDate(rs.getDate("REGDATE"));
				n.setWriterId(rs.getString("WRITERID"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public void edit(String id, String title, String content) {
		String sql = "UPDATE Notice SET title= ?,content = ? WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, id);
			int result = st.executeUpdate();
			if (result == 1)
				System.out.println("?��?��?���?");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(String title, String content) {
		String sql = "INSERT INTO Notice(id,title, content, writerId) VALUES((select ifnull(max(cast(id as signed integer)),0)+1 from Notice as b),?,?,?)";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, title);
			st.setString(2, content);
			st.setString(3, "robin");// !!!!!로그?��?�� ?��?��?���? 바꾸�?
			int result = st.executeUpdate();
			if(result==1)
				System.out.println("�??���? ?���?");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String id) {
		String sql = "DELETE FROM Notice WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		// JDBC ?��?��?���? 로드
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// ?���? / ?���?
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			int result = st.executeUpdate();
			if (result == 1)
				System.out.println("?��?��?���?");
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void getHitsCountup(int hit,String id) {
		hit++;
		String sql = "UPDATE Notice SET hit= ? where id =?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, hit);
			st.setString(2, id);
			int result = st.executeUpdate();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
