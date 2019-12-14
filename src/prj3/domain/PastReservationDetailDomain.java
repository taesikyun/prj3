package prj3.domain;

public class PastReservationDetailDomain {

	private String reservation_num, room_name, user_id, name, phone, email, r_realtime,
			reservation_date, pay_status, pay_date, pay_method, require;
	private int  year, month, day, charge;
	
	public String getReservation_num() {
		return reservation_num;
	}
	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getR_realtime() {
		return r_realtime;
	}
	public void setR_realtime(String r_realtime) {
		this.r_realtime = r_realtime;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	public String getPay_status() {
		return pay_status;
	}
	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}
	public String getPay_date() {
		return pay_date;
	}
	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
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
	public int getCharge() {
		return charge;
	}
	public void setCharge(int charge) {
		this.charge = charge;
	}
	@Override
	public String toString() {
		return "PastReservationDetailDomain [reservation_num=" + reservation_num + ", room_name=" + room_name
				+ ", user_id=" + user_id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", r_realtime="
				+ r_realtime + ", reservation_date=" + reservation_date + ", pay_status=" + pay_status + ", pay_date="
				+ pay_date + ", pay_method=" + pay_method + ", require=" + require + ", year=" + year + ", month="
				+ month + ", day=" + day + ", charge=" + charge + "]";
	}
	
}
