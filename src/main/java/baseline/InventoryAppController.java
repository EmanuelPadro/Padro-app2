package baseline;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InventoryAppController {
    public TextField newItemSerial;
    public TextField newItemName;
    public TextField newItemValue;

    public TextField editItemID;
    public TextField editSerial;
    public TextField editName;
    public TextField editValue;

    public TextField saveFileName;
    public TextField loadFileName;

    public Button addItemButton;
    public Button removeItemButton;
    public Button editItemButton;

    public Button removeAllButton;
    public TableView<Item> tableView;
    public Button saveButton;
    public Button loadButton;

    public TextArea errorLog;

    ObservableList<Item> Items = FXCollections.observableArrayList();
    Operations operations = new Operations();

    public void addItem(ActionEvent actionEvent) {
        String name = newItemName.getText();
        String serial = newItemSerial.getText();
        String value = newItemValue.getText();

        Item newItem = operations.createItem(name, serial, value);
        //checks if there issues and prints otherwise
        if(operations.itemCheck(newItem, Items)){
            Items.add(newItem);
            tableView.setItems(Items);
        }else{
            operations.errorManager("Failed to add Item");
        }
        errorLog.setText(operations.returnErrors());
        operations.clearErrors();
    }

    public void RemoveItem(ActionEvent actionEvent) {
        String serialID = editItemID.getText();
        /*The line bellow is a simplified for(){if(){}} loop that will
        items for the serial and delete it*/
        Items.removeIf(item -> item.getSerial().equals(serialID));
    }

    public void editItem(ActionEvent actionEvent) {
        String serialID = editItemID.getText();
        String name = editName.getText();
        String serial = editSerial.getText();
        String value = editValue.getText();

        //These following if statements check for blank boxes to set serials to current serial in that case
        for(int i = 0; i < Items.size(); i++){
            Item item = Items.get(i);
            if(item.getSerial().equals(serialID)){

                if(name.isBlank()){
                    name = item.getName();
                }
                if(serial.isBlank()){
                    serial = item.getSerial();
                }
                if(value.isBlank()){
                    value = String.valueOf(item.getValue());
                }

                //Finally creates item and replaces previous item with it
                Item newItem = operations.createItem(name, serial, value);
                if(newItem != null){
                    Items.set(Items.indexOf(item),newItem);
                    tableView.setItems(Items);
                }else{
                    operations.errorManager("Failed to Add Edit Item");
                }
            }
        }
        //Prints errors at the end of doing everything
        errorLog.setText(operations.returnErrors());
        operations.clearErrors();
    }

    public void removeAll(ActionEvent actionEvent) {
        Items.remove(0,Items.size());
    }

    public void load(ActionEvent actionEvent) {
        String filename = loadFileName.getText();
        List<Item> readItems = operations.fileReader(filename);
        Items.remove(0,Items.size());
        Items.addAll(readItems);
        tableView.setItems(Items);
        errorLog.setText(operations.returnErrors());
        operations.clearErrors();
    }

    public void save(ActionEvent actionEvent)  {
        String filename = saveFileName.getText();
        try {
            operations.fileCreator(filename,Items);
        } catch (IOException e) {
            e.printStackTrace();
            operations.errorManager("File Save Error");
        }
        errorLog.setText(operations.returnErrors());
        operations.clearErrors();
    }
}
