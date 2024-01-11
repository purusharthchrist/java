import java.util.*;

//customer class
class Customer{
    static HashMap<Integer, ArrayList<String>> customers = new HashMap<>();
    static int customerNo;

    //Default customers
    Customer(){
        customerNo += 1;
        ArrayList<String> cstDetails = new ArrayList<>();
        cstDetails.add("Khushi");
        cstDetails.add("9876589545");
        cstDetails.add("khushi@gmail.com");
        cstDetails.add("New Street");
        customers.put(customerNo, cstDetails);

        customerNo += 1;
        cstDetails = new ArrayList<>();
        cstDetails.add("Mishi");
        cstDetails.add("8957625412");
        cstDetails.add("mishi@gmail.com");
        cstDetails.add("Old Street");
        customers.put(customerNo, cstDetails);

        customerNo += 1;
        cstDetails = new ArrayList<>();
        cstDetails.add("Suyash");
        cstDetails.add("7856954856");
        cstDetails.add("suyash@gmail.com");
        cstDetails.add("East Street");
        customers.put(customerNo, cstDetails);

        customerNo += 1;
        cstDetails = new ArrayList<>();
        cstDetails.add("Vishwas");
        cstDetails.add("7859645823");
        cstDetails.add("vishwas@gmail.com");
        cstDetails.add("West Street");
        customers.put(customerNo, cstDetails);
    }

    //constructor for customer registration
    Customer(String cstName, String phNo, String cstMail, String cstAddress){
        //incrementing customer number
        customerNo += 1;
        //creating a list of customer details
        ArrayList<String> cstDetails = new ArrayList<>();
        //adding the customer details to the list
        cstDetails.add(cstName);
        cstDetails.add(phNo);
        cstDetails.add(cstMail);
        cstDetails.add(cstAddress);
        //adding the customer to the hashmap
        customers.put(customerNo, cstDetails);
        System.out.println("Login created successfully!");
        System.out.println("Your customer ID is "+customerNo+".");
        System.out.println("Please remeber this for future purposes.\n");
    }

    //updating the customer details
    static void updateDetails(int customerNo, int oldField){
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
    }
    
