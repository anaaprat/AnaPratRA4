package models;

public class AI {

	private Integer code;
	private String name;
	private String type;
	private Integer yearAp;
	private String image;

	public AI() {
		super();
	}

	public AI(int code, String name, String type, Integer yearAp, String image) {
		super();
		this.code = code;
		this.name = name;
		this.type = type;
		this.yearAp = yearAp;
		this.image = image;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getYearAp() {
		return yearAp;
	}

	public void setYearAp(Integer yearAp) {
		this.yearAp = yearAp;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String toString() {
		return "IA [code=" + code + ", name=" + name + ", type=" + type + ", yearAp=" + yearAp + ", image=" + image
				+ "]";
	}
}
