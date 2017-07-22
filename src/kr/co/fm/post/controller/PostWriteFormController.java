package kr.co.fm.post.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;

/**
 * 포스트 작성 폼 컨트롤러 
 * @author henry
 *
 */
public class PostWriteFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "/jsp/post/writeForm.jsp";
	}
	

}
