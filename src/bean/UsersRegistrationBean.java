package bean;

public class UsersRegistrationBean {

	private String name;
	private String userName;
	private String password;
	private String email;
	private String contactNo;

	public UsersRegistrationBean() {

	}

	public UsersRegistrationBean(String name) {
		this.name = name;
	}

	public UsersRegistrationBean(String name, String password) {
		this.name = name;
		this.password = password;
	}

	public UsersRegistrationBean(String userName, String password, String name, String contactNo, String email) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.contactNo = contactNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

}
