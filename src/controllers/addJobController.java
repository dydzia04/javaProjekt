package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import main.DatabaseOperations;

public class addJobController {

	@FXML
	private TextField positionText;
	@FXML
	private TextField salaryText;

	DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	void saveJob(ActionEvent event) {
		if (positionText.getText().isEmpty() || salaryText.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się wprowadzić pozycji.");
			alert.showAndWait();
			return;
		}
		String name = positionText.getText();
		String salary = salaryText.getText();
		int salaryInt;

		try {
			salaryInt = Integer.parseInt(salary);
		} catch (NumberFormatException nfe) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się wprowadzić pozycji.");
			alert.showAndWait();

			nfe.printStackTrace();

			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Dodawanie");
		alert.setHeaderText("Czy dodać pozycję:");
		alert.setContentText("Nazwa: " + name + "\nSalary: " + salaryInt);
		ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
		alert.showAndWait().ifPresent(response -> {
			if (response == okButton) {
				DbOps.createJob(name, salaryInt);
				positionText.setText("");
				salaryText.setText("");

			} else {
				alert.close();
			}
		});
	}

}
