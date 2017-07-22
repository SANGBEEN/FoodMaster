package kr.co.fm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.util.JDBCClose;
import kr.co.fm.vo.LikeVo;

public class LikeDao {

	public LikeDao() {
		super();

	}

	public List<LikeVo> selectInitLikeList(String id) {

		List<LikeVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = "select user_id, r_no, is_like, reg_date from r_like where user_id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				String userId = rs.getString(++i);
				int rNo = rs.getInt(++i);
				String isLike = rs.getString(++i);
				String regDate = rs.getString(++i);
				System.out.println(rNo + ": " + userId);
				list.add(new LikeVo(userId, rNo, isLike, regDate));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return list;
	}

	public int insertLike(String userId, int iNum, int isLike) {
		String sql = "insert into r_like values(?,?,?,sysdate)";
		int x = -1;
		try (
				
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
		) {
			
			int i = 0;
			pstmt.setString(++i, userId);
			pstmt.setInt(++i, iNum);
			if(isLike==1){
				pstmt.setString(++i, "F");
			}else{
				pstmt.setString(++i, "T");
			}
			
			x = pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return x;
		
		
		
	}
	public int updateLike(String userId, int iNum, int isLike){
		String sql = "update r_like set is_like=? where user_id = ? and r_no=?";
		int x = -1;
		try (
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
					
			) {
				
				int i = 0;
				
				if(isLike==1){
					pstmt.setString(++i, "F");
				}else{
					pstmt.setString(++i, "T");
				}
				pstmt.setString(++i, userId);
				pstmt.setInt(++i, iNum);
				x = pstmt.executeUpdate();
						
			} catch (Exception e) {
				e.printStackTrace();
			}
		return x;
			
	}
	public int updateLike(int iNum, int isLike){
		String sql = "select like_cnt from recipes where no=?";
		String sql2 = "update recipes set like_cnt =? where no = ?";
		int x = -1;
		ResultSet rs = null;
		PreparedStatement pstmt= null;
		Connection conn = null;
		
		try  {
				conn = new ConnectionFactory().getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, iNum);
				int i = 0;
				rs = pstmt.executeQuery();
				rs.next();
				int likeCnt = rs.getInt(1);
				System.out.println("likeCnt: "+likeCnt);
				
				pstmt = null;
				System.out.println("like update");
				pstmt = conn.prepareStatement(sql2);
				
				
				if(isLike==1){
					likeCnt = likeCnt-1;
					System.out.println("like 1 column");
					pstmt.setInt(1, likeCnt);
				}else{
					likeCnt = likeCnt+1;
					System.out.println("like 0 column");
					pstmt.setInt(1, likeCnt);
				}
				
				pstmt.setInt(2, iNum);
				x = pstmt.executeUpdate();
						
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				JDBCClose.close(conn, pstmt);
			}
		return x;
			
	}
	
	

	public boolean isLike(String userId, int rNo, int tag) {

		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		boolean flag = true;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append(" select user_id, r_no, is_like, reg_date ");
			sql.append(" from r_like where user_id=? and r_no= ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, userId);
			stmt.setInt(2, rNo);
			rs = stmt.executeQuery();
			if (rs.next()) {
				sql = new StringBuilder();
				stmt = null;
				System.out.println("like update");
				sql.append(" update r_like set is_like=? where user_id = ? and r_no=? ");
				stmt = conn.prepareStatement(sql.toString());
				if (rs.getString(3).equals("T")) {
					stmt.setString(1, "F");
					flag = false;
				} else {
					stmt.setString(1, "T");
				}
				stmt.setString(2, userId);
				stmt.setInt(3, rNo);
				stmt.executeUpdate();
			} else {
				sql = new StringBuilder();
				stmt = null;
				System.out.println("like insert");
				sql.append(" insert into r_like values(?,?,?,sysdate)");
				stmt = conn.prepareStatement(sql.toString());
				stmt.setString(1, userId);
				stmt.setInt(2, rNo);
				stmt.setString(3, "T");
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, stmt);
		}
		return false;
	}

}
