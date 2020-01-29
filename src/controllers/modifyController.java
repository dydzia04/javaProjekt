package controllers;

import entity.Department;
import entity.Employee;
import entity.Job;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import main.DatabaseOperations;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class modifyController implements Initializable {

	final DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	private TextField nameText;
	@FXML
	private TextField surnameText;
	@FXML
	private ChoiceBox<?> posChBox;
	@FXML
	private ChoiceBox<?> deptChBox;
	@FXML
	private ChoiceBox<?> employeeList;
	private int employeeID;

	@FXML
	void readEmployeeData(ActionEvent event) {
		String[] name = employeeList.getSelectionModel().getSelectedItem().toString().split(" ");
		name[0] = name[0].replaceAll(":", "");
		employeeID = Integer.parseInt(name[0]);
		List found = DbOps.findEmployeesByID(employeeID);
		Employee e = (Employee) found.get(0);

		nameText.setText(e.getFirstName());
		surnameText.setText(e.getSurName());
		posChBox.getSelectionModel().select(e.getIdJob() - 1);
		deptChBox.getSelectionModel().select(e.getIdDept() - 1);


	}

	@FXML
	void saveEmployee(ActionEvent event) {
		if (nameText.getText().isEmpty() || surnameText.getText().isEmpty()) {
			createError();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modyfikacja");
		alert.setHeaderText("Czy chcesz zmienić dane użytkownika na: ");
		alert.setContentText("Imię: " + nameText.getText() + "\nNazwisko: " + surnameText.getText() + "\nPozycja: " + posChBox.getSelectionModel().getSelectedItem() + "\nMiejsce pracy: " + deptChBox.getSelectionModel().getSelectedItem());
		ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) {
				DbOps.updateEmployeeFirstName(employeeID, nameText.getText());
				DbOps.updateEmployeeSurName(employeeID, surnameText.getText());
				DbOps.updateEmployeeIdDept(employeeID, deptChBox.getSelectionModel().getSelectedIndex() + 1);
				DbOps.updateEmployeeIdJob(employeeID, posChBox.getSelectionModel().getSelectedIndex() + 1);
			} else {
				alert.close();
			}
		});
		reload();
	}

	void createError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Błąd");
		alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się zmodyfikować użytkownika. \nProszę uzupełnić pola tekstowe.");
		alert.showAndWait();
		return;
	}

	void reload() {
		setWorkplace();
		setPosition();
		writeEmployeesToChoiceBox();
	}

	void setPosition() {
		List Jobs = DbOps.getAllJobs();
		List toAdd = new ArrayList();
		Iterator iteratorJ = Jobs.iterator();

		while (iteratorJ.hasNext()) {
			Job j = (Job) iteratorJ.next();
			toAdd.add(j.getName());
		}
		posChBox.getItems().addAll(toAdd);
	}

	void setWorkplace() {
		List departaments = DbOps.getAllDepartaments();
		List toAdd = new ArrayList();
		Iterator iteratorD = departaments.iterator();

		while (iteratorD.hasNext()) {
			Department d = (Department) iteratorD.next();
			toAdd.add(d.getAddress());
		}
		deptChBox.getItems().addAll(toAdd);
	}

	void writeEmployeesToChoiceBox() {
		employeeList.getItems().clear();
		List employees = DbOps.getAllEmployees();
		List toAdd = new ArrayList();
		Iterator iteratorE = employees.iterator();

		while (iteratorE.hasNext()) {
			Employee e = (Employee) iteratorE.next();
			toAdd.add(e.getId() + ": " + e.getFirstName() + " " + e.getSurName());
		}
		employeeList.getItems().addAll(toAdd);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setPosition();
		setWorkplace();
		writeEmployeesToChoiceBox();
		employeeList.getSelectionModel().select(0);
	}
}
