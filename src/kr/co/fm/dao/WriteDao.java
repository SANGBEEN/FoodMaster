package kr.co.fm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.vo.RecipesVo;

public class WriteDao {

	/**
	 * 레시피 게시글 작성
	 * @param vo
	 */
	public void recipeInsert(RecipesVo vo) {
		
		StringBuilder sql = new StringBuilder(); 
		sql.append("insert into RECIPES (NO, TITLE, TITLE_IMG, CONTENT, VIDEO, USER_ID, CATEGORY_NO ,REG_DATE)");
		sql.append("values(recipes_seq.nextval, ?, ?, ?, ?, ?, ?, sysdate)");
		
		try (
				
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				
		) {
			
			int i = 1;
			pstmt.setString(i++, vo.getTitle());
			pstmt.setString(i++, vo.getTitleImg());
			pstmt.setString(i++, vo.getContent());
			pstmt.setString(i++, vo.getVideo());
			pstmt.setString(i++, vo.getUserId());
			pstmt.setInt(i++, vo.getCategoryNo());
		
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 레시피 게시글 수정
	 * @param vo
	 */
	public RecipesVo recipeUpdate(RecipesVo vo) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("update RECIPES set TITLE=?, TITLE_IMG=?, CONTENT=?, VIDEO=?, USER_ID=?, CATEGORY_NO=? ");
		sql.append(" where NO=?");
		
		try (
			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			
		) {
			
			int i = 1;
			pstmt.setString(i++, vo.getTitle());
			pstmt.setString(i++, vo.getTitleImg());
			pstmt.setString(i++, vo.getContent());
			pstmt.setString(i++, vo.getVideo());
			pstmt.setString(i++, vo.getUserId());
			pstmt.setInt(i++, vo.getCategoryNo());
			pstmt.setInt(i++, vo.getNo());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
		
	}
	
	
	/**
	 * 레시피 게시글 삭제
	 * @param recipeNo
	 */
	public void recipeDelete(int recipeNo){
		
		String sql = "delete from RECIPES where no=?";
		
		try (
				
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
		) {
			
			pstmt.setInt(1, recipeNo);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
