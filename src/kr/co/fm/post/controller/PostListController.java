package kr.co.fm.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.PostDao;
import kr.co.fm.vo.PostVo;

/**
 * 포스트 전체 조회 컨트롤러 
 * @author henry
 *
 */
public class PostListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		PostDao dao = new PostDao();
		List<PostVo> list = new ArrayList<>();
		
		list = dao.selectAllPost();
		request.setAttribute("list", list);
		return "/jsp/post/list.jsp";
		
		
	}
	
}
