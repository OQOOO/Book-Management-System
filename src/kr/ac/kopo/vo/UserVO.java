package kr.ac.kopo.vo;

public class UserVO {

	private String id;
	private String password;
	private String name;
	private int birthDate;
	private String email;
	private String phoneNum;
	private int adminRight;
	
	public UserVO() {
		
	}

	public UserVO(String id, String password, String name, int birthDate, String email, String phoneNum) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.phoneNum = phoneNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(int birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
	public int getAdminRight() {
		return adminRight;
	}

	public void setAdminRight(int adminRight) {
		this.adminRight = adminRight;
	}

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", password=" + password + ", name=" + name + ", birthDate=" + birthDate
				+ ", email=" + email + ", phoneNum=" + phoneNum + ", adminRight=" + adminRight + "]";
	}

	
	
	
	
}
