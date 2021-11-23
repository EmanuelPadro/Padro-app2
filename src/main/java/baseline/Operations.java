package baseline;


/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */


import javafx.collections.ObservableList;

import java.util.ArrayList;

//"^[a-zA-Z]+\s*$"
public class Operations {

    public Item createItem(String name, String serial, String iValue){
        Item newItem = new Item();

        //Will check name Input
        name = name.trim();
        name = name.replaceAll("\\s+"," ");

        if(name.length() >= 2 && name.length() <= 256 && name.matches("^[a-zA-Z ]*$")){
            newItem.setName(name);
        }else{
            errorManager("Input Name Wrong");
            return null;
        }

        //will check serial code Input
        if(serial.matches("^[A-Z]{1}-\\d{3}-\\d{3}-\\d{3}")){
            newItem.setSerial(serial);
        }else{
            errorManager("Input non Serial Code");
            return null;
        }

        //Will check Value Input
        try {
            Double.parseDouble(iValue);
        }
        catch(Exception e){
            errorManager("Input NonNumber");
        }

        double value = Double.parseDouble(iValue);
        if( value < 0){
            errorManager("Input Negative Number");
            return null;
        }else{
            newItem.setValue(value);
        }


        return newItem;
    }

    public boolean itemCheck(Item createdItem, ObservableList<Item> Items){
        boolean issues = false;

        /*This code will check for any null results and duplicate serials to
        avoid printing erroneous input*/

        if (createdItem == null ){
            issues = true;
        }else{
            for (Item item : Items) {
                if (item.getSerial().equals(createdItem.getSerial())) {
                    issues = true;
                    errorManager("Duplicate Serial");
                    break;
                }
            }
        }
        return !issues;
    }



    public void errorManager(String error){
        ArrayList<String> errors = new ArrayList<>();
        //will check error Num for ID and print out statement where needed
        if ("Input Name Wrong".equals(error)) {
            System.out.println("Please enter a name between 2 and 256 characters");
        }
        if(error.equals("Input NonNumber")){
            System.out.println("Please Enter a number above 0 for Value");
        }
        if(error.equals("Input Negative Number")){
            System.out.println("Item value must be above 0");
        }
        if(error.equals("Input non Serial Code")){
            System.out.println("Please enter a serial code in the" +
                    " format A-xxx-xxx-xxx where x is letter or digit");
        }
        if(error.equals("Failed to add Item")){
            System.out.println("Failed to add Item to Inventory");
        }
        if(error.equals("Duplicate Serial")){
            System.out.println("There is already an Item with that Serial #");
        }
        if(error.equals("Failed to Add Edit Item")){
            System.out.println("Failed to edit item please fix errors and try again");
        }
    }

}
