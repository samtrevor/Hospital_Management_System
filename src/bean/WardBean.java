package bean;

public class WardBean {
	
	private String wardName;
	private String wardType;
	private int noOfBeds;
	private int charges;

	public WardBean() {
	
	}
	
	public WardBean(String wardName){
		this.wardName = wardName;
	}
	
	public WardBean(String wardName,String wardType,int noOfBeds,int charges){
		this.wardName = wardName;
		this.wardType = wardType;
		this.noOfBeds = noOfBeds;
		this.charges = charges;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getWardType() {
		return wardType;
	}

	public void setWardType(String wardType) {
		this.wardType = wardType;
	}

	public int getNoOfBeds() {
		return noOfBeds;
	}

	public void setNoOfBeds(int noOfBeds) {
		this.noOfBeds = noOfBeds;
	}

	public int getCharges() {
		return charges;
	}

	public void setCharges(int charges) {
		this.charges = charges;
	}

}
