package baseline;


/*
 *  UCF COP3330 Summer 2021 Application Assignment 1 Solution
 *  Copyright 2021 Emanuel Padro
 */


import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        }
        //will check serial code Input
        if(serial.matches("^[A-Z]-\\d{3}-\\d{3}-\\d{3}")){
            newItem.setSerial(serial);
        }else{
            errorManager("Input non Serial Code");
        }

        //Will check Value Input
        if(iValue.matches("\\d+(?:\\.\\d+)?")){
            double value = Double.parseDouble(iValue);
            if( value < 0){
                errorManager("Input Negative Number");
            }else{
                newItem.setValue(value);
            }
        }else {
            errorManager("Input NonNumber");
        }

        return newItem;
    }

    public boolean itemCheck(Item createdItem, ObservableList<Item> Items){
        boolean issues = false;

        /*This code will check for any null results and duplicate serials to
        avoid printing erroneous input*/

        if (createdItem == null
                || createdItem.getValue() == -1
                || createdItem.getSerial() == null
                || createdItem.getName() == null ){
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

    public void fileCreator(String filename, ObservableList<Item> Items) throws IOException {
        File file = new File(filename);

        try{
            if(file.createNewFile()) {
                System.out.println("File made");
            }else{
                System.out.println("File exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = new FileOutputStream(file, true);
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        List<Item> listItems = new ArrayList<>(Items);
        oos.writeObject(listItems);
        byte[] bytes = bos.toByteArray();


        fos.write(bytes);
        fos.close();

    }

    public List<Item> fileReader(String fileName)  {

        try ( InputStream fis = new FileInputStream(fileName);
              ObjectInputStream oin = new ObjectInputStream(fis)
        ){
            byte[] fileContent = new byte[(int) fileName.length()];
            ByteArrayInputStream bis = new ByteArrayInputStream(fileContent);
            List<Item> readItems = (List<Item>) oin.readObject();

            readItems.forEach(System.out::println);

            return readItems;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    ArrayList<String> errors = new ArrayList<>();

    public void errorManager(String error){
        //will check error Num for ID and print out statement where needed
        if ("Input Name Wrong".equals(error)) {
            System.out.println("Please enter a name between 2 and 256 characters");
            errors.add("Please enter a name between 2 and 256 characters\n");
        }
        if(error.equals("Input NonNumber")){
            System.out.println("Please Enter a number above 0 for Value");
            errors.add("Please enter a number for the item Value\n");
        }
        if(error.equals("Input Negative Number")){
            System.out.println("Item value must be above 0");
            errors.add("Item value must be above 0\n");
        }
        if(error.equals("Input non Serial Code")){
            System.out.println("Please enter a serial code in the" +
                    " format A-xxx-xxx-xxx where x is letter or digit");
            errors.add("Please enter a serial code in the" +
                    "format A-xxx-xxx-xxx where x is a letter or digit and A a letter\n");
        }
        if(error.equals("Failed to add Item")){
            System.out.println("Failed to add Item to Inventory");
            errors.add("Failed to add Item to Inventory\n");
        }
        if(error.equals("Duplicate Serial")){
            System.out.println("There is already an Item with that Serial #");
            errors.add("There is already an Item with that Serial #\n");
        }
        if(error.equals("Failed to Add Edit Item")){
            System.out.println("Failed to edit item please fix errors and try again");
            errors.add("Failed to edit item please fix errors and try again\n");
        }
        if(error.equals("File Exists")){
            System.out.println("File already exists");
            errors.add("File already exists\n");
        }
    }

    public String returnErrors(){
        return String.valueOf(errors)
                .replace("[","")
                .replace(",","")
                .replace("]","");
    }

    public void clearErrors(){
        errors.clear();
    }

}
