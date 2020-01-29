package controllers;

import entity.Department;
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

public class modifyDeptController implements Initializable {

	@FXML
	private TextField adressText;
	@FXML
	private TextField phoneText;
	@FXML
	private ChoiceBox<?> deptList;
	@FXML
	private TextField emailText;

	private int DeptID;

	DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	void redDeptData(ActionEvent event) {
		String[] name = deptList.getSelectionModel().getSelectedItem().toString().split(" ");
		name[0] = name[0].replaceAll(":", "");
		DeptID = Integer.parseInt(name[0]);
		List found = DbOps.findDepartamentByID(DeptID);
		Department d = (Department) found.get(0);

		adressText.setText(d.getAddress());
		phoneText.setText(d.getPhoneNumber());
		emailText.setText(d.geteMail());
		writeDeptsToComboBox();
	}

	@FXML
	void saveDept(ActionEvent event) {
		if (adressText.getText().isEmpty() || phoneText.getText().isEmpty() || emailText.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się zmodyfikować użytkownika. \nProszę uzupełnić pola tekstowe.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modyfikacja");
		alert.setHeaderText("Czy chcesz zmienić dane wydziału na: ");
		alert.setContentText("Adres: " + adressText.getText() + "\nNumer telefonu: " + phoneText.getText() + "\nE-Mail: " + emailText.getText());
		ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) {
				DbOps.updateDepartmentAddress(DeptID, adressText.getText());
				DbOps.updateDepartmentEMail(DeptID, emailText.getText());
				DbOps.updateDepartmentPhoneNumber(DeptID, phoneText.getText());

				adressText.setText("");
				emailText.setText("");
				phoneText.setText("");
			} else {
				alert.close();
			}
		});
		writeDeptsToComboBox();
	}

	void writeDeptsToComboBox() {
		deptList.getItems().clear();
		List dept = DbOps.getAllDepartments();
		List toAdd = new ArrayList();
		Iterator iteratorD = dept.iterator();

		while (iteratorD.hasNext()) {
			Department d = (Department) iteratorD.next();
			toAdd.add(d.getId() + " " + d.getAddress());
		}

		deptList.getItems().addAll(toAdd);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		writeDeptsToComboBox();
		deptList.getSelectionModel().select(0);
	}
}
