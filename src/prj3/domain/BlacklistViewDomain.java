package prj3.domain;

public class BlacklistViewDomain {
	
	private String user_id, user_name, warning_type, input_date, warning_content;

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

	public String getWarning_type() {
		return warning_type;
	}

	public void setWarning_type(String warning_type) {
		this.warning_type = warning_type;
	}

	public String getInput_date() {
		return input_date;
	}

	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}

	public String getWarning_content() {
		return warning_content;
	}

	public void setWarning_content(String warning_content) {
		this.warning_content = warning_content;
	}

	@Override
	public String toString() {
		return "BlacklistViewDomain [user_id=" + user_id + ", user_name=" + user_name + ", warning_type=" + warning_type
				+ ", input_date=" + input_date + ", warning_content=" + warning_content + "]";
	}
	
}
