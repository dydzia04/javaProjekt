package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import main.DatabaseOperations;

public class addDeptController {

	@FXML
	private TextField adressText;
	@FXML
	private TextField phoneText;
	@FXML
	private TextField emailText;

	final DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	void saveDept(ActionEvent event) {
		if (adressText.getText().isEmpty() || phoneText.getText().isEmpty() || emailText.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się wprowadzić wydziału.");
			alert.showAndWait();
			return;
		}

		String adress = adressText.getText();
		String phone = phoneText.getText();
		String email = emailText.getText();

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Dodawanie");
		alert.setHeaderText("Czy dodać wydział:");
		alert.setContentText("Adres: " + adress + "\nNumer Telefonu: " + phone + "\nE-Mail: " + email);
		ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
		alert.showAndWait().ifPresent(response -> {
			if (response == okButton) {
				DbOps.createDepartment(adress, phone, email);
				adressText.setText("");
				phoneText.setText("");
				emailText.setText("");
			} else {
				alert.close();
			}
		});

	}

}
