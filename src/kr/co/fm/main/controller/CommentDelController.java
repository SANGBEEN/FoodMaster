package kr.co.fm.main.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.CommentDao;
import kr.co.fm.dao.WriteDao;
import kr.co.fm.vo.CommentsVo;
import kr.co.fm.vo.RecipesVo;

public class CommentDelController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Set VO
		request.setCharacterEncoding("utf-8");
		int no = Integer.parseInt(request.getParameter("no"));
		int tableNo;
		CommentDao cDao = new CommentDao();
		String table="";
		
		// r_comment
		if (request.getParameter("rNo") != null) {
			tableNo = Integer.valueOf(request.getParameter("rNo"));
			table="r_comments";
			cDao.commentDelete(no,table );
		}else{
			tableNo = Integer.valueOf(request.getParameter("postNo"));
			table="p_comments";
			cDao.commentDelete(no, table);
		}
		//출력
		request.setAttribute("commList", cDao.selectList(tableNo,table));
		//System.out.println(" "+cDao.selectList(r_no).toString());
		StringBuilder sb = new StringBuilder();
		List<CommentsVo> list = cDao.selectList(tableNo,table);
		//System.out.println("list size: "+list.size());
		for(int i =0; i<list.size();i++ ){
			CommentsVo vo = list.get(i);
			sb.append(vo.toString());
			if(i!=(list.size()-1))
				sb.append(", ");
		}
		//sb.append(sb.toString());
		System.out.println(sb.toString());
		request.setAttribute("list",sb.toString());
		return "/jsp/main/comment.jsp";
	}
}
