package kr.co.fm.post.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.PostDao;
import kr.co.fm.vo.PostVo;

/**
 * 
 * 포스트 상세조회 컨트롤러 
 * @author henry
 *
 */
public class PostDetailController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PostDao dao = new PostDao();
		PostVo post = new PostVo();
		int no = Integer.parseInt(request.getParameter("no"));
		post = dao.detailPostByNo(no);
		
		request.setAttribute("post", post);
		
		return "/jsp/post/detail.jsp";
	}
	
}
