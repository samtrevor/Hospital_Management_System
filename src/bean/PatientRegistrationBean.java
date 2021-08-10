package bean;

public class PatientRegistrationBean {

	private String patientId;
	private String name;
	private String fathersName;
	private String address;
	private String contactNo;
	private String email;
	private int age;
	private String gender;
	private String bloodGroup;
	private String remarks;

	public PatientRegistrationBean(){
		
	}
	
	public PatientRegistrationBean(String patientId) {
		this.patientId = patientId;
	}

	public PatientRegistrationBean(String patientId, String name, String fathersName, String address, String contactNo,
			String email, int age, String gender, String bloodGroup, String remarks) {
		this.patientId = patientId;
		this.name = name;
		this.fathersName = fathersName;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.age = age;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.remarks = remarks;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
