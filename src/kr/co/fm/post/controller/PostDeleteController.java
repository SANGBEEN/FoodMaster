package kr.co.fm.post.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.PostDao;
import kr.co.fm.vo.PhotosVo;

public class PostDeleteController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int no = Integer.parseInt(request.getParameter("no"));
		PostDao dao = new PostDao();
		List<PhotosVo> photolist = new ArrayList<>();
		photolist = dao.selectPhotoByNo(no);
		
		dao.deletePostByNo(no);
		for(PhotosVo photo :  photolist){
			String path = request.getSession().getServletContext().getRealPath("/upload/posts/"+photo.getUrl());
			File f = new File(path);
			System.out.println(f);
			if(f.exists())f.delete();
		}
		
		return "/jsp/post/delete.jsp";
		


	}
	
}
