package entity;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class DeleteEmployeeData {
	private Button more; // TODO: wyśrodkować, zmienić ikonę na jakąś ładną
	private int id;
	private String firstName;
	private String surName;
	private String jobName;
	private int salary;
	private String address;
	private String phoneNumber;
	private String eMail;

	public DeleteEmployeeData(Button more, int id, String firstName, String surName, String jobName, int salary, String address, String phoneNumber, String eMail) {
		this.more = more;
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
		this.jobName = jobName;
		this.salary = salary;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail;
	}

	public DeleteEmployeeData() {
		this.more = new Button("Więcej");
		this.more.setOnAction(event -> { //TODO: add delete functionality, make alert a confirmation
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("ID: " + this.id);
			alert.setHeaderText(this.firstName + " " + this.surName);
			alert.setContentText("Pozycja: " + this.jobName + "\n" +
					"Zarobki: " + this.salary + "\n" +
					"Adres: " + this.address + "\n" +
					"Numer Tel: " + this.phoneNumber + "\n" +
					"E-Mail: " + this.eMail);

			alert.showAndWait();
		});
	}

	//public ShowEmployeeData(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Button getMore() {
		return more;
	}

	public void setMore(Button more) {
		this.more = more;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String toString() {
		return "ShowEmployeeData{" +
				"more=" + more +
				", id=" + id +
				", firstName='" + firstName + '\'' +
				", surName='" + surName + '\'' +
				", jobName='" + jobName + '\'' +
				", salary='" + salary + '\'' +
				", address='" + address + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", eMail='" + eMail + '\'' +
				'}';
	}
}
