package controllers;

import entity.Department;
import entity.Employee;
import entity.Job;
import entity.ShowEmployeeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.DatabaseOperations;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class showAndSearchController implements Initializable {

  @FXML
  private ComboBox<String> tableComboBox;
  @FXML
  private TextField toFindTextField;

  @FXML
  private TableView<ShowEmployeeData> databaseTable;
  @SuppressWarnings("FieldCanBeLocal")
  @FXML
  private TableColumn<ShowEmployeeData, Integer> ID;
  @SuppressWarnings("FieldCanBeLocal")
  @FXML
  private TableColumn<ShowEmployeeData, String> name;
  @SuppressWarnings("FieldCanBeLocal")
  @FXML
  private TableColumn<ShowEmployeeData, String> surName;
  @SuppressWarnings("FieldCanBeLocal")
  @FXML
  private TableColumn<ShowEmployeeData, String> position;
  @FXML
  private TableColumn<ShowEmployeeData, Object> deleteBtn;
  @FXML
  private TableColumn<ShowEmployeeData, Object> miscData;

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
          List emp = DbOps.findEmployeesByID(id);
          this.createAlertForSearch(emp);
          break;
        }
        case "Imię": {
          List emp = DbOps.findEmployeesByName(textToFind);
          this.createAlertForSearch(emp);
          break;
        }
        case "Nazwisko": {
          List emp = DbOps.findEmployeesBySurName(textToFind);
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
    int empID = 0;
    int index = 0;

    while (iterator.hasNext()) {
      Employee employee = (Employee) iterator.next();
      //noinspection StringConcatenationInLoop
      empID = employee.getId();

      output += "ID: " + employee.getId() + "\n";
      output += "Imię: " + employee.getFirstName() + "\n";
      output += "Nazwisko: " + employee.getSurName() + "\n";
    }

    for (Object row : databaseTable.getItems()) {
      ShowEmployeeData e = (ShowEmployeeData) row;
      System.out.println(e.toString());
      if (e.getId() == empID) {
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
    alert.setHeaderText("Znaleziono pracownika.");
    alert.setContentText(output);

    alert.showAndWait();
  }

  void initalizeTable() {
    databaseTable.getColumns().clear();
    databaseTable.getItems().clear();

    ID = new TableColumn("ID");
    ID.setCellValueFactory(new PropertyValueFactory<>("id"));

    name = new TableColumn("Imię");
    name.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    surName = new TableColumn("Nazwisko");
    surName.setCellValueFactory(new PropertyValueFactory<>("surName"));

    position = new TableColumn("Pozycja");
    position.setCellValueFactory(new PropertyValueFactory<>("jobName"));

    miscData = new TableColumn<>("Więcej");
    miscData.setCellValueFactory(new PropertyValueFactory<>("more"));

    deleteBtn = new TableColumn<>("Usuń");
    deleteBtn.setCellValueFactory(new PropertyValueFactory<>("delete"));

    databaseTable.getColumns().addAll(ID, name, surName, position, deleteBtn, miscData);
    ID.setSortType(TableColumn.SortType.ASCENDING);
    databaseTable.getSortOrder().add(ID);
  }

  void updateTableContent() {
    List employees = DbOps.getAllEmployees();
    List jobs = DbOps.getAllJobs();
    List depts = DbOps.getAllDepartaments();

    initalizeTable();

    Iterator iterator = employees.iterator();

    while (iterator.hasNext()) {
      Employee e = (Employee) iterator.next();
      ShowEmployeeData data = new ShowEmployeeData();

      data.setId(e.getId());
      data.setFirstName(e.getFirstName());
      data.setSurName(e.getSurName());

      Iterator iteratorJob = jobs.iterator();
      while (iteratorJob.hasNext()) {
        Job j = (Job) iteratorJob.next();
        if (e.getIdJob() == j.getId()) {
          data.setJobName(j.getName());
          data.setSalary(j.getSalary());
        }
      }

      Iterator iteratorDept = depts.iterator();
      while (iteratorDept.hasNext()) {
        Department d = (Department) iteratorDept.next();
        if (e.getIdDept() == d.getId()) {
          data.setAddress(d.getAddress());
          data.setPhoneNumber(d.getPhoneNumber());
          data.seteMail(d.geteMail());
        }
      }
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
    tableComboBox.getItems().setAll("ID", "Imię", "Nazwisko");
    tableComboBox.getSelectionModel().select(0);
    updateTableContent();
  }
}
