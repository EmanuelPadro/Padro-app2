package baseline;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ListAppController {
    public TextField newItemSerial;
    public TextField newItemName;
    public TextField newItemValue;

    public TextField editItemID;
    public TextField editSerial;
    public TextField editName;
    public TextField editValue;

    public Button addItemButton;
    public Button removeItemButton;
    public Button editItemButton;

    public Button displayItemsButton;
    public Button removeAllButton;
    public TableView<Item> tableView;

    ObservableList<Item> Items = FXCollections.observableArrayList();
    Operations operations = new Operations();

    public void addItem(ActionEvent actionEvent) {
        String name = newItemName.getText();
        String serial = newItemSerial.getText();
        String value = newItemValue.getText();

        Item newItem = operations.addItem(name, serial, value);
        Items.add(newItem);
        tableView.setItems(Items);
    }

    public void RemoveItem(ActionEvent actionEvent) {

    }

    public void editItem(ActionEvent actionEvent) {
    }

    public void displayItems(ActionEvent actionEvent) {
    }

    public void removeAll(ActionEvent actionEvent) {
    }
}
