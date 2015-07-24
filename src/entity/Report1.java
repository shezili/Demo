package entity;

public class Report1 {
	
	private String number;
	
	private String area;
	
	private String location;
	
	private String description;
	
	private String image1;
	
	private String image2;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	@Override
	public String toString() {
		return "Report1 [number=" + number + ", area=" + area + ", location="
				+ location + ", description=" + description + ", image1="
				+ image1 + ", image2=" + image2 + "]";
	}
	
	
}
