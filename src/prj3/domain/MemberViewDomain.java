package prj3.domain;

public class MemberViewDomain {
	
	private String user_id, user_name, phone, warning_content;
	private int cnt;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWarning_content() {
		return warning_content;
	}
	public void setWarning_content(String warning_content) {
		this.warning_content = warning_content;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "MemberViewDomain [user_id=" + user_id + ", user_name=" + user_name + ", phone=" + phone
				+ ", warning_content=" + warning_content + ", cnt=" + cnt + "]";
	}
	
}
