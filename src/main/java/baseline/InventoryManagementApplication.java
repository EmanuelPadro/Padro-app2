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
import javafx.stage.Stage;

import java.util.Objects;

public class InventoryManagementApplication extends Application {

    TableView<Item> table;


    @Override
    public void start(Stage stage) throws Exception {
        Parent root =
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ListApplication.fxml")));

        //Name Column
        TableColumn<Item, String> nameColumn = new TableColumn<>("Item Name");
        nameColumn.setMinWidth(250);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Item, String> serialColumn = new TableColumn<>("Item Serial");
        serialColumn.setMinWidth(200);
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));

        TableColumn<Item, Double> valueColumn = new TableColumn<>("Item Value");
        valueColumn.setMinWidth(150);
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        table = new TableView<>();
        table.setItems(getItems());
        table.getColumns().addAll(nameColumn,serialColumn,valueColumn);

        //add vBox



        Scene scene = new Scene(root);
        stage.setTitle("List Application");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Item> getItems(){
        ObservableList<Item> items = FXCollections.observableArrayList();
        items.add(new Item(2.00,"A-999-999-999","T Item"));
        items.add(new Item(3.00,"B-999-999-999","T Video"));
        items.add(new Item(4.00,"C-999-999-999","T Money"));

        return items;

    }
}