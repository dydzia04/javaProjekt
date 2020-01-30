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

public class ShowDepartamentData {
	private int id;
	private String adress;
	private String phoneNumber;
	private String email;
	private Button delete;

	public ShowDepartamentData(int id, String adress, String phoneNumber, String email, Button delete) {
		this.id = id;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.delete = delete;
	}

	public ShowDepartamentData() {
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
			alert.setContentText("Czy usunąć " + this.adress);
			ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
			ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
			ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
			alert.showAndWait().ifPresent(type -> {
				if (type == okButton) {
					DatabaseOperations db = new DatabaseOperations();
					db.deleteDepartment(this.id);
				} else {
					alert.close();
				}
			});
		});
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

}
