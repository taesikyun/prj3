package prj3.domain;

public class MainReservationViewDomain {

	private String reservation_num, name, room_name, r_realtime;

	public String getReservation_num() {
		return reservation_num;
	}

	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
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

	public String getR_realtime() {
		return r_realtime;
	}

	public void setR_realtime(String r_realtime) {
		this.r_realtime = r_realtime;
	}

	@Override
	public String toString() {
		return "MainReservationViewDomain [reservation_num=" + reservation_num + ", name=" + name + ", room_name="
				+ room_name + ", r_realtime=" + r_realtime + "]";
	}
	
}
