package kr.co.fm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.util.JDBCClose;
import kr.co.fm.vo.RecipesVo;

public class MainDao {
	public static final String RECIPES_LIST = "recipesList";

	/**
	 * 전체 게시물 목록 조회하는 기능
	 */
	public List<RecipesVo> selectAllList() {

		List<RecipesVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = "select * from recipes";

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				int no = rs.getInt(++i);
				System.out.println(no);
				String title = rs.getString(++i);
				String content = rs.getString(++i);
				String video = rs.getString(++i);
				String userId = rs.getString(++i);
				int categoryNo = rs.getInt(++i);
				int viewCnt = rs.getInt(++i);
				int likeCnt = rs.getInt(++i);
				String regDate = rs.getString(++i);
				String titleImg = rs.getString(++i);
				list.add(new RecipesVo(no, title, titleImg, content, video, userId, categoryNo, viewCnt, likeCnt,
						regDate));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return list;
	}

	/**
	 * 상세 아이템을 조회하는 기능
	 */

	/* 게시물 조회 */
	public RecipesVo selectDetailObject(int sNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		RecipesVo recipesVo = null;
		try {
			conn = new ConnectionFactory().getConnection();
			
			String sql = "update recipes set view_cnt = view_cnt+1 where no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sNo);
			pstmt.executeUpdate();
			
			sql = "select * from recipes where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int i = 0;
			int no = rs.getInt(++i);
			System.out.println(no);
			String title = rs.getString(++i);
			String content = rs.getString(++i);
			String video = rs.getString(++i);
			String userId = rs.getString(++i);
			int categoryNO = rs.getInt(++i);
			int viewCnt = rs.getInt(++i);
			int likeCnt = rs.getInt(++i);
			String regDate = rs.getString(++i);
			String titleImg = rs.getString(++i);
			recipesVo = new RecipesVo(no, title, titleImg, content, video, userId, categoryNO, viewCnt, likeCnt,
					regDate);

		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return recipesVo;
	}

	// 메뉴에 따른 조회
	public List<RecipesVo> selectMenuList(int menuNum) {

		List<RecipesVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = "select * from recipes where category_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, menuNum);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				int no = rs.getInt(++i);
				System.out.println(no);
				String title = rs.getString(++i);
				String content = rs.getString(++i);
				String video = rs.getString(++i);
				String userId = rs.getString(++i);
				int categoryNo = rs.getInt(++i);
				int viewCnt = rs.getInt(++i);
				int likeCnt = rs.getInt(++i);
				String regDate = rs.getString(++i);
				String titleImg = rs.getString(++i);
				list.add(new RecipesVo(no, title, titleImg, content, video, userId, categoryNo, viewCnt, likeCnt,
						regDate));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return list;
	}

	// 검색에 따른 조회
	public List<RecipesVo> selectSearchList(String text) {

		List<RecipesVo> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = "select * from recipes where content like ? or title like ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + text + "%");
			pstmt.setString(2, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int i = 0;
				int no = rs.getInt(++i);
				System.out.println(no);
				String title = rs.getString(++i);
				String content = rs.getString(++i);
				String video = rs.getString(++i);
				String userId = rs.getString(++i);
				int categoryNo = rs.getInt(++i);
				int viewCnt = rs.getInt(++i);
				int likeCnt = rs.getInt(++i);
				String regDate = rs.getString(++i);
				String titleImg = rs.getString(++i);
				list.add(new RecipesVo(no, title, titleImg, content, video, userId, categoryNo, viewCnt, likeCnt,
						regDate));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return list;
	}
	public int countList(int category){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count =0;
		try {
			conn = new ConnectionFactory().getConnection();
			String sql = "select count(*) from recipes";
			if(category != 0){
				sql += " where category_no=" + category;
			}

			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
				System.out.println(count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}

		return count;
	}
}
