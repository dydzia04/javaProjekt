package controllers;

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

  @FXML private ComboBox<String> tableComboBox;
  @FXML private TextField toFindTextField;

  @FXML private TableView databaseTable;
  @FXML private TableColumn ID;
  @FXML private TableColumn name;
  @FXML private TableColumn surName;
  @FXML private TableColumn position;
  @FXML private TableColumn miscData; // TODO: add button showing more information

  DatabaseOperations DbOps = new DatabaseOperations();

  @FXML
  void searchForSomeone(ActionEvent event) {
    //TODO: zabezpieczenie
    String value = tableComboBox.getValue();
    String textToFind = toFindTextField.getText();
    System.out.println(value);
    System.out.println(textToFind);

    if ( value == null ){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Błędne wprowadzenie");
      alert.setHeaderText("Niepoprawna opcja");
      alert.setContentText("Proszę wybrać odpowiednią wartość z listy rozwijanej.");

      alert.showAndWait();
    }

    if( !toFindTextField.getText().isEmpty() ){

      if ( value.equals("ID") ){

        int id = Integer.parseInt(textToFind);
        List emp = DbOps.findEmployeesByID(id);
        String output = null;

        Iterator iterator = emp.listIterator();
        while (iterator.hasNext()){
          Employee employee = (Employee) iterator.next();
          output += employee.toString()+"\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik wyszukania");
        alert.setHeaderText("Znaleziono pracownika.");
        alert.setContentText(output);

        alert.showAndWait();
      }

      else if ( value.equals("Imię") ){
        List emp = DbOps.findEmployeesByName(textToFind);
        String output = null;

        Iterator iterator = emp.listIterator();

        while (iterator.hasNext()){
          Employee employee = (Employee) iterator.next();
          output += employee.toString()+"\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik wyszukania");
        alert.setHeaderText("Znaleziono pracownika.");
        alert.setContentText(output);

        alert.showAndWait();
      }

      else if ( value.equals("Nazwisko") ){
        List emp = DbOps.findEmployeesBySurName(textToFind);
        String output = null;

        Iterator iterator = emp.listIterator();
        while (iterator.hasNext()){
          Employee employee = (Employee) iterator.next();
          output += employee.toString()+"\n";
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wynik wyszukania");
        alert.setHeaderText("Znaleziono pracownika.");
        alert.setContentText(output);

        alert.showAndWait();
      }

    }
    else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Błędne wprowadzenie");
      alert.setHeaderText("Puste pole tekstowe");
      alert.setContentText("Proszę wprowadzić odpowiednia wartość.");

      alert.showAndWait();
    }
  }

  void updateTableContent(){
    //TODO: pobranie z DbOps danych, dodanie ich do tabeli
    List employees = DbOps.getAllEmployees();
    List jobs = DbOps.getAllJobs();

    System.out.println(employees);
    System.out.println(jobs);

    databaseTable.getColumns().clear();

    ID = new TableColumn("ID");
    ID.setCellValueFactory(new PropertyValueFactory<>("id"));

    name = new TableColumn("Imię");
    name.setCellValueFactory(new PropertyValueFactory<>("firstName"));

    surName = new TableColumn("Nazwisko");
    surName.setCellValueFactory(new PropertyValueFactory<>("surName"));

    position = new TableColumn("Pozycja");
    position.setCellValueFactory(new PropertyValueFactory<>("name"));

    //TODO: add button inside column for more info

    databaseTable.getColumns().addAll(ID, name, surName, position);

    Iterator iterator = employees.iterator();

    while ( iterator.hasNext() ){
      ShowEmployeeData data = new ShowEmployeeData();
      Employee e = (Employee) iterator.next();

      data.setId( e.getId() );
      data.setFirstName( e.getFirstName() );
      data.setSurName( e.getSurName() );
      Iterator iteratorJob = jobs.iterator();

      while ( iteratorJob.hasNext() ){
        Job j = (Job) iteratorJob.next();
        if ( e.getIdJob() == j.getId() ) data.setName(j.getName());
      }

      databaseTable.getItems().add(data);
      //TODO: add to columns
    }

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    tableComboBox.getItems().setAll("ID", "Imię", "Nazwisko");
    updateTableContent();
  }
}
