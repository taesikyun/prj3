package prj3.vo;

public class RoomConceptVO {
	
	private String concept_name, concept_name2, brief_info, info, image;

	public String getConcept_name() {
		return concept_name;
	}

	public void setConcept_name(String concept_name) {
		this.concept_name = concept_name;
	}

	public String getConcept_name2() {
		return concept_name2;
	}

	public void setConcept_name2(String concept_name2) {
		this.concept_name2 = concept_name2;
	}

	public String getBrief_info() {
		return brief_info;
	}

	public void setBrief_info(String brief_info) {
		this.brief_info = brief_info;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "RoomConceptUpdateVO [concept_name=" + concept_name + ", concept_name2=" + concept_name2
				+ ", brief_info=" + brief_info + ", info=" + info + ", image=" + image + "]";
	}

}
