package prj3.domain;

public class MainNoticeViewDomain {

	private String n_subject, n_input_date;
	private int n_num;
	
	public String getN_subject() {
		return n_subject;
	}
	public void setN_subject(String n_subject) {
		this.n_subject = n_subject;
	}
	public String getN_input_date() {
		return n_input_date;
	}
	public void setN_input_date(String n_input_date) {
		this.n_input_date = n_input_date;
	}
	public int getN_num() {
		return n_num;
	}
	public void setN_num(int n_num) {
		this.n_num = n_num;
	}
	@Override
	public String toString() {
		return "MainNoticeViewDomain [n_subject=" + n_subject + ", n_input_date=" + n_input_date + ", n_num=" + n_num
				+ "]";
	}
	
}
