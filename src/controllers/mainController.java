package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

	@FXML
	private Tab employeesTab;
	@FXML
	private Tab departmentsTab;
	@FXML
	private Tab jobsTab;

	void setEmployeesTabText() {
		try {
			FileInputStream inputStream = new FileInputStream("src/img/004-male-r.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			imageView.setRotate(imageView.getRotate() + 90);
			employeesTab.setGraphic(imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	void setDepartmentsTabText() {
		try {
			FileInputStream inputStream = new FileInputStream("src/img/005-home-r.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			imageView.setRotate(imageView.getRotate() + 90);
			departmentsTab.setGraphic(imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	void setJobsTabText() {
		try {
			FileInputStream inputStream = new FileInputStream("src/img/006-suitcase-r.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			imageView.setRotate(imageView.getRotate() + 90);
			jobsTab.setGraphic(imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDepartmentsTabText();
		setEmployeesTabText();
		setJobsTabText();
	}
}
