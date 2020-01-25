package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class modifyController implements Initializable {

  @FXML private TextField nameText;
  @FXML private TextField surnameText;
  @FXML private Button choosePositionBtn;
  @FXML private Button chooseWorkplaceBtn;
  @FXML private Button saveNewEmployeeBtn;
  @FXML private ChoiceBox<?> employeeList;

  @FXML
  void saveEmployee(ActionEvent event) {
    //TODO: update employee
  }

  @FXML
  void showSetPosition(ActionEvent event) {

  }

  @FXML
  void showSetWorkplace(ActionEvent event) {

  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    //TODO: read employees as {name, surname} to choice box
    //TODO: read employee data to choice box pools
  }
}
