package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class InventoryApplicationTests {

    //This tests will test all operations methods as these are the most important

    @Test
    void TestCreateItem(){
        System.out.println("Test 1 Results");
        Operations testOperations = new Operations();
        Item testItem1 = new Item(10.0,"A-999-999-999","Amogus Plush");
        Item item = testOperations.createItem("Amogus Plush","A-999-999-999","10");

        //These assertions check whether the created item by the function matches the input given to it
        Assertions.assertEquals(testItem1.getName(),item.getName());
        System.out.println("Names: " + testItem1.getName() + " |VS| " + item.getName());
        Assertions.assertEquals(testItem1.getSerial(),item.getSerial());
        System.out.println("Serial: " + testItem1.getSerial() + " |VS| " + item.getSerial());
        Assertions.assertEquals(testItem1.getValue(),item.getValue());
        System.out.println("Values: " + testItem1.getValue() + " |VS| " + item.getValue());
    }

    @Test
    void TestItemCheck(){
        Operations testOperations = new Operations();
        ObservableList<Item> testItems = FXCollections.observableArrayList();
        Item testItem1 = new Item(10.0,"A-999-999-999","Amogus Plush");
        //duplicate item different values

        //This assertion will send a item to the item checker and expect a true boolean value
        Assertions.assertTrue(testOperations.itemCheck(testItem1, testItems));
        System.out.println(" first item check is: " + testOperations.itemCheck(testItem1,testItems));

        //This tests if item checker catches duplicate serials
        testItems.add(testItem1);
        Assertions.assertFalse(testOperations.itemCheck(testItem1,testItems));
        System.out.println("Second item check is: " + testOperations.itemCheck(testItem1,testItems));
    }

    @Test
    void TestErrorManagement(){
        //This Test is made to test the error management process
        Operations testOperations = new Operations();
        testOperations.errorManager("Input NonNumber");
        testOperations.errorManager("Failed to add Item");

        //will test two errors for proper output
        String testErrors = testOperations.returnErrors();
        String expectedErrors = """
                Please enter a number for the item Value
                 Failed to add Item to Inventory
                """;
        Assertions.assertEquals(expectedErrors,testErrors);

        //Will test errors is cleared correctly
        testOperations.clearErrors();
        testErrors = testOperations.returnErrors();
        Assertions.assertEquals("",testErrors);


    }

    @Test
    void saveLoadTest(){
        Operations testOperations = new Operations();
        ObservableList<Item> testItems = FXCollections.observableArrayList();
        String path = "D:\\emanu\\object oriented\\Projects Folder\\Padro-app2\\src\\test\\resources\\testing.txt";
        ObservableList<Item> compareItem = FXCollections.observableArrayList();

        File expectedfile = new File(path);
        if(expectedfile.delete()){
            System.out.println("Testing Message: saveLoadTest file was deleted");
        }


        Item testItem4 = testOperations.createItem("Helmet", "A-666-666-666", "90");
        Item testItem5 = testOperations.createItem("Kunai", "A-444-666-444", "96");

        testItems.add(testItem4);
        testItems.add(testItem5);
        compareItem.add(testItem4);
        compareItem.add(testItem5);

        try {
            testOperations.fileCreator(path, testItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Item> loadedItems = testOperations.fileReader(path);
        testItems.remove(0,testItems.size());
        testItems.addAll(loadedItems);

        //These assertions compare the items saved vs the ones loaded
        Assertions.assertEquals(compareItem.get(0).getName(),testItems.get(0).getName());
        Assertions.assertEquals(compareItem.get(0).getSerial(),testItems.get(0).getSerial());
        Assertions.assertEquals(compareItem.get(0).getValue(),testItems.get(0).getValue());
        Assertions.assertEquals(compareItem.get(1).getName(),testItems.get(1).getName());
        Assertions.assertEquals(compareItem.get(1).getSerial(),testItems.get(1).getSerial());
        Assertions.assertEquals(compareItem.get(1).getValue(),testItems.get(1).getValue());

    }

}
