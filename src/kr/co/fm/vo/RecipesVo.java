package kr.co.fm.vo;

import java.io.Serializable;

public class RecipesVo implements Serializable{
	/*레시피, 레시피에 대한 카테고리 가지고 온다*/
	//recipes table
	private int no;
	private String title;
	private String titleImg;
	private String content;
	private String video;
	private String userId;
	private int categoryNo;
	private int viewCnt;
	private int likeCnt;
	private String regDate;
	
	//categories table
	private String categoryName;

	
	public RecipesVo() {
		super();
	}

	public RecipesVo(int no, String title, String titleImg, String content, String video, String userId, int categoryNo, int viewCnt,
			int likeCnt, String regDate) {
		super();
		this.no = no;
		this.title = title;
		this.titleImg = titleImg;
		this.content = content;
		this.video = video;
		this.userId = userId;
		this.categoryNo = categoryNo;
		this.viewCnt = viewCnt;
		this.likeCnt = likeCnt;
		this.regDate = regDate;
	}

	public RecipesVo(int no, String title, String titleImg, String content, String video, String userId, int viewCnt, int likeCnt,
			String regDate, String categoryName) {
		super();
		this.no = no;
		this.title = title;
		this.titleImg = titleImg;
		this.content = content;
		this.video = video;
		this.userId = userId;
		this.viewCnt = viewCnt;
		this.likeCnt = likeCnt;
		this.regDate = regDate;
		this.categoryName = categoryName;
	}

	public RecipesVo(String title, String content, String video, String userId, int categoryNo, String titleImg) {
		super();
		this.title = title;
		this.content = content;
		this.video = video;
		this.userId = userId;
		this.categoryNo = categoryNo;
		this.titleImg = titleImg;
	}
	public RecipesVo(int no, String title, String content, String video, String userId, int categoryNo, String titleImg) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.video = video;
		this.userId = userId;
		this.categoryNo = categoryNo;
		this.titleImg = titleImg;
	}
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RecipesVo [no=");
		builder.append(no);
		builder.append(", title=");
		builder.append(title);
		builder.append(", titleImg=");
		builder.append(titleImg);
		builder.append(", content=");
		builder.append(content);
		builder.append(", video=");
		builder.append(video);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", categoryNo=");
		builder.append(categoryNo);
		builder.append(", viewCnt=");
		builder.append(viewCnt);
		builder.append(", likeCnt=");
		builder.append(likeCnt);
		builder.append(", regDate=");
		builder.append(regDate);
		builder.append(", categoryName=");
		builder.append(categoryName);
		builder.append("]");
		return builder.toString();
	}	
	
	

	
	
}
