package entity;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import main.DatabaseOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowJobData {
	private int id;
	private String jobName;
	private int salary;
	private Button delete;// TODO: center, make it icon

	public ShowJobData(int id, String jobName, int salary, Button delete, Button more) {
		this.id = id;
		this.jobName = jobName;
		this.salary = salary;
		this.delete = delete;
	}

	public ShowJobData() {
		this.delete = new Button("Usuń");
		try {
			FileInputStream inputStream = new FileInputStream("src/img/002-trash.png");
			Image image = new Image(inputStream);
			ImageView imageView = new ImageView(image);
			this.delete = new Button("", imageView);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		this.delete.setOnAction(event -> {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Usuwanie");
			alert.setContentText("Czy usunąć " + this.jobName);
			ButtonType okButton = new ButtonType("Tak", ButtonBar.ButtonData.YES);
			ButtonType noButton = new ButtonType("Nie", ButtonBar.ButtonData.NO);
			ButtonType cancelButton = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
			alert.showAndWait().ifPresent(type -> {
				if (type == okButton) {
					DatabaseOperations db = new DatabaseOperations();
					db.deleteJob(this.id);
				} else {
					alert.close();
				}
			});
		});
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}
}
