package src.com.secretd.web.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import src.com.secretd.web.dao.HospitalDao;
import src.com.secretd.web.entity.Hospital;

public class JdbcHospitalDao implements HospitalDao {

	@Override
	public List<Hospital> getList(int page, String query) {
		List<Hospital> list = null;
		String sql = "SELECT * FROM Hospital WHERE name like ? order by id ASC limit ?,10";
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
				Hospital h = new Hospital();
				h.setId(rs.getString("id"));
				h.setSubject(rs.getString("subject"));
				h.setName(rs.getString("name"));
				h.setAddress(rs.getString("address"));
				h.setPhone_number(rs.getString("phone_number"));
				list.add(h);
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

	@Override
	public int getCount() {
		int count = 0;
		String sqlCount = "SELECT COUNT(id) as count FROM Hospital";

		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			Statement stCount = con.createStatement();
			ResultSet rsCount = stCount.executeQuery(sqlCount);
			rsCount.next();
			count = rsCount.getInt("count");
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

	@Override
	public int delete(String h_id) {
		int result=0;
		String sql = "DELETE FROM Hospital WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, h_id);
			result = st.executeUpdate();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert(String subject, String name, String address, String phone_number) {
		int result = 0;
		String sql = "INSERT INTO Hospital(id,subject,name, address, phone_number) VALUES((select ifnull(max(cast(id as signed integer)),0)+1 from Hospital as b),?,?,?,?)";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			System.out.println("db에 들어옴");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);

			st.setString(1, subject);
			st.setString(2, name);
			st.setString(3, address);
			st.setString(4, phone_number);
			result = st.executeUpdate();
			System.out.println("result : " + result);
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Hospital get(String id) {
		Hospital h = null;
		String sql = "SELECT * FROM Hospital WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				h = new Hospital();
				h.setSubject(rs.getString("subject"));
				h.setName(rs.getString("name"));
				h.setAddress(rs.getString("address"));
				h.setPhone_number(rs.getString("phone_number"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return h;
	}

	@Override
	public int edit(String id, String subject, String name, String address, String phone_number) {
		int result = 0;
		String sql = "UPDATE Hospital SET subject= ?, name = ?, address=?, phone_number=? WHERE id = ?";
		String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, "soonface", "2014");
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, subject);
			st.setString(2, name);
			st.setString(3, address);
			st.setString(4, phone_number);
			st.setString(5, id);
			result = st.executeUpdate();
			System.out.println("edit result=" + result);
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	   @Override
	   public int getCount(String query, String subject, String addr) {
	      int count = 0;
	      String sqlCount = "SELECT COUNT(id) as count FROM Hospital WHERE name like ? and subject like ? and address like ?";

	      String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection con = DriverManager.getConnection(url, "soonface", "2014");
	         PreparedStatement stCount = con.prepareStatement(sqlCount);
	         
	         
	         stCount.setString(1, "%" + query + "%");
	         stCount.setString(2, "%" + subject + "%");
	         stCount.setString(3, "%" + addr + "%");
	         
	         ResultSet rsCount = stCount.executeQuery();
	         
	         rsCount.next();
	         count = rsCount.getInt("count");
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

	   @Override
	   public List<Hospital> getList(String query, String subject, String addr, int page) {
	      List<Hospital> list = null;
	      String sql = "SELECT * FROM Hospital WHERE name like ? and subject like ? and address like ? order by id ASC limit ?,10";
	      String url = "jdbc:mysql://211.238.142.247/soonfacedb?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";

	      int offset = (page - 1) * 10;
	      try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection con = DriverManager.getConnection(url, "soonface", "2014");
	         PreparedStatement st = con.prepareStatement(sql);
	         st.setString(1, "%" + query + "%");
	         st.setString(2, "%" + subject + "%");
	         st.setString(3, "%" + addr + "%");
	         st.setInt(4, offset);
	         ResultSet rs = st.executeQuery();
	         list = new ArrayList<>();
	         while (rs.next()) {
	            Hospital h = new Hospital();
	            h.setId(rs.getString("id"));
	            h.setSubject(rs.getString("subject"));
	            h.setName(rs.getString("name"));
	            h.setAddress(rs.getString("address"));
	            h.setPhone_number(rs.getString("phone_number"));
	            list.add(h);
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


}
