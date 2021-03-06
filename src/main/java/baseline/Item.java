package baseline;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */

import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//This class is the blueprint for all items
public class Item implements Serializable {
    private double value;
    private String serial;
    private String name;

    public Item() {
        this(-1,null,null);
    }

    public Item(double iValue, String iSerial, String iName){
        this.value = iValue;
        serial = iSerial;
        name = iName;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}