import java.util.*;
import java.time.LocalDate;

class Inventory{
    //hashmap to keep track of the inventory
    static HashMap<Integer, Integer> inventory = new HashMap<>();
    Inventory(){
        System.out.println("Updating the inventory...");
        inventory.put(101, 10);
        inventory.put(102, 8);
        inventory.put(103, 5);
        inventory.put(104, 6);
        inventory.put(105, 15);
    }
}

class Item{
    //hashmap to store the items
    static HashMap<String, Integer> items = new HashMap<>();
    Item(){
        System.out.println("Updating the items...\n\n");
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
    //variable to store the details of all the order
    static HashMap<Integer, Object> allOrders = new HashMap<>();
    //variable to store the details of the new order
    ArrayList<Object> newOrder = new ArrayList<>();
    //variables to store the item details of an instance
    String itemName;
    int quantity;
    int orderNo;
    
    Order(int orderId, String itemName, int quantity){
        this.orderNo = orderId;
        this.itemName = itemName;
        this.quantity = quantity;

        this.newOrder.add(LocalDate.now());
        this.newOrder.add(this.itemName);
        this.newOrder.add(this.quantity);
        
        //keeping track of all the orders
        allOrders.put(orderId, this.newOrder);
        System.out.println("Order Placed!");
    }
}

class EnhancedOrdeFfulfillmentSystem{

    //hashmap to store the order status
    static HashMap<Integer, String> orderStatus = new HashMap<>();

    //creating the placeOrder method
    static void placeOrder(Order order){
        
        //checking if the given order can be completed or not
        boolean goAhead = checkInventoryAvailability(order);
        if (goAhead==true)
            startProcessing(order);
        else
            //setting status to "cancelled" if there is not enough stock
            orderStatus.put(order.orderNo, "cancelled due to shortage of supply.");
    }

    //creating the startProcessing method
    static void startProcessing(Order order){
        //updating the inventory
        updateInventory(order);
        //setting status to "processed" after the inventory is updated
        orderStatus.put(order.orderNo, "processed!");
    }

    //creating the waitForCompletion method
    // static void waitForCompletion(){

    // }

    //method to update the inventory
    static void updateInventory(Order order){
        int itemId = Item.items.get(order.itemName);
        Inventory.inventory.put(itemId, Inventory.inventory.get(itemId) - order.quantity);
    }

    //checking if enough stock is available
    static boolean checkInventoryAvailability(Order order){
        int quantityNeeded = order.quantity;
        try{
            int itemId = Item.items.get(order.itemName);
            //if there is enough stock available
            if(quantityNeeded<=Inventory.inventory.get(itemId)){
                return true;
            }
            else
                return false;
        } catch(Exception e){
            return false; //catching the exception when the item is not in the inventory
        }
    }

    //tracking an order
    void trackOrderStatus(int OrderId){
        String status = orderStatus.get(OrderId);
        System.out.println("The order has been "+status+"\n");
    }
}

public class question2{
    public static void main(String[] args){
        boolean flag = true;
        //updating the inventory
        new Inventory();
        //updating the items
        new Item();
        while(flag==true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Logging in as:");
            System.out.println("1. Admin\n2. Customer\n3. Exit");
            int tempOuter = sc.nextInt();
            sc.nextLine();

            //admin functionality
            if(tempOuter == 1){
                System.out.println("\nEnter the password for admin: ");
                String pw = sc.nextLine();
                if (pw.equals("hehe")){
                    System.out.println("Logged in as admin!\n\n");
                }
                else{
                    System.out.println("Incorrect Password! Logging out!");
                    flag = false;
                }

                while(flag==true){
                    System.out.println("\nWhat operation do you want to perform?");
                    System.out.println("1.Update the inventory\n2.Display the inventory\n3.Exit\n\n");
                    switch (sc.nextInt()) {
                        //updating the inventory
                        case 1:
                            System.out.println("Enter the item ID: ");
                            int itemId = sc.nextInt();
                            sc.nextLine();
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
                            System.out.println("\nDisplaying the inventory...");
                            for(Object obj: Inventory.inventory.keySet()){
                                System.out.println(obj+" : "+Inventory.inventory.get(obj));
                            }
                            System.out.println("\nDisplaying the items...");
                            for(Object obj: Item.items.keySet()){
                                System.out.println(obj+" : "+Item.items.get(obj));
                            }
                            break;
                        
                        case 3:
                            flag = false;
                            break;
                    }
                }
                
            }

            //customer functionality
            else if (tempOuter == 2){
                
                //variable to store the order number
                int orderNo = 0;
                int repeat = 0;

                while(repeat!=-1){
                    //displaying the inventory
                    System.out.println("\n\nInventory");
                    System.out.println("-----------------------");
                    for(Object obj: Item.items.keySet()){
                        System.out.println(obj+" : "+Item.items.get(obj));
                    }

                    //asking for user input
                    System.out.println("\nWhat operation do you want to perform?");
                    System.out.println("1. Place an order\n2. Track an order\n3.Exit");
                    int temp = sc.nextInt();

                    switch (temp) {
                        case 1:
                            while(flag==true){
                                
                                //placing an order
                                System.out.println("Enter the item name: ");
                                String itemName = sc.next();
                                sc.nextLine();
                                System.out.println("Enter the quantity of the item you want to order: ");
                                int quantity = sc.nextInt();
                                orderNo+=1;
                                Order latestOrder = new Order(orderNo, itemName, quantity);
                                
                                //placing the order
                                EnhancedOrdeFfulfillmentSystem.placeOrder(latestOrder);
                                System.out.println("\nDo you want to place more orders?");
                                System.out.println("1. Yes\n2. No");
                                temp = sc.nextInt();
                                if(temp==2)
                                    flag=false;
                            }
                            break;

                        case 2:
                            flag=true;
                            do{
                                //displaying the current orders
                                System.out.println("Current Orders\n----------------");
                                for(Object obj: Order.allOrders.keySet()){
                                    System.out.println(obj+" : "+Order.allOrders.get(obj));
                                }   
                                System.out.println("\nWhich order do you need an update on? (-1 to exit)"); 
                                int trackNo = sc.nextInt();
                                if (trackNo!=-1){
                                    //creating a reference variable to track the order
                                    EnhancedOrdeFfulfillmentSystem trackObj = new EnhancedOrdeFfulfillmentSystem();

                                    //tracking the order
                                    trackObj.trackOrderStatus(trackNo);
                                    }
                                else
                                    flag=false;
                            } while(flag==true);

                        case 3:
                            repeat=-1;
                    
                        default:
                            break;
                    }
                }
            }
            //repeating the outer loop
            if(tempOuter==3)
                flag=false;
            else
                flag=true;
        }
    }
}