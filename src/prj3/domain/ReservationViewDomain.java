package prj3.domain;

public class ReservationViewDomain {
	
	private String reservation_num, user_id, name, room_name, pay_status;
	private int year, month, day;
	
	public String getReservation_num() {
		return reservation_num;
	}
	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "ReservationViewDomain [reservation_num=" + reservation_num + ", user_id=" + user_id + ", name=" + name
				+ ", room_name=" + room_name + ", pay_status=" + pay_status + ", year=" + year + ", month=" + month
				+ ", day=" + day + "]";
	}

}
