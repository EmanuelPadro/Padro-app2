package baseline;


/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */

public class Operations {

    public Item addItem(String name, String serial, String iValue){

        try {
            Double.parseDouble(iValue);
        }
        catch(Exception e){
            errorManager("Entered NonNumber");
        }
        double value = Double.parseDouble(iValue);
        if( value < 0){

            errorManager("Negative Number");
        }

        Item newItem = new Item();
        newItem.setName(name);
        newItem.setSerial(serial);
        newItem.setValue(value);

        return newItem;
    }

    public void errorManager(String error){
        //will check error Num for ID and print out statement where needed
        if(error.equals("Entered NonNumber")){
            System.out.println("Please Enter a number above 0 for Value");
        }
        if(error.equals("Negative Number")){
            System.out.println("Input a number above 0");
        }

    }

}
