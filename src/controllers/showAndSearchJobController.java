package controllers;

import entity.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.DatabaseOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class showAndSearchJobController implements Initializable {

	@FXML
	private ComboBox<String> tableComboBox;
	@FXML
	private TextField toFindTextField;
	@FXML
	private Button refreshBtn;

	@FXML
	private TableView<ShowJobData> databaseTable;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowJobData, Integer> ID;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowJobData, String> name;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowJobData, Integer> salary;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowJobData, Object> deleteBtn;

	final DatabaseOperations DbOps = new DatabaseOperations();

	@FXML
	void searchForSomeone(ActionEvent event) {
		String value = tableComboBox.getValue();
		String textToFind = toFindTextField.getText();

		if (textToFind.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Błędne wprowadzenie");
			alert.setHeaderText("Puste pole tekstowe");
			alert.setContentText("Proszę wprowadzić odpowiednia wartość.");

			alert.showAndWait();
		}

		if (!toFindTextField.getText().isEmpty()) {

			switch (value) {
				case "ID": {
					int id = Integer.parseInt(textToFind);
					List emp = DbOps.findJobsByID(id);
					this.createAlertForSearch(emp);
					break;
				}
				case "Pozycja": {
					List emp = DbOps.findJobsByPosition(textToFind);
					this.createAlertForSearch(emp);
					break;
				}
				case "Wynagrodzenie": {
					List emp = DbOps.findJobsBySalary(Integer.parseInt(textToFind));
					this.createAlertForSearch(emp);
					break;
				}
				default: {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Błędne wprowadzenie");
					alert.setHeaderText("Puste pole tekstowe");
					alert.setContentText("Proszę wprowadzić odpowiednia wartość.");

					alert.showAndWait();
				}
			}
		}
	}

	void createAlertForSearch(List emp) {
		databaseTable.getSelectionModel().clearSelection();
		if (emp.isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Wynik wyszukiwania");
			alert.setHeaderText("Nie znaleziono.");
			alert.setContentText("Nie znaleziono pozycji o podanych danych.");

			alert.showAndWait();
			return;
		}
		String output = "";

		Iterator iterator = emp.listIterator();
		int jobID = 0;
		int index = 0;

		while (iterator.hasNext()) {
			Job job = (Job) iterator.next();
			jobID = job.getId();

			output += "ID: " + job.getId() + "\n";
			output += "Pozycja: " + job.getName() + "\n";
			output += "Wynagrodzenie: " + job.getSalary() + "\n";
		}

		for (Object row : databaseTable.getItems()) {
			ShowJobData j = (ShowJobData) row;
			System.out.println(j.toString());
			if (j.getId() == jobID) {
				databaseTable.requestFocus();
				databaseTable.getSelectionModel().select(index);
				databaseTable.getFocusModel().focus(index);
				break;
			}
			index++;
		}

		Alert alert = new Alert(Alert.AlertType.INFORMATION);

		alert.setResizable(true);
		alert.getDialogPane().setPrefSize(480, 320);

		alert.setTitle("Wynik wyszukania");
		alert.setHeaderText("Znaleziono pozycję.");
		alert.setContentText(output);

		alert.showAndWait();
	}

	void initalizeTable() {
		databaseTable.getColumns().clear();
		databaseTable.getItems().clear();

		ID = new TableColumn("ID");
		ID.setCellValueFactory(new PropertyValueFactory<>("id"));

		name = new TableColumn("Pozycja");
		name.setCellValueFactory(new PropertyValueFactory<>("jobName"));

		salary = new TableColumn("Wynagrodzenie");
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

		deleteBtn = new TableColumn<>("Usuń");
		deleteBtn.setCellValueFactory(new PropertyValueFactory<>("delete"));

//		databaseTable.getColumns().addAll(ID, name, salary, deleteBtn);
		databaseTable.getColumns().addAll(name, salary, deleteBtn);
	}

	void updateTableContent() {
		List jobs = DbOps.getAllJobs();

		initalizeTable();

		Iterator iterator = jobs.iterator();

		while (iterator.hasNext()) {
			Job j = (Job) iterator.next();
			ShowJobData data = new ShowJobData();

			data.setId(j.getId());
			data.setJobName(j.getName());
			data.setSalary(j.getSalary());

			databaseTable.getItems().add(data);
		}
	}

	@FXML
	void refresh(ActionEvent event) {
		updateTableContent();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		databaseTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		tableComboBox.getItems().setAll("ID", "Pozycja", "Wynagrodzenie");
		tableComboBox.getItems().setAll("Pozycja", "Wynagrodzenie");
		tableComboBox.getSelectionModel().select(0);
		updateTableContent();
		try {
			FileInputStream inputStream = new FileInputStream("src/img/003-refresh.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			refreshBtn.setText("");
			refreshBtn.setGraphic(imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
