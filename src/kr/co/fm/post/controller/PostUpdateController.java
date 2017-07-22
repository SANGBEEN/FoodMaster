package kr.co.fm.post.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

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

public class PostUpdateController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");

		PostVo post = new PostVo();
		PostDao dao = new PostDao();
		BitFileNamePolicy p = new BitFileNamePolicy();
		List<PhotosVo> photolist = new ArrayList<>();
		List<PhotosVo> oriList =new ArrayList<>();

		List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());
		for (Part filePart : fileParts) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
			if(fileName.equals(""))
				break;
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
		
		System.out.println("-----");
		for(PhotosVo p2 : photolist)
			System.out.println(p2);
		
		int no = Integer.parseInt(request.getParameter("no"));
		String content = request.getParameter("content");
		UsersVo userVO = (UsersVo)request.getSession().getAttribute("userVO");
		String userId = userVO.getId();
		
		post.setNo(no);
		post.setUserId(userId);
		post.setContent(content);
		
		if(photolist.isEmpty()){
			System.out.println("파일안올림");
			dao.updatePost(post);
		}else{
			System.out.println("파일올림");
			dao.deletePhotoByPostNo(no);
			oriList = dao.selectPhotoByNo(no);
			for(PhotosVo photo :  oriList){
				System.out.println("파일삭제");
				String path = request.getSession().getServletContext().getRealPath("/upload/posts/"+photo.getUrl());
				File f = new File(path);
				System.out.println(f);
				if(f.exists())f.delete();
			}
			post.setPhotolist(photolist);
			dao.updatePost(post);
			dao.insertPhoto(post);
		}
//		System.out.println("filename : "+ Paths.get(fileParts.get(0).getSubmittedFileName()).getFileName().toString());
//		if( Paths.get(fileParts.get(0).getSubmittedFileName()).getFileName().toString().equals("")){
//			List<PhotosVo> list = dao.selectPhotoByNo(no);
//			for(PhotosVo p1 : list){
//				PhotosVo photo = new PhotosVo();
//				//photo.setUrl(list.get(0).getUrl());
//				photo.setUrl(p1.getUrl());
//				photolist.add(photo);
//				
//			}
			//
//			System.out.println("업데이트 파일안올림");
//			post.setPhotolist(photolist);
//			dao.updatePost(post);
//			//
//		}else{
//			System.out.println("파일올렷을때");
//			post.setPhotolist(photolist);
//			dao.updatePost(post);
//			dao.deletePhotoByPostNo(no);
//			dao.insertPhoto(post);
//		}

//		
//		post.setPhotolist(photolist);
//		System.out.println("포스트");
//		System.out.println(post);
//		//delete dao.deletePhotoByPostNo()
//		dao.updatePost(post);
//		dao.updatePhoto(post);// update -> insert
		
		return "/jsp/post/update.jsp";
	}
	
}
