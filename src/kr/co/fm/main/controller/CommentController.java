package kr.co.fm.main.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.CommentDao;
import kr.co.fm.vo.CommentsVo;

public class CommentController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//Set VO
		request.setCharacterEncoding("utf-8");
		String table = "p_comments";
		int no=0;
		if(request.getParameter("rNo") != null){
			table="r_comments";
			no = Integer.valueOf(request.getParameter("rNo"));
		}else if(request.getParameter("postNo") != null) {
			table="p_comments";
			no = Integer.valueOf(request.getParameter("postNo"));
		}
		CommentDao cDao = new CommentDao();
		
		//r_comment
		System.out.println("<><><><><><>"+table);
		System.out.println("?>?>?>?>?>?>?>"+no);
		if(request.getParameter("comment")!=null){
			if(table.equals("r_comments")){
				int r_no = Integer.valueOf(request.getParameter("rNo"));
				String user_id = request.getParameter("userId");
				String content = request.getParameter("content");
				int parentNo = Integer.valueOf(request.getParameter("parentNo"));
				CommentsVo commVo = new CommentsVo();
				commVo.setrNo(r_no);
				commVo.setUserId(user_id);
				commVo.setContent(content);
				commVo.setParentNo(parentNo);
				
				System.out.println("test2 : "+commVo.toString());
				//insertComment
				int result = cDao.insertComment(commVo,table);
				System.out.println("result: "+result);
			}else{
				int p_no = Integer.valueOf(request.getParameter("postNo"));
				String user_id = request.getParameter("userId");
				String content = request.getParameter("content");
				int parentNo = Integer.valueOf(request.getParameter("parentNo"));
				CommentsVo commVo = new CommentsVo();
				commVo.setPostNo(p_no);
				commVo.setUserId(user_id);
				commVo.setContent(content);
				commVo.setParentNo(parentNo);
				
				System.out.println("test2 : "+commVo.toString());
				//insertComment
				int result = cDao.insertComment(commVo,table);
				System.out.println("result: "+result);
				
			}
		}
		//출력
		List<CommentsVo> list = cDao.selectList(no,table);
		StringBuilder sb = new StringBuilder();
		
		//System.out.println(" "+cDao.selectList(r_no).toString());
		//System.out.println("list size: "+list.size());
		if(list.size()!=0){
			request.setAttribute("commList", cDao.selectList(no,table));
			for(int i =0; i<list.size();i++ ){
				CommentsVo vo = list.get(i);
				sb.append(vo.toString());
				if(i!=(list.size()-1))
					sb.append(", ");
			}
			//sb.append(sb.toString());
			System.out.println(sb.toString());
			request.setAttribute("list",sb.toString());
		}
		return "/jsp/main/comment.jsp";
	}
}
