package baseline;


/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */

public class Operations {

    public Item addItem(String name, String serial, String iValue){
        Item newItem = new Item();

        //Will check name Input
        if(name.length() >= 2 && name.length() <= 256 && name.matches("^[a-zA-Z]*$")){
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

    public void errorManager(String error){
        //will check error Num for ID and print out statement where needed
        if(error.equals("Input Name Wrong")){
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
    }

}
