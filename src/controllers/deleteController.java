package controllers;

import entity.Department;
import entity.Employee;
import entity.Job;
import entity.ShowEmployeeData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.DatabaseOperations;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class deleteController implements Initializable { //TODO: change fxml to more suitable, maybe add deletion to main window as another button

  final DatabaseOperations DbOps = new DatabaseOperations();

  @FXML
  private TableView<ShowEmployeeData> usersTable;
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
  private TableColumn<ShowEmployeeData, Integer> miscData;
  @FXML
  private Button deleteBtn;

  @FXML
  void deleteEmployee(ActionEvent event) {
  } //TODO: logic to deleting

  void deleteEmployeeAlert() {
  }//TODO: open dialog window asking for confirmation to delete with credentials that was highlighted

  void initalizeTable() {
    usersTable.getColumns().clear();
    usersTable.getItems().clear();

    ID = new TableColumn("ID");
    ID.setCellValueFactory(new PropertyValueFactory<>("id"));

    name = new TableColumn("Imię");
    name.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    surName = new TableColumn("Nazwisko");
    surName.setCellValueFactory(new PropertyValueFactory<>("surName"));

    position = new TableColumn("Pozycja");
    position.setCellValueFactory(new PropertyValueFactory<>("jobName"));

    miscData = new TableColumn<>("Usuń");
    miscData.setCellValueFactory(new PropertyValueFactory<>("more"));

    usersTable.getColumns().addAll(ID, name, surName, position, miscData);
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
      usersTable.getItems().add(data);
    }

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    updateTableContent();
  }
}
