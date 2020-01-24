package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.DatabaseOperations;

import java.net.URL;
import java.util.ResourceBundle;

public class showAndSearchController implements Initializable {

  @FXML private ComboBox<String> tableComboBox;
  @FXML private TextField toFindTextField;
  @FXML private TableView databaseTable;

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

        System.out.println(DbOps.findEmployeesByID(id));
      }

      else if ( value.equals("Imię") ){
        System.out.println(DbOps.findEmployeesByName(textToFind));
      }

      else if ( value.equals("Nazwisko") ){
        System.out.println(DbOps.findEmployeesBySurName(textToFind));
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
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    tableComboBox.getItems().setAll("ID", "Imię", "Nazwisko");
  }
}
