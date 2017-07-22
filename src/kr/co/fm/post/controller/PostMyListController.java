package kr.co.fm.post.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.PostDao;
import kr.co.fm.vo.PostVo;
import kr.co.fm.vo.UsersVo;

/**
 * 내가 올린 포스트 조회 컨트롤
 * @author henry
 *
 */
public class PostMyListController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PostDao dao = new PostDao();
		List<PostVo> list = new ArrayList<>();
		
		HttpSession session = request.getSession();
		UsersVo userVO = (UsersVo)session.getAttribute("userVO");
		String id = userVO.getId();
		list = dao.selectPostById(id);
		request.setAttribute("list", list);
		
		return "/jsp/post/myList.jsp";
	}
	

}
