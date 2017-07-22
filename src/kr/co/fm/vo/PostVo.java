package kr.co.fm.vo;

import java.util.List;

public class PostVo {
	private int no;
	private String userId;
	private String content;
	private String regDate;
	private List<PhotosVo> photolist;
	
	
	public PostVo() {
		super();
	}

	

	public PostVo(int no, String userId, String content, String regDate, List<PhotosVo> photolist) {
		super();
		this.no = no;
		this.userId = userId;
		this.content = content;
		this.regDate = regDate;
		this.photolist = photolist;
	}



	public List<PhotosVo> getPhotolist() {
		return photolist;
	}

	public void setPhotolist(List<PhotosVo> photolist) {
		this.photolist = photolist;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}



	@Override
	public String toString() {
		return "{\"no\":\"" + no + "\", \"userId\":\"" + userId + "\", \"content\":\"" + content + "\", \"regDate\":\"" + regDate
				+ "\", \"photolist\":" + photolist + "}";
	}
	
	
	
}
