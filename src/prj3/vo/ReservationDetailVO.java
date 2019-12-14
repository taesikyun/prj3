package prj3.vo;

public class ReservationDetailVO {
	
	private String reservation_num, r_realtime;


	public String getReservation_num() {
		return reservation_num;
	}


	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}


	public String getR_realtime() {
		return r_realtime;
	}


	public void setR_realtime(String r_realtime) {
		this.r_realtime = r_realtime;
	}


	@Override
	public String toString() {
		return "ReservationDetailVO [reservation_num=" + reservation_num + ", r_realtime=" + r_realtime + "]";
	}
	
}
