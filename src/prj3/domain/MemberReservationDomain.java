package prj3.domain;

public class MemberReservationDomain {
	
	private String user_id, reservation_num;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getReservation_num() {
		return reservation_num;
	}

	public void setReservation_num(String reservation_num) {
		this.reservation_num = reservation_num;
	}

	@Override
	public String toString() {
		return "MemberReservationDomain [user_id=" + user_id + ", reservation_num=" + reservation_num + "]";
	}
	
}
