import java.util.*;
import java.time.LocalDate;

class Inventory{
    static HashMap<Integer, Integer> inventory;
    static{
        inventory.put(101, 10);
        inventory.put(102, 8);
        inventory.put(103, 5);
        inventory.put(104, 6);
        inventory.put(105, 15);
    }
}

class Item{
    //hashmap to store the items
    static HashMap<String, Integer> items;

    static{
        items.put("Milk", 101);
        items.put("Eggs", 102);
        items.put("Bread", 103);
        items.put("Juice", 104);
        items.put("Water Bottle", 105);
    }

    //constructor to add item to the inventory
    Item(int itemId, String itemName, int quantity){
        Inventory.inventory.put(itemId, quantity);
        items.putIfAbsent(itemName, itemId);
    }
}

class Order{
    int orderId;
    String orderName;
    LocalDate orderDate;
    String itemName;
    int quantity;
    Order(int orderId, String orderName, String itemName, int quantity){
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderDate = LocalDate.now();
        this.itemName = itemName;
        this.quantity = quantity;
    }
}

class EnhancedOrdeFfulfillmentSystem{
    // //creating the placeOrder method
    // void placeOrder(Order order){

    // }

    // //creating the startProcessing method
    // void startProcessing(){

    // }

    // //creating the waitForCompletion method
    // void waitForCompletion(){

    // }

    //method to update the inventory
    void updateInventory(Order order){
        int itemId = Item.items.get(order.itemName);
        int quantity = Inventory.inventory.get(itemId);
        Inventory.inventory.put(itemId, quantity);
        System.out.println("Inventory Updated!");
    }

    //checking if enough stock is available
    boolean checkInventoryAvailability(Order order){
        int quantityNeeded = order.quantity;
        int itemId = Item.items.get(order.itemName);
        //if there is enough stock available
        if(quantityNeeded<=Inventory.inventory.get(itemId)){
            return true;
        }
        else
            return false;
    }
}

public class question2{
    public static void main(String[] args) {
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("Logging in as:");
        System.out.println("1. Admin\n2. Customer");
        int temp = sc.nextInt();
        sc.nextLine();

        //admin functionality
        if(temp == 1){
            System.out.println("Enter the password for admin: ");
            String pw = sc.nextLine();
            if (pw.equals("hehe")){
                System.out.println("Logged in as admin!\n\n");
            }
            else{
                System.out.println("Incorrect Password! Logging out!");
                flag = false;
            }

            while(flag==true){
                System.out.println("What operation do you want to perform?");
                System.out.println("1.Update the inventory\n2.Display the inventory\n3.Exit\n\n");
                switch (sc.nextInt()) {
                    //updating the inventory
                    case 1:
                        System.out.println("Enter the item ID: ");
                        int itemId = sc.nextInt();
                        System.out.println("Enter the item name: ");
                        String itemName = sc.nextLine();
                        System.out.println("Enter the quantity of the item: ");
                        int quantity = sc.nextInt();

                        //adding the values to their respective hashmaps
                        Item.items.put(itemName, itemId);
                        Inventory.inventory.put(itemId, quantity);
                        break;
                    
                    //displaying the inventory
                    case 2:
                        System.out.println("Displaying the inventory...");
                        for(Object obj: Inventory.inventory.keySet()){
                            System.out.println(obj+" : "+Inventory.inventory.get(obj));
                        }
                        break;
                    
                    case 3:
                        flag = false;
                        break;
                }
            }
            
        }

        //customer functionality
        // else if (temp == 2){
        //     continue;
        // }
    }
}
