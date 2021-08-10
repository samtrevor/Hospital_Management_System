package bean;

public class ServicesBean {

	private int serviceId;
	private String serviceName;
	private String serviceDate;
	private String patientId;
	private int serviceCharges;

	public ServicesBean() {

	}

	public ServicesBean(int serviceId) {
		this.serviceId = serviceId;
	}

	public ServicesBean(int serviceId, String serviceName, String serviceDate, String patientId, int serviceCharges) {
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.serviceDate = serviceDate;
		this.patientId = patientId;
		this.serviceCharges = serviceCharges;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public int getServiceCharges() {
		return serviceCharges;
	}

	public void setServiceCharges(int serviceCharges) {
		this.serviceCharges = serviceCharges;
	}

}
