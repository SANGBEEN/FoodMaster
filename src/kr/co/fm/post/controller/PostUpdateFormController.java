package kr.co.fm.post.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.PostDao;
import kr.co.fm.vo.PhotosVo;
import kr.co.fm.vo.PostVo;

public class PostUpdateFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PostVo post = new PostVo();
		PostDao dao = new PostDao();
		int no =Integer.parseInt(request.getParameter("no"));
		post = dao.detailPostByNo(no);
		List<PhotosVo> photolist = dao.selectPhotoByNo(no);
		post.setPhotolist(photolist);
		request.setAttribute("post", post);
		
		return "/jsp/post/updateForm.jsp";
	}
	
}