    //display customer details
    static void display(){
        System.out.println("\nCustomer Details\n---------------------");
        TreeSet<Integer> sortedCustomers = new TreeSet<>();
        sortedCustomers.addAll(customers.keySet());
        for(Object obj: sortedCustomers){
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
    Product(){
        ArrayList<String> temp = new ArrayList<>();
        productId+=1;
        temp.add("Milk");
        temp.add("24");
        temp.add("250");
        catalog.put(productId, temp);
        inventory.put(productId, 12);

        temp = new ArrayList<>();
        productId+=1;
        temp.add("Bread");
        temp.add("40");
        temp.add("200");
        catalog.put(productId, temp);
        inventory.put(productId, 15);

        temp = new ArrayList<>();
        productId+=1;
        temp.add("Butter");
        temp.add("25");
        temp.add("100");
        catalog.put(productId, temp);
        inventory.put(productId, 18);

        temp = new ArrayList<>();
        productId+=1;
        temp.add("Eggs");
        temp.add("12");
        temp.add("20");
        catalog.put(productId, temp);
        inventory.put(productId, 30);
    }
    //creating products with user inputs
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
    static void productDetails(int productId, int fieldId){
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
    static void displayProduct(){
        TreeSet<Integer> sortedOrders = new TreeSet<>();
        sortedOrders.addAll(catalog.keySet());
        for(Object obj: sortedOrders){
            System.out.println(obj+" : "+catalog.get(obj));
        }
    }

    //display inventory
    static void displayInventory(){
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
    static HashMap<Integer, HashMap<Integer, Integer>> orderDetails = new HashMap<>();
    //hashmap to store the status of the order
    static HashMap<Integer, String> orderStatus = new HashMap<>();
    //hashmap for items ordered by customers
    static HashMap<Integer, HashSet<String>> customerItems = new HashMap<>();
    //hashmap for customerId and orderId
    static HashMap<Integer, ArrayList<Integer>> customerOrders = new HashMap<>();
    //placing an order
    Order(int custId, HashMap<Integer, Integer> items){
        //incrementing the orderId
        orderId+=1;
        //adding the order to the customerId
        if (customerOrders.containsKey(custId)){
            customerOrders.get(custId).add(orderId);
        }
        else{
            ArrayList<Integer> tempArray = new ArrayList<>();
            tempArray.add(orderId);
            customerOrders.put(custId, tempArray);
        }
        //updating the inventory
        for(Integer obj: items.keySet()){
            int prevQuant = Product.inventory.get(obj);
            //checking the inventory
            if (prevQuant<items.get(obj)){
                flag = 0;
                break;
            }
        }
        //updating the status
        if (flag==0)
            orderStatus.put(orderId, "cancelled because one or more items are out of stock!");
        else{
            for(Integer obj: items.keySet()){
                int prevQuant = Product.inventory.get(obj);
                //updating the inventory
                Product.inventory.put(obj, prevQuant-items.get(obj));

                //adding the item to customer items
                if (customerItems.containsKey(custId))
                    customerItems.get(custId).add(Product.catalog.get(obj).get(0));
                else{
                    //hashset to store the unique items
                    HashSet<String> uniqueItems = new HashSet<>();
                    uniqueItems.add(Product.catalog.get(obj).get(0));
                    customerItems.put(custId, uniqueItems);
                }
            }
            //updating the status
            orderStatus.put(orderId, "processed");
        }
        //adding the order details to the hashmap
        orderDetails.put(orderId, items);
        System.out.println("Your order ID is: "+orderId);
    }

    //display order details for admin
    static void display(){
        for(Object obj: orderDetails.keySet()){
            System.out.println("Order No.: "+obj);
            for(Object item: orderDetails.get(obj).keySet()){
                //printing the order details
                System.out.println(item+" : "+Product.catalog.get(item).get(0));
            }
        }
    }

    //display order details for customer
    static void display(int orderId){
            for(Object item: orderDetails.get(orderId).keySet()){
                //printing the order details
                System.out.println(item+" : "+Product.catalog.get(item));
            }
    }

    //tracking the order
    static void trackOrder(int trackId){
        System.out.println("The order has been "+orderStatus.get(trackId));
    }
}

//main class
public class question{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int outerFlag=1;
        int choice;

        //initializing the customers and products
        new Customer();
        new Product();

        while (outerFlag==1){
            System.out.println("\n\nLogging in as...\n1. Admin\n2. Customer\n3. Exit");
            int temp = sc.nextInt();
            int flag=1;
                switch (temp) {
                    
                    //admin functionality
                    case 1:
                        while(flag==1){
                            System.out.println("\nWhat operation do you want to perform?");
                            System.out.println("1. Add a new product\n2. Update details of an existing product\n3. View sorted customer details");
                            System.out.println("4. View current orders\n5. View sorted catalog\n6. Items ordered by different customers\n7.Exit");
                            choice = sc.nextInt();

                            switch (choice) {

                                //adding a new product
                                case 1:
                                    //taking user input
                                    sc.nextLine();
                                    System.out.println("Enter the product name: ");
                                    String productName = sc.nextLine();
                                    System.out.println("Enter the product price: ");
                                    String price = String.valueOf(sc.nextInt());
                                    System.out.println("Enter the weight of the item: ");
                                    String itemWeight = Float.toString(sc.nextFloat());
                                    System.out.println("Enter the quantity of the item: ");
                                    int quantity = sc.nextInt();

                                    //creating a new product
                                    new Product(productName, price, itemWeight, quantity);
                                    System.out.println("Product Added!");
                                    break; 
                                
                                //updating the details of a product
                                case 2:
                                    //displaying the products
                                    Product.displayProduct();
                                    System.out.println("Enter the Product ID: ");
                                    int prodId = sc.nextInt();
                                    System.out.println("Which field do you want to update?");
                                    System.out.println("1. Product Name\n2. Product Price\n3. Product Weight");
                                    int fieldId = sc.nextInt();
                                    
                                    //updating details
                                    Product.productDetails(prodId, fieldId);
                                    System.out.println("Product Details updated!");
                                    break;
                                
                                //viewing customer details
                                case 3:
                                    Customer.display();
                                    break;

                                //viewing order details
                                case 4:
                                    System.out.println("Current Orders");
                                    System.out.println("--------------------");
                                    Order.display();
                                    break;

                                //viewing the product catalog and the inventory
                                case 5:
                                    System.out.println("Product Catalog | Quantity");
                                        System.out.println("--------------------------------");
                                        for(Object obj: Product.catalog.keySet()){
                                            System.out.println(obj+" : "+Product.catalog.get(obj)+" | "+Product.inventory.get(obj));
                                        }
                                    break;

                                //viewing items ordered by the customer
                                case 6:
                                    System.out.println("\n\nItems ordered by the customers");
                                    System.out.println("---------------------------------");
                                    for(Object obj: Order.customerItems.keySet()){
                                        System.out.println(obj+" : "+Order.customerItems.get(obj));
                                    }
                                //exit
                                case 7:
                                    flag=0;
                                    break;
                            }
                    }
                    break;

                    //customer functionality
                    case 2:
                        while(flag==1){
                            System.out.println("\nWhat operation do you want to perform?");
                            System.out.println("1. Register\n2. Update details\n3. Place an order\n4. Track an order\n5. Exit");
                            temp = sc.nextInt();
                            switch (temp) {
                                //customer registration
                                case 1:
                                    sc.nextLine();
                                    System.out.println("Enter your name: ");
                                    String cstName = sc.nextLine();
                                    System.out.println("Phone Number: ");
                                    String phNo =  String.valueOf(sc.nextLong());
                                    sc.nextLine();
                                    System.out.println("E-mail ID: ");
                                    String mailId = sc.nextLine();
                                    System.out.println("Address: ");
                                    String add = sc.nextLine();
                                    //storing the customer details
                                    new Customer(cstName, phNo, mailId, add);
                                    break;
                                
                                //updating customer details
                                case 2:
                                    sc.nextLine();
                                    System.out.println("Enter your login number: ");
                                    int login = sc.nextInt();
                                    System.out.println("Which field do you want to update?");
                                    System.out.println("1. Name\n2. Phone Number\n3. E-mail ID\n4. Address");
                                    int field = sc.nextInt();
                                    Customer.updateDetails(login, field);
                                    break;

                                //placing an order
                                case 3:
                                    HashMap<Integer, Integer> items = new HashMap<>();
                                    System.out.println("Please enter your login ID: ");
                                    login = sc.nextInt();

                                    //taking input
                                    int moreItems = 1;
                                    while(moreItems==1){
                                        //printing out the products
                                        System.out.println("Product Catalog | Quantity");
                                        System.out.println("--------------------------------");
                                        for(Object obj: Product.catalog.keySet()){
                                            System.out.println(obj+" : "+Product.catalog.get(obj)+" | "+Product.inventory.get(obj));
                                        }
                                        System.out.println("Enter the product ID: ");
                                        int prodId = sc.nextInt();
                                        System.out.println("Enter the quantity");
                                        int quantity = sc.nextInt();
                                        items.put(prodId, quantity);
                                        System.out.println("Item added!");
                                        System.out.println("Do you want to order more items?");
                                        System.out.println("1. Yes\n2. No");
                                        int askUser = sc.nextInt();
                                        if (askUser==2)
                                            moreItems=0;
                                    }
                                    System.out.println("Order Placed!\n");
                                    //storing order details
                                    new Order(login, items);
                                    break;

                                //tracking an order
                                case 4:
                                    System.out.println("Please enter your login ID: ");
                                    login = sc.nextInt();
                                    //printing all orders placed by the customer
                                    for(Integer obj: Order.customerOrders.get(login)){
                                        System.out.println();
                                        Order.display(obj);
                                        System.out.println("\nStatus: ");
                                        Order.trackOrder(obj);
                                    }
                                    break;

                                case 5:
                                    flag = 0;
                                    break;
                            }
                        }
                        break;
                    
                    //exit
                    case 3:
                        outerFlag = 0;
                        break;

                    //invalid input
                    default:
                        System.out.println("Invalid Choice, please try again!\n");
                        break;
                }
        }
    }
}
