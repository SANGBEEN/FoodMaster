package kr.co.fm.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.fm.util.ConnectionFactory;
import kr.co.fm.util.JDBCClose;
import kr.co.fm.vo.PhotosVo;
import kr.co.fm.vo.PostVo;

public class PostDao {

	public PostDao() {
		super();
	}
	/**
	 * 포스트 번호 조회 
	 * @return
	 */
	public int selectNo() {
		
		String sql = "select posts_seq.nextval from dual";
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	/**
	 * 개인 포스트 리스트 조회 
	 * @param no
	 * @return List<PostVo>
	 */
	public List<PostVo> selectPostById(String user_id){
		
		StringBuilder sql = new StringBuilder();
		List<PostVo> list = new ArrayList<>();
		list = null;
		sql.append(" select posts.no, posts.user_id, posts.content, posts.reg_date, photos.no, photos.url, photos.reg_date ");
		sql.append(" from posts join photos on posts.no=photos.post_no ");
		sql.append(" where user_id = ? order by 4 desc ");
		
		ResultSet rs = null;
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				) 
		{	
			stmt.setString(1, user_id);
			System.out.println("query......");
			rs = stmt.executeQuery();
			List<PhotosVo> photolist = new ArrayList<>();
			int i=0;
			while(rs.next()){
				PostVo post = new PostVo();
				PhotosVo photo = new PhotosVo();
				post.setNo(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setContent(rs.getString(3));
				post.setRegDate(rs.getString(4));
				
				if(list==null){
					list= new ArrayList<>();
					photolist = new ArrayList<>();
					photo.setNo(rs.getInt(5));
					photo.setRegDate(rs.getString(7));
					photo.setPostNo(rs.getInt(1));
					photo.setUrl(rs.getString(6));
					
					photolist.add(photo);
					post.setPhotolist(photolist);
					list.add(post);
					
				}else if(list.get(i).getNo()!=post.getNo()){
					photolist = new ArrayList<>();
					photo.setNo(rs.getInt(5));
					photo.setRegDate(rs.getString(7));
					photo.setPostNo(rs.getInt(1));
					photo.setUrl(rs.getString(6));
					
					photolist.add(photo);
					post.setPhotolist(photolist);
					list.add(post);
					i++;
				}else{
					photo.setNo(rs.getInt(5));
					photo.setRegDate(rs.getString(7));
					photo.setPostNo(rs.getInt(1));
					photo.setUrl(rs.getString(6));
					
					list.get(i).getPhotolist().add(photo);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 모든 포스트 조회 
	 * @return List<PostVo>
	 */
	public List<PostVo> selectAllPost(){
		
		StringBuilder sql = new StringBuilder();
		List<PostVo> list = new ArrayList<>();
		list = null;
		sql.append(" select posts.no , posts.user_id, posts.content, posts.reg_date, photos.no, photos.url, photos.reg_date ");
		sql.append(" from posts join photos on posts.no=photos.post_no order by 4 desc ");
		ResultSet rs = null;
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				) 
		{
			rs = stmt.executeQuery();
			List<PhotosVo> photolist = new ArrayList<>();
			int i = 0;
			while(rs.next()){
				PostVo post = new PostVo();
				PhotosVo photo = new PhotosVo();
				post.setNo(rs.getInt(1));
				post.setUserId(rs.getString(2));
				post.setContent(rs.getString(3));
				post.setRegDate(rs.getString(4));
				
				if(list==null){
					list= new ArrayList<>();
					photolist = new ArrayList<>();
					photo.setNo(rs.getInt(5));
					photo.setRegDate(rs.getString(7));
					photo.setPostNo(rs.getInt(1));
					photo.setUrl(rs.getString(6));
					
					photolist.add(photo);
					post.setPhotolist(photolist);
					list.add(post);
				}else if(list.get(i).getNo()!=post.getNo()){
					photolist = new ArrayList<>();
					photo.setNo(rs.getInt(5));
					photo.setRegDate(rs.getString(7));
					photo.setPostNo(rs.getInt(1));
					photo.setUrl(rs.getString(6));
					
					photolist.add(photo);
					post.setPhotolist(photolist);
					list.add(post);
					i++;
				}else{
					photo.setNo(rs.getInt(5));
					photo.setRegDate(rs.getString(7));
					photo.setPostNo(rs.getInt(1));
					photo.setUrl(rs.getString(6));
					
					list.get(i).getPhotolist().add(photo);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 포스트 상세 조회 
	 * @param no
	 * @return PostVo
	 */
	public PostVo detailPostByNo(int no){
		
		StringBuilder sql = new StringBuilder();
		PostVo post = null;
		boolean flag =  true;
		//sql.append(" select * from posts where no=? ");
		sql.append(" select posts.no, posts.user_id, posts.content, posts.reg_date, photos.no, photos.url, photos.reg_date ");
		sql.append(" from posts join photos on posts.no=photos.post_no ");
		sql.append(" where posts.no=?");
		ResultSet rs = null;
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				) 
		{
			stmt.setInt(1, no);
			rs = stmt.executeQuery();
			List<PhotosVo> photolist = new ArrayList<>();
			PhotosVo photo = new PhotosVo();
			photolist = new ArrayList<>();
			
			
			while(rs.next()){
				if(post==null){
					post= new PostVo();
					post.setNo(rs.getInt(1));
					post.setUserId(rs.getString(2));
					post.setContent(rs.getString(3));
					post.setRegDate(rs.getString(4));
					
				}
				
				photo.setNo(rs.getInt(5));
				photo.setRegDate(rs.getString(7));
				photo.setPostNo(rs.getInt(1));
				photo.setUrl(rs.getString(6));
				photolist.add(photo);
				
	
					
			}
			post.setPhotolist(photolist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(post);
		return post;
	}
	
	/**
	 * 포스트 생성 
	 * @param post
	 */
	public void insertPost(PostVo post){
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = new ConnectionFactory().getConnection();
			//conn.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			
			//포스트 생성
			sql.append(" insert into posts values(?,?,?,sysdate)");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, post.getNo());
			stmt.setString(2, post.getUserId());
			stmt.setString(3, post.getContent());
			stmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCClose.close(conn, stmt);
		}
		
	}
	
	/**
	 * 사진 업로드 
	 * @param photolist
	 */
	public void insertPhoto(PostVo post){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = new ConnectionFactory().getConnection();
			for(PhotosVo photo : post.getPhotolist()){
				System.out.println("log----------");
				System.out.println(photo.getUrl());
			}
			for(PhotosVo photo : post.getPhotolist()){
				StringBuilder sql = new StringBuilder();
				sql.append(" insert into photos values(photos_seq.nextval,?,?,sysdate) ");
				stmt = conn.prepareStatement(sql.toString());
				stmt.setInt(1, post.getNo());
				stmt.setString(2, photo.getUrl());
				System.out.println("==============");
				
				System.out.println(post.getNo());
				System.out.println(photo.getUrl());
				stmt.executeUpdate();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCClose.close(conn, stmt);
		}
	}
	
	/**
	 * 포스트 사진  삭제
	 * @param post
	 */
	public void deletePhotoByPostNo(int no){
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from photos where post_no=? ");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql.toString());
				)
		{
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 포스트 수정 
	 * @param post
	 */
	public void updatePost(PostVo post){
		
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement stmt = null;
		try {
			StringBuilder sql = new StringBuilder();
			System.out.println(post);
			sql.append(" update posts set content = ? where no = ? ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, post.getContent());
			stmt.setInt(2, post.getNo());
			stmt.executeUpdate();
//			
//			sql = null;
//			stmt = null;
//			List<PhotosVo> photolist = new ArrayList<>();
//			photolist = post.getPhotolist();
//			sql.append(" update photos set url = ? where post_no=?");
//			stmt = conn.prepareStatement(sql.toString());
//			for(PhotosVo photo : photolist){
//				stmt.setString(1, post.getPhotolist().get(0).getUrl());
//				stmt.setInt(2, post.getNo());
//				stmt.executeUpdate();
//			}
//			conn.commit();
		}catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			JDBCClose.close(conn, stmt);
		}
	}
	
	/**
	 * 사진 수정 
	 * @param post
	 */
	public void updatePhoto(PostVo post){
		Connection conn = new ConnectionFactory().getConnection();
		PreparedStatement stmt = null;
		try {
			//rownum subquery
			StringBuilder sql = new StringBuilder();
			List<PhotosVo> photolist = new ArrayList<>();
			int pCount = post.getPhotolist().size();
			photolist = post.getPhotolist();
			
			//sql.append(" count")
			
			
			sql.append(" update photos set url = ? where post_no=?");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, post.getPhotolist().get(0).getUrl());
			stmt.setInt(2, post.getNo());
			stmt.executeUpdate();

		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCClose.close(conn, stmt);
		}
	}
	
	/**
	 * 포스트 삭제 
	 * @param no
	 */
	public void deletePostByNo(int no){
		StringBuilder sql = new StringBuilder();
		sql.append("delete from posts where no =?");
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql.toString());
				) 
		{
			stmt.setInt(1, no);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 사진 리스트
	 * @param postNo
	 * @return List<PhotosVo>
	 */
	public List<PhotosVo> selectPhotoByNo(int postNo) {
		List<PhotosVo> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from photos where post_no = ?");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setInt(1, postNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhotosVo photo = new PhotosVo();
				photo.setNo(rs.getInt("no"));
				photo.setPostNo(rs.getInt("post_no"));
				photo.setUrl(rs.getString("url"));
				photo.setRegDate(rs.getString("reg_date"));
				
				list.add(photo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}
}
