package kr.co.fm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.plaf.synth.SynthSeparatorUI;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.vo.UsersVo;

public class LoginDao {
	public UsersVo loginCheck(String id, String password){
		UsersVo uvo = new UsersVo();
		StringBuilder sql = new StringBuilder();
		sql.append("select no, id, name, type, email ");
		sql.append("  from users ");
		sql.append(" where id = ? and password = ? ");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
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
	
	public UsersVo naverLoginCheck(String email){
		UsersVo uvo = new UsersVo();
		StringBuilder sql = new StringBuilder();
		sql.append("select no, id, name, type, email ");
		sql.append("  from users ");
		sql.append(" where email = ?");

		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setString(1, email);
			
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
