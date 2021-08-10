package bean;

public class RoomBean {

	private String roomNo;
	private String roomType;
	private int roomCharges;
	private String roomStatus;
	
	public RoomBean() {
		
	}
	
	public RoomBean(String roomNo) {
		this.roomNo =  roomNo;
	}
	
	public RoomBean(String roomNo,String roomType,int roomCharges,String roomStatus){
		this.roomNo = roomNo;
		this.roomType = roomType;
		this.roomCharges = roomCharges;
		this.roomStatus = roomStatus;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomCharges() {
		return roomCharges;
	}

	public void setRoomCharges(int roomCharges) {
		this.roomCharges = roomCharges;
	}

	public String getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}

}
