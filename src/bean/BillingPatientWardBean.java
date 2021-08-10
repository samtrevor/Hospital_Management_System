package bean;

public class BillingPatientWardBean {

	private int billNo;
	private int dischargeID;
	private String billingDate;
	private double bedCharges;
	private int noOfDays;
	private double totalBedCharges;
	private double serviceCharges;
	private double totalCharges;
	private String paymentMode;
	private String paymentModeDetails;
	private double chargesPaid;
	private double dueCharges;

	public BillingPatientWardBean() {

	}

	public BillingPatientWardBean(int billNo) {
		this.billNo = billNo;
	}

	public BillingPatientWardBean(int billNo, int dischargeID, String billingDate, double bedCharges, int noOfDays,
			double totalBedCharges, double serviceCharges, double totalCharges, String paymentMode,
			String paymentModeDetails, double chargesPaid, double dueCharges) {
		this.billNo = billNo;
		this.dischargeID = dischargeID;
		this.billingDate = billingDate;
		this.bedCharges = bedCharges;
		this.noOfDays = noOfDays;
		this.totalBedCharges = totalBedCharges;
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

	public double getBedCharges() {
		return bedCharges;
	}

	public void setBedCharges(double bedCharges) {
		this.bedCharges = bedCharges;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public double getTotalBedCharges() {
		return totalBedCharges;
	}

	public void setTotalBedCharges(double totalBedCharges) {
		this.totalBedCharges = totalBedCharges;
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
