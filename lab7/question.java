import java.util.*;

//customer class
class Customer{
    static HashMap<Integer, ArrayList<String>> customers = new HashMap<>();
    static int customerNo;

    //constructor for customer registration
    Customer(String cstName, String PhNo, String cstMail, String cstAddress){
        //incrementing customer number
        customerNo += 1;
        //creating a list of customer details
        ArrayList<String> cstDetails = new ArrayList<>();
        //adding the customer details to the list
        cstDetails.add(cstName);
        cstDetails.add(cstMail);
        cstDetails.add(PhNo);
        cstDetails.add(cstAddress);
        //adding the customer to the hashmap
        customers.put(customerNo, cstDetails);
    }

    //updating the customer details
    void updateDetails(int customerNo, int oldField){
        //variable to store the new details
        String newDetails;
        //variable to capture the IO stream
        Scanner sc = new Scanner(System.in);
        //cases for updating different fields
        switch (oldField) {
            case 1:
                System.out.print("Enter the new name: ");
                newDetails = sc.nextLine();
                //updating the value
                customers.get(customerNo).set(0, newDetails);
                break;
            case 2:
                System.out.print("Enter the new phone number: ");
                newDetails = sc.nextLine();
                //updating the value
                customers.get(customerNo).set(1, newDetails);
                break;
            case 3:
                System.out.print("Enter the new mail ID: ");
                newDetails = sc.nextLine();
                //updating the value
                customers.get(customerNo).set(2, newDetails);
                break;
            case 4:
                System.out.print("Enter the new address: ");
                newDetails = sc.nextLine();
                //updating the value
                customers.get(customerNo).set(3, newDetails);
                break;
        }
        sc.close();
    }
    
    //display customer details
    void display(){
        System.out.println("\nCustomer Details\n---------------------");
        for(Object obj: customers.keySet()){
            System.out.println(obj+" : "+ customers.get(obj));
        }
    }
}

//product class
class Product{
    static int productId;
    //hashmap for catalog
    static HashMap<Integer, ArrayList<String>> catalog = new HashMap<>();
    //hashmap for inventory
    static HashMap<Integer, Integer> inventory = new HashMap<>();
    //creating default product catalog
    Product(String productName, String price, String itemWeight, int quantity){
        //arraylist to store product details
        ArrayList<String> temp = new ArrayList<>();
        productId+=1;
        temp.add(productName);
        temp.add(price);
        temp.add(itemWeight);
        //adding the product to catalog
        catalog.put(productId, temp);
        //adding the product to inventory
        inventory.put(productId, quantity);
    }

    //updating product details
    void productDetails(int productId, int fieldId){
        String value;
        Scanner sc = new Scanner(System.in);
        switch (fieldId) {
            //updating the name
            case 1:
                System.out.print("Enter the new name: ");
                value = sc.nextLine();
                catalog.get(productId).set(0, value);
                break;
            //updating the price
            case 2:
                System.out.print("Enter the new price: ");
                value = String.valueOf(sc.nextInt());
                catalog.get(productId).set(1, value);
                break;
            //updating the weight
            case 3:
                System.out.print("Enter the new weight: ");
                value = String.valueOf(sc.nextFloat());
                catalog.get(productId).set(2, value);
                break;
        }
        sc.close();
    }

    //updating the inventory
    void updateInventory(int id, int newQuantity){
        int flag = 1;
        do{
            if(inventory.containsKey(id)){
                //updating quantity of the product
                inventory.put(id, newQuantity);
                flag=0;
            }
            else{
                flag=1;
                System.out.println("The given product does not exist in the inventory!");
                System.out.println("Either give the ID of an existing product or add a new product to the inventory.");
            }
        } while(flag==1); //asking for correct input
    }

    //display product details
    void displayProduct(){
        for(Object obj: catalog.keySet()){
            System.out.println(obj+" : "+catalog.get(obj));
        }
    }

    //display inventory
    void displayInventory(){
        for(Object obj: inventory.keySet()){
            System.out.println(obj+" : "+inventory.get(obj));
        }
    }
}

//order class
class Order{
    static int orderId;
    int flag=1;
    //hashmap to store order details
    HashMap<Integer, HashMap<Integer, Integer>> orderDetails = new HashMap<>();
    //hashmap to store the status of the order
    HashMap<Integer, String> orderStatus = new HashMap<>();
    //placing an order
    Order(int custId, HashMap<Integer, Integer> items){
        //incrementing the orderId
        orderId+=1;
        //updating the inventory
        for(Integer obj: items.keySet()){
            int prevQuant = Product.inventory.get(obj);
            //checking the inventory
            if (prevQuant<items.get(obj)){
                flag = 0;
                break;
            }
            else{
                Product.inventory.put(obj, prevQuant-items.get(obj));
            }
        }
        //updating the status
        if (flag==0)
            orderStatus.put(orderId, "cancelled");
        else
            orderStatus.put(orderId, "processed");
        //adding the order details to the hashmap
        orderDetails.put(orderId, items);
    }

    //display order details
    void display(){
        for(Object obj: orderDetails.keySet()){
            System.out.print(obj+" : ");
            for(Object item: orderDetails.get(obj).keySet()){
                //printing the order details
                System.out.println(item+" : "+Product.catalog.get(item));
            }
        }
    }

    //tracking the order
    void trackOrder(int trackId){
        System.out.println("The order has been "+orderStatus.get(trackId));
    }
}

//main class
public class question{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine();
        System.out.println("PNo.");
        String no = sc.nextLine();
        System.out.println("ID");
        String id = sc.nextLine();
        System.out.println("Add");
        String add = sc.nextLine();
        Customer cust = new Customer(name, no, id, add);
        cust.updateDetails(1,1);
    }
}
