import java.util.Scanner;

//abstract class
abstract class Robber{
    void RobbingClass(){
        System.out.println("MScAI&ML");
    }

    //constructor
    Robber(){
        System.out.println("I love MachineLearning.");
    }

    //abstract methods for different cases
    abstract int RowHouses(int loot[]);
    abstract int RoundHouses(int loot[]);
    abstract int SquareHouse(int loot[]);
    abstract int MultiHouseBuilding(int loot[]);
}


class JAVAProfessionalRobber extends Robber {

    //method for robbing row houses
    int RowHouses(int loot[]) {
        int[] money = new int[3];
        money[0] = loot[0] + loot[2]; //combine 1st and 3rd
        money[1] = loot[1] + loot[3]; //combine 2nd and 4th
        money[2] = loot[0] + loot[3]; //combine 1st and 4th
        
        int total = 0;
        for (int i = 0; i < 3; i++) {
            if (total < money[i]) {
                total = money[i];
            }
        }
        return total;
    }

    //method for robbing round houses
    int RoundHouses(int loot[]) {
        int left = 0;
        int right = 0;
        
        for (int i = 0; i < loot.length; i++) {
            if (i % 2 == 0) {
                left += loot[i]; //combine even positions
            } else {
                right += loot[i]; //combine odd positions
            }
        }
        return Math.max(left, right);
    }

    //method for robbing square houses
    int SquareHouse(int loot[]) {
        int left = 0;
        int right = 0;
        
        for (int i = 0; i < loot.length; i++) {
            if (i % 2 == 0) {
                left += loot[i]; //combine even positions
            } else {
                right += loot[i]; //combine odd positions
            }
        }
        return Math.max(left, right);
    }

    //method for robbing multi houses
    int MultiHouseBuilding(int loot[]) {
        int[] money = new int[4];
        money[0] = loot[0] + loot[3]; //combine 1st and 4th
        money[1] = loot[0] + loot[2] + loot[4]; //combine odd positions
        money[2] = loot[1] + loot[3] + loot[5]; //combine even positions
        money[3] = loot[2] + loot[5]; //combine 3rd and 6th
        
        int total = 0;
        for (int i = 0; i < 4; i++) {
            if (total < money[i]) {
                total = money[i];
            }
        }
        return total;
    }
}

//driver code
class lab4{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        int maxProfit = 0;
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();
        int[] lootArray = new int[6];
        
        do{
            System.out.println("Select a target property:\n1.Row Houses\n2.Round Houses\n3.Square Houses\n4.Multi House Building");
            int houseType = sc.nextInt();

            //getting loot amounts for the selected house type
            for (int i = 0; i < 4; i++) {
                System.out.println("Enter the loot in house " + (i + 1));
                lootArray[i] = sc.nextInt();
            }

            //executing robbery based on user choice
            switch (houseType){
                case 1:
                    maxProfit = robber.RowHouses(lootArray);
                    break;
                case 2:
                    maxProfit = robber.RoundHouses(lootArray);
                    break;
                case 3:
                    maxProfit = robber.SquareHouse(lootArray);
                    break;
                case 4:
                    maxProfit = robber.MultiHouseBuilding(lootArray);
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
            System.out.println("Profit = " + maxProfit);

            // Check if the user wants to commit another robbery
            System.out.println("Do you want to commit another robbery?\n1.Yes\n2.No");
            choice = sc.nextInt();
        } while (choice != 2);

        System.out.println("Robbing is illegal, you know?");
        System.out.println("ok byeeeee :)");
        sc.close();
    }
}
