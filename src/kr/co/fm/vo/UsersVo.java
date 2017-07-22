package kr.co.fm.vo;

import java.io.Serializable;

public class UsersVo implements Serializable{
	private int no;
	private String id;
	private String name;
	private String type;
	private String password;
	private String email;
	private String regDate;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "UsersVo [no=" + no + ", id=" + id + ", name=" + name + ", type=" + type + ", password=" + password
				+ ", email=" + email + ", regDate=" + regDate + "]";
	}
	
	
	
}
