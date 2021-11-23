package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
    void TestErrorManager(){
        Operations testOperations = new Operations();
    }
}
