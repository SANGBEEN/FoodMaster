package kr.co.fm.post.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;

import kr.co.fm.core.controller.Controller;
import kr.co.fm.dao.PostDao;
import kr.co.fm.util.BitFileNamePolicy;
import kr.co.fm.vo.PhotosVo;
import kr.co.fm.vo.PostVo;
import kr.co.fm.vo.UsersVo;

/**
 * 포스트 작성 컨트롤러 
 * @author henry
 *
 */
public class PostWriteController implements Controller{
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");

		BitFileNamePolicy p = new BitFileNamePolicy();
		PostDao dao = new PostDao();
		PostVo post = new PostVo();
		List<PhotosVo> photolist = new ArrayList<>();
		//
		
		List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			
			InputStream fileContent = filePart.getInputStream();
			File file = new File("C:\\Users\\Administrator\\workspace\\FmProject\\WebContent\\upload\\posts\\+fileName");
			//File file = new File("C:\\web-workspace\\FmProject\\WebContent\\upload\\posts\\"+fileName);
			file = p.rename(file);
			FileOutputStream out = new FileOutputStream(file);
			int read = 0;
			byte[] bytes =new byte[1024*5];
			while((read=fileContent.read(bytes))!=-1){
				out.write(bytes, 0, read);
			}
			out.close();
			fileContent.close();
			
			System.out.println(fileName);
			PhotosVo photo = new PhotosVo();
			photo.setUrl(file.getName());
			System.out.println("업로드 로그");
			System.out.println(file.getName());
			photolist.add(photo);
		}
		
		String content = request.getParameter("content");
		UsersVo userVO = (UsersVo)request.getSession().getAttribute("userVO");
		String userId = userVO.getId();
		System.out.println(userVO);
		
		int no = dao.selectNo();
		post.setNo(no);
		post.setUserId(userId);
		post.setContent(content);
		post.setPhotolist(photolist);
		System.out.println(post);
		dao.insertPost(post);
		dao.insertPhoto(post);
		
		return "/jsp/post/write.jsp";
	}
	

}
