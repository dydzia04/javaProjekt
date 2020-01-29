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

public class showAndSearchDeptController implements Initializable {

	@FXML
	private ComboBox<String> tableComboBox;
	@FXML
	private TextField toFindTextField;
	@FXML
	private Button refreshBtn;

	@FXML
	private TableView<ShowDepartamentData> databaseTable;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowDepartamentData, Integer> ID;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowDepartamentData, String> adress;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowDepartamentData, String> phoneNumber;
	@SuppressWarnings("FieldCanBeLocal")
	@FXML
	private TableColumn<ShowDepartamentData, String> email;
	@FXML
	private TableColumn<ShowDepartamentData, Object> deleteBtn;

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
					List emp = DbOps.findDepartamentByID(id);
					this.createAlertForSearch(emp);
					break;
				}
				case "Adres": {
					List emp = DbOps.findDepartmentByAddress(textToFind);
					this.createAlertForSearch(emp);
					break;
				}
				case "Numer telefonu": {
					List emp = DbOps.findDepartmentByPhoneNumber(textToFind);
					this.createAlertForSearch(emp);
					break;
				}
				case "E-Mail": {
					List emp = DbOps.findDepartmentByEMail(textToFind);
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
			alert.setContentText("Nie znaleziono pracownika o podanych danych.");

			alert.showAndWait();
			return;
		}
		String output = "";

		Iterator iterator = emp.listIterator();
		int depID = 0;
		int index = 0;

		while (iterator.hasNext()) {
			Department department = (Department) iterator.next();
			depID = department.getId();

			output += "ID: " + department.getId() + "\n";
			output += "Adres: " + department.getAddress() + "\n";
			output += "Numer Telefonu: " + department.getPhoneNumber() + "\n";
			output += "E-Mail: " + department.geteMail() + "\n";
		}

		for (Object row : databaseTable.getItems()) {
			ShowDepartamentData d = (ShowDepartamentData) row;
			System.out.println(d.toString());
			if (d.getId() == depID) {
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
		alert.setHeaderText("Znaleziono wydział.");
		alert.setContentText(output);

		alert.showAndWait();
	}

	void initalizeTable() {
		databaseTable.getColumns().clear();
		databaseTable.getItems().clear();

		ID = new TableColumn("ID");
		ID.setCellValueFactory(new PropertyValueFactory<>("id"));

		adress = new TableColumn("Adres");
		adress.setCellValueFactory(new PropertyValueFactory<>("adress"));

		phoneNumber = new TableColumn("Numer telefonu");
		phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

		email = new TableColumn("E-Mail");
		email.setCellValueFactory(new PropertyValueFactory<>("email"));

		deleteBtn = new TableColumn<>("Usuń");
		deleteBtn.setCellValueFactory(new PropertyValueFactory<>("delete"));

//		databaseTable.getColumns().addAll(ID, adress, phoneNumber, email, deleteBtn);
		databaseTable.getColumns().addAll(adress, phoneNumber, email, deleteBtn);
	}

	void updateTableContent() {
		List depts = DbOps.getAllDepartments();

		initalizeTable();

		Iterator iterator = depts.iterator();

		while (iterator.hasNext()) {
			Department d = (Department) iterator.next();
			ShowDepartamentData data = new ShowDepartamentData();

			data.setId(d.getId());
			data.setAdress(d.getAddress());
			data.setPhoneNumber(d.getPhoneNumber());
			data.setEmail(d.geteMail());

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
//		tableComboBox.getItems().setAll("ID", "Adres", "Numer telefonu", "E-Mail");
		tableComboBox.getItems().setAll("Adres", "Numer telefonu", "E-Mail");
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
