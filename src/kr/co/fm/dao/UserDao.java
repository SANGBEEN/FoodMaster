package kr.co.fm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.vo.UsersVo;

public class UserDao {
	public void insertUser(UsersVo uvo){
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into users(no, id, name, type, password, email, reg_date) ");
		sql.append(" values(users_seq.nextval,?,?,?,?,?,sysdate)");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, uvo.getId());
			pstmt.setString(2, uvo.getName());
			System.out.println(uvo.getName());
			pstmt.setString(3, "U");
			pstmt.setString(4, uvo.getPassword());
			pstmt.setString(5, uvo.getEmail());
			pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public UsersVo userCheck(String id){
		UsersVo uvo = new UsersVo();
		StringBuilder sql = new StringBuilder();
		sql.append("select id");
		sql.append("  from users ");
		sql.append(" where id = ?");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				uvo.setId(rs.getString("id"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return uvo;
	}
	
	public UsersVo selectUserDetail(int no){
		UsersVo uvo = new UsersVo();
		StringBuilder sql = new StringBuilder();
		sql.append("select *");
		sql.append(" from posts  ");
		sql.append(" where no = ?");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setInt(1, no);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				uvo.setNo(rs.getInt("no"));
				uvo.setId(rs.getString("id"));
				uvo.setName(rs.getString("name"));
				uvo.setType(rs.getString("type"));
				uvo.setEmail(rs.getString("email"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return uvo;
	}
}
