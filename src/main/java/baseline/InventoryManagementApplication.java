package baseline;

/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 first_name last_name
 */


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;

public class InventoryManagementApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("InventoryApplication.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("List Application");
        stage.setScene(scene);
        stage.show();



    }

    public static void main(String[] args) {
        launch(args);
    }
}