package bean;

public class Nurse_WardBoy_Bean {

	private String id;
	private String name;
	private String category;
	private String address;
	private String contactNo;
	private String email;
	private String qualifications;
	private String bloodGroup;
	private String dateOfJoining;

	public Nurse_WardBoy_Bean() {

	}

	public Nurse_WardBoy_Bean(String id) {
		this.id = id;
	}

	public Nurse_WardBoy_Bean(String id, String name, String category, String address, String contactNo, String email,
			String qualifications, String bloodGroup, String dateOfJoining) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.qualifications = qualifications;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
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
