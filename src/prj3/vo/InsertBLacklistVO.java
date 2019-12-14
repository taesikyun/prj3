package prj3.vo;

public class InsertBLacklistVO {
	
	private String user_id, warning_content, warning_type;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getWarning_content() {
		return warning_content;
	}

	public void setWarning_content(String warning_content) {
		this.warning_content = warning_content;
	}

	public String getWarning_type() {
		return warning_type;
	}

	public void setWarning_type(String warning_type) {
		this.warning_type = warning_type;
	}

	@Override
	public String toString() {
		return "InsertBLacklistVO [user_id=" + user_id + ", warning_content=" + warning_content + ", warning_type="
				+ warning_type + "]";
	}
	
}
