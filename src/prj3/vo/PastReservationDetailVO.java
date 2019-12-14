package prj3.vo;

public class PastReservationDetailVO {
	
	private String r_realtime, reservation_num;

	public String getR_realtime() {
		return r_realtime;
	}

	public void setR_realtime(String r_realtime) {
		this.r_realtime = r_realtime;
	}

	public String getReservation_num() {
		return reservation_num;
	}

	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}

	@Override
	public String toString() {
		return "PastReservationDetailVO [r_realtime=" + r_realtime + ", reservation_num=" + reservation_num + "]";
	}
	
}
