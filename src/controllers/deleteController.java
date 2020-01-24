package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class deleteController {

  @FXML private TableView<?> usersTable;
  @FXML private Button deleteBtn;

  @FXML
  void deleteUser(ActionEvent event) {
    //TODO: open dialog window asking for confirmation to delete with credentials that was highlighted
  }

}
