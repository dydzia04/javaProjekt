package controllers;

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

public class modifyJobController implements Initializable {

	@FXML
	private TextField positionText;
	@FXML
	private TextField salaryText;
	@FXML
	private ChoiceBox<?> jobList;

	int JobID;

	DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	void readJobData(ActionEvent event) {
		String[] name = jobList.getSelectionModel().getSelectedItem().toString().split(" ");
		name[0] = name[0].replaceAll(":", "");
		JobID = Integer.parseInt(name[0]);
		List found = DbOps.findJobsByID(JobID);
		Job j = (Job) found.get(0);

		positionText.setText(j.getName());
		salaryText.setText(String.valueOf(j.getSalary()));
		writeJobsToComboBox();
	}

	@FXML
	void saveJob(ActionEvent event) {
		if (positionText.getText().isEmpty() || salaryText.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błąd");
			alert.setContentText("Niepoprawne wprowadzenie! \nNie udało się zmodyfikować użytkownika. \nProszę uzupełnić pola tekstowe.");
			alert.showAndWait();
			return;
		}

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Modyfikacja");
		alert.setHeaderText("Czy chcesz zmienić dane pozycji na: ");
		alert.setContentText("Nazwa: " + positionText.getText() + "\nWynagrodzenie: " + salaryText.getText());
		ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
		ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
		ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
		alert.showAndWait().ifPresent(type -> {
			if (type == okButton) {
				DbOps.updateJobName(JobID, positionText.getText());
				DbOps.updateJobSalary(JobID, Integer.parseInt(salaryText.getText()));

				positionText.setText("");
				salaryText.setText("");
			} else {
				alert.close();
			}
		});
		writeJobsToComboBox();
	}

	void writeJobsToComboBox() {
		jobList.getItems().clear();
		List jobs = DbOps.getAllJobs();
		List toAdd = new ArrayList();
		Iterator iteratorJ = jobs.iterator();

		while (iteratorJ.hasNext()) {
			Job j = (Job) iteratorJ.next();
			toAdd.add(j.getId() + " " + j.getName());
		}
		jobList.getItems().addAll(toAdd);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		writeJobsToComboBox();
		jobList.getSelectionModel().select(0);
	}
}
