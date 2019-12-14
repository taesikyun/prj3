package prj3.domain;

public class WarningTypeDomain {
	
	private String warning_type, warning_code;

	public String getWarning_type() {
		return warning_type;
	}

	public void setWarning_type(String warning_type) {
		this.warning_type = warning_type;
	}

	public String getWarning_code() {
		return warning_code;
	}

	public void setWarning_code(String warning_code) {
		this.warning_code = warning_code;
	}

	@Override
	public String toString() {
		return "WarningTypeDomain [warning_type=" + warning_type + ", warning_code=" + warning_code + "]";
	}

}
