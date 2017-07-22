package kr.co.fm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.util.JDBCClose;
import kr.co.fm.vo.CommentsVo; 

public class CommentDao {
	public int insertComment(CommentsVo comm, String table) {
		String sql="";
		int result = 0;
		
		if(table.equals("r_comments")){
			sql = "insert into r_comments values(r_comments_seq.nextval, ?, ?, ?, sysdate, ?)";

			try (Connection conn = new ConnectionFactory().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);) {
				int i = 0;
				pstmt.setInt(++i, comm.getrNo());
				pstmt.setString(++i, comm.getUserId());
				pstmt.setString(++i, comm.getContent());
				pstmt.setInt(++i, comm.getParentNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			sql = "insert into p_comments values(p_comments_seq.nextval, ?, ?, ?, ?, sysdate)";
			
			try (Connection conn = new ConnectionFactory().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);) {
				int i = 0;
				pstmt.setInt(++i, comm.getPostNo());
				pstmt.setString(++i, comm.getUserId());
				pstmt.setString(++i, comm.getContent());
				pstmt.setInt(++i, comm.getParentNo());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("입력완료: "+result);
		return result;
	}
	
	public void commentDelete(int no, String table){
		String sql = "";
		if(table.equals("r_comments")){
			sql = "delete from r_comments where no=?";
		}else{
			sql = "delete from p_comments where no=?";
		}
		
		try (
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
		) {
			
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<CommentsVo> selectList(int tableNo, String table) {
		
		List<CommentsVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = "";
			if(table.equals("r_comments")){
				sql = "select * from r_comments where r_no=? order by reg_date";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, tableNo);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					int no = rs.getInt(++i);
					int rNo = rs.getInt(++i);
					String userId = rs.getString(++i);
					String content = rs.getString(++i);
					String regDate = rs.getString(++i);
					int parentNo = rs.getInt(++i);
					
					CommentsVo vo = new CommentsVo();
					vo.setNo(no);
					vo.setrNo(rNo);
					vo.setUserId(userId);
					vo.setContent(content);
					vo.setRegDate(regDate);
					vo.setParentNo(parentNo);
					
					System.out.println(vo.toString());
					list.add(vo);
				}
			}else{
				sql = "select * from p_comments where post_no=? order by reg_date";
					
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, tableNo);
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int i = 0;
					int no = rs.getInt(++i);
					int pNo = rs.getInt(++i);
					String userId = rs.getString(++i);
					String content = rs.getString(++i);
					int parentNo = rs.getInt(++i);
					String regDate = rs.getString(++i);
					
					CommentsVo vo = new CommentsVo();
					vo.setNo(no);
					vo.setPostNo(pNo);
					vo.setUserId(userId);
					vo.setContent(content);
					vo.setRegDate(regDate);
					vo.setParentNo(parentNo);
					
					System.out.println(vo.toString());
					list.add(vo);
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return list;
	}
}
