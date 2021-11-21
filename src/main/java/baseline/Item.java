package baseline;

/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */

//This class is the blueprint for all items
public class Item {
    double value;
    String serial;
    String name;

    public Item(double iValue, String iSerial, String iName){
        value = iValue;
        serial = iSerial;
        name = iName;
    }

}