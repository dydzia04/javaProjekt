package entity;

public class ShowEmployeeData {
	private int id;
	private String firstName;
	private String surName;
	private String name;

	public ShowEmployeeData(int id, String firstName, String surName, String name) {
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
		this.name = name;
	}

	public ShowEmployeeData(){}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getSurName() { return surName; }
	public void setSurName(String surName) { this.surName = surName; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	@Override
	public String toString() {
		return "ShowEmployeeData{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", surName='" + surName + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
