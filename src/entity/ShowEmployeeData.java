package entity;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.DatabaseOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowEmployeeData {
	private int id;
	private String firstName;
	private String surName;
	private String jobName;
	private int salary;
	private String address;
	private String phoneNumber;
	private String eMail;
	private Button delete;// TODO: center, make it icon
	private Button more; // TODO: center, make it icon

	public ShowEmployeeData(Button delete, Button more, int id, String firstName, String surName, String jobName, int salary, String address, String phoneNumber, String eMail) {
		this.delete = delete;
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

	public ShowEmployeeData() {
		this.delete = new Button("Usuń");
		try {
			FileInputStream inputStream = new FileInputStream("src/img/002-trash.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			this.delete = new Button("", imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		this.delete.setOnAction(event -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Usuwanie");
			alert.setContentText("Czy usunąć " + this.firstName + " " + this.surName);
			ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
			ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
			ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
			alert.showAndWait().ifPresent(type -> {
				if (type == okButton) {
					DatabaseOperations db = new DatabaseOperations();
					db.deleteEmployee(this.id);
				} else {
					alert.close();
				}
			});
		});

		this.more = new Button("Więcej");
		try {
			FileInputStream inputStream = new FileInputStream("src/img/001-more.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			this.more = new Button("", imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.more.setOnAction(event -> {
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

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
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
