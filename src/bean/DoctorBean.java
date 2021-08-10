package bean;

public class DoctorBean {

	private String id;
	private String name;
	private String fatherName;
	private String address;
	private String contactNo;
	private String email;
	private String qualification;
	private String specialization;
	private String gender;
	private String bloodGroup;
	private String dateOfJoining;

	public DoctorBean() {

	}

	public DoctorBean(String id) {
		this.id = id;
	}

	public DoctorBean(String id, String name, String fatherName, String address, String contactNo, String email,
			String qualification, String specialization, String gender, String bloodGroup, String dateOfJoining) {
		this.id = id;
		this.name = name;
		this.fatherName = fatherName;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.qualification = qualification;
		this.specialization = specialization;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.dateOfJoining = dateOfJoining;
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

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

}
