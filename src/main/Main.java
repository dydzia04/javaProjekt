package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main.fxml"));
        
        primaryStage.setTitle("CRUD");
        primaryStage.setScene(new Scene(root, 630, 395));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        DatabaseOperations database = new DatabaseOperations();

        System.out.println("\n\n--- Employees ---");
        System.out.println(database.getAllEmployees());

        System.out.println("\n\n--- Departaments ---");
        System.out.println(database.getAllDepartaments());

        System.out.println("\n\n--- Jobs ---");
        System.out.println(database.getAllJobs());

        System.out.println("\n\n--- Find name ---");
        database.findEmployeesByName("Michał");
        database.findEmployeesByName("Jacek");
        database.findEmployeesByName("Dawid");
        database.findEmployeesByName("Marcin");

        System.out.println("\n\n--- Find Surname ---");
        database.findEmployeesBySurName("Łukowy");
        database.findEmployeesBySurName("Wart");
        database.findEmployeesBySurName("Maga");
        database.findEmployeesBySurName("Duda");

        System.out.println("\n\n--- Find ID ---");
        database.findEmployeesByID(1);
        database.findEmployeesByID(3);
        database.findEmployeesByID(5);
        database.findEmployeesByID(7);

        launch(args);
    }
}

