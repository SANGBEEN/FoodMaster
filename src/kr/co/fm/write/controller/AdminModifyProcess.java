package kr.co.fm.write.controller;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.WriteDao;
import kr.co.fm.util.BitFileNamePolicy;
import kr.co.fm.vo.RecipesVo;

public class AdminModifyProcess implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		if(request.getParameter("no")!= null){
			int no = Integer.parseInt(request.getParameter("no"));
			//수정버튼을 통해 접근할때
			return "/jsp/write/adminModify.jsp";
		}else{
			String saveFolder = "C:\\Users\\Administrator\\workspace\\FmProject\\WebContent\\upload\\recipes";	
			MultipartRequest multi = new MultipartRequest( request, saveFolder, 1024*1024*10, "utf-8", new BitFileNamePolicy()); 	
			int no = Integer.parseInt(multi.getParameter("no"));
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			content = content.replace("\r\n", "<br/>");
			String video = multi.getParameter("video");
			String userId = multi.getParameter("id");
			String titleImg = "";
			int categoryNo = Integer.parseInt(multi.getParameter("category_no"));
					
			Enumeration files = multi.getFileNames();
			
			String fileName = (String)files.nextElement();	//attachfile
			File file = multi.getFile(fileName);	//경로-파일명
			if(multi.getParameter("preImg") != null){
				titleImg = multi.getParameter("preImg");
			}else{
				titleImg = multi.getFilesystemName(fileName);	//파일명
			}
			
			RecipesVo recipes = new RecipesVo(no,title, content, video, userId, categoryNo, titleImg);
			
			WriteDao dao = new WriteDao();
			dao.recipeUpdate(recipes);
			return "redirect:/FmProject/multiList.do";
		}
		
	}

}
