package controllers;

import entity.Department;
import entity.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.DatabaseOperations;

import java.net.URL;
import java.util.*;

public class addController implements Initializable {

	final DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private ComboBox<?> choosePositionComBox;
	@FXML
	private ComboBox<?> chooseDeptComBox;

	@FXML
	void saveEmployee(ActionEvent event) {
		if (nameText.getText().isEmpty() || surnameText.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się wprowadzić użytkownika.");
			alert.showAndWait();
			return;
		}
		String name = nameText.getText();
		String surname = surnameText.getText();
		int idDpt = chooseDeptComBox.getSelectionModel().getSelectedIndex() + 1;
		int idJob = choosePositionComBox.getSelectionModel().getSelectedIndex() + 1;

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Dodawanie");
		alert.setHeaderText("Czy dodać pracownika:");
		alert.setContentText("Imię: " + name + "\n" +
				"Nazwisko: " + surname + "\n" +
				"Pozycja: " + choosePositionComBox.getSelectionModel().getSelectedItem() + "\n" +
				"Miejsce pracy: " + chooseDeptComBox.getSelectionModel().getSelectedItem());
		ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
		alert.showAndWait().ifPresent(response -> {
			if (response == okButton) {
				DbOps.createEmployee(name, surname, idDpt, idJob);
				nameText.setText("");
				surnameText.setText("");
				choosePositionComBox.getSelectionModel().select(0);
				chooseDeptComBox.getSelectionModel().select(0);
			} else {
				alert.close();
			}
		});


		if (alert.getResult() == ButtonType.YES) {
			System.out.println(alert.getResult());
			DbOps.createEmployee(name, surname, idDpt, idJob);
			nameText.setText("");
			surnameText.setText("");
			choosePositionComBox.getSelectionModel().select(0);
			chooseDeptComBox.getSelectionModel().select(0);
		} else if (alert.getResult() == ButtonType.NO) {
			alert.close();
		} else {
			alert.close();
		}
	}

	void loadPositions() {
		List jobs = DbOps.getAllJobs();
		List toAdd = new ArrayList();
		Iterator jIterator = jobs.iterator();
		while (jIterator.hasNext()) {
			Job j = (Job) jIterator.next();
			toAdd.add(j.getName());
		}
		choosePositionComBox.getItems().setAll(toAdd);
	}

	void loadDepts() {
		List dept = DbOps.getAllDepartaments();
		List toAdd = new ArrayList();
		Iterator dIterator = dept.iterator();
		while (dIterator.hasNext()) {
			Department d = (Department) dIterator.next();
			toAdd.add(d.getAddress());
		}
		chooseDeptComBox.getItems().setAll(toAdd);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadDepts();
		loadPositions();
		chooseDeptComBox.getSelectionModel().select(0);
		choosePositionComBox.getSelectionModel().select(0);
	}
}
