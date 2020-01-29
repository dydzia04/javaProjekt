package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));

		primaryStage.setTitle("CRUD");
		primaryStage.setScene(new Scene(root, 663, 400));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	private static void test() {
		DatabaseOperations database = new DatabaseOperations();
        /*
        System.out.println("\n\n--- Employees ---");
        System.out.println(database.getAllEmployees());

        System.out.println("\n\n--- Departaments ---");
        System.out.println(database.getAllDepartaments());

        System.out.println("\n\n--- Jobs ---");
        System.out.println(database.getAllJobs());

        System.out.println("\n\n--- Find name ---");
        database.findEmployeesByName("P");
        database.findEmployeesByName("Jacek");


        System.out.println("\n\n--- Find Surname ---");
        database.findEmployeesBySurName("Łukowy");
        database.findEmployeesBySurName("D");

        System.out.println("\n\n--- Find ID ---");
        database.findEmployeesByID(1);
        database.findEmployeesByID(3);
        database.findEmployeesByID(5);

        System.out.println("--- ADD ---");
        database.createEmployee("Dominik", "Maga", 1, 1);

        database.deleteEmployee(16);

        database.updateEmployeeSurName(3, "Dominik");
        database.updateEmployeeFirstName(3, "Maga");
        database.updateEmployeeIdJob(3, 1);
        database.updateEmployeeIdDept(3,1);
        */
		// ---- WORKING ----
	}


	public static void main(String[] args) {

		//Icons made by https://www.flaticon.com/authors/srip
		test();
		launch(args);
	}
}

