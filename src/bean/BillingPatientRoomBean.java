package bean;

public class BillingPatientRoomBean {

	private int billNo;
	private int dischargeID;
	private String billingDate;
	private int noOfDays;
	private double roomCharges;
	private double totalRoomCharges;
	private double serviceCharges;
	private double totalCharges;
	private String paymentMode;
	private String paymentModeDetails;
	private double chargesPaid;
	private double dueCharges;

	public BillingPatientRoomBean() {

	}

	public BillingPatientRoomBean(int billNo) {

	}

	public BillingPatientRoomBean(int billNo, int dischargeID, String billingDate, int noOfDays, double roomCharges,
			double totalRoomCharges, double serviceCharges, double totalCharges, String paymentMode,
			String paymentModeDetails, double chargesPaid, double dueCharges) {
		this.billNo = billNo;
		this.dischargeID = dischargeID;
		this.billingDate = billingDate;
		this.noOfDays = noOfDays;
		this.roomCharges = roomCharges;
		this.totalRoomCharges = totalRoomCharges;
		this.serviceCharges = serviceCharges;
		this.totalCharges = totalCharges;
		this.paymentMode = paymentMode;
		this.paymentModeDetails = paymentModeDetails;
		this.chargesPaid = chargesPaid;
		this.dueCharges = dueCharges;
	}

	public int getBillNo() {
		return billNo;
	}

	public void setBillNo(int billNo) {
		this.billNo = billNo;
	}

	public int getDischargeID() {
		return dischargeID;
	}

	public void setDischargeID(int dischargeID) {
		this.dischargeID = dischargeID;
	}

	public String getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public double getRoomCharges() {
		return roomCharges;
	}

	public void setRoomCharges(double roomCharges) {
		this.roomCharges = roomCharges;
	}

	public double getTotalRoomCharges() {
		return totalRoomCharges;
	}

	public void setTotalRoomCharges(double totalRoomCharges) {
		this.totalRoomCharges = totalRoomCharges;
	}

	public double getServiceCharges() {
		return serviceCharges;
	}

	public void setServiceCharges(double serviceCharges) {
		this.serviceCharges = serviceCharges;
	}

	public double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(double totalCharges) {
		this.totalCharges = totalCharges;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentModeDetails() {
		return paymentModeDetails;
	}

	public void setPaymentModeDetails(String paymentModeDetails) {
		this.paymentModeDetails = paymentModeDetails;
	}

	public double getChargesPaid() {
		return chargesPaid;
	}

	public void setChargesPaid(double chargesPaid) {
		this.chargesPaid = chargesPaid;
	}

	public double getDueCharges() {
		return dueCharges;
	}

	public void setDueCharges(double dueCharges) {
		this.dueCharges = dueCharges;
	}
}
