package prj3.domain;

public class MainQnaViewDomain {

	private String q_subject, user_id, q_input_date, q_answer_flag;
	private int q_num;
	
	public String getQ_subject() {
		return q_subject;
	}
	public void setQ_subject(String q_subject) {
		this.q_subject = q_subject;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getQ_input_date() {
		return q_input_date;
	}
	public void setQ_input_date(String q_input_date) {
		this.q_input_date = q_input_date;
	}
	public String getQ_answer_flag() {
		return q_answer_flag;
	}
	public void setQ_answer_flag(String q_answer_flag) {
		this.q_answer_flag = q_answer_flag;
	}
	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	@Override
	public String toString() {
		return "MainQnaViewDomain [q_subject=" + q_subject + ", user_id=" + user_id + ", q_input_date=" + q_input_date
				+ ", q_answer_flag=" + q_answer_flag + ", q_num=" + q_num + "]";
	}
	
}