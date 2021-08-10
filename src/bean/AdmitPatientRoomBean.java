package bean;

public class AdmitPatientRoomBean {

	private int admitId;
	private String patientId;
	private String patientName;
	private String gender;
	private String bloodGroup;
	private String disease;
	private String admitDate;
	private String roomNo;
	private String doctorId;
	private String doctorName;
	private String remarks;

	public AdmitPatientRoomBean() {

	}

	public AdmitPatientRoomBean(String patientId) {
		this.patientId = patientId;
	}

	public AdmitPatientRoomBean(int admitId) {
		this.admitId = admitId;
	}

	public AdmitPatientRoomBean(int admitId, String patientId, String disease, String admitDate, String roomNo,
			String doctorId, String remarks) {
		this.admitId = admitId;
		this.patientId = patientId;
		this.disease = disease;
		this.admitDate = admitDate;
		this.roomNo = roomNo;
		this.doctorId = doctorId;
		this.remarks = remarks;
	}

	public int getAdmitId() {
		return admitId;
	}

	public void setAdmitId(int admitId) {
		this.admitId = admitId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
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

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getAdmitDate() {
		return admitDate;
	}

	public void setAdmitDate(String admitDate) {
		this.admitDate = admitDate;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
