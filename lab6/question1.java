import java.util.*;

public class question1 extends Thread{

    static int calculate(int sum, int[] coins){
        int n = coins.length;

        //array to store the number of ways to achieve each sum
        int[] ways = new int[sum + 1];
        //there is only one way to have sum=0
        ways[0] = 1;

        //iterating through the coin denominations
        for (int coin : coins) {
            //iterating through possible sums
            for (int j = coin; j <= sum; j++) {
                //adding ways to make the remaining sum (j-coin)
                ways[j] += ways[j - coin];
            }
        }
        //returning the number of ways to get the final sum
        return ways[sum];
    }

    static void input(){
        //taking user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of coins: ");
        int n = sc.nextInt();
        int[] coins = new int[n];

        for(int i=0; i<n; i++){
            System.out.println("Enter the denomination of coin:"+i);
            coins[i]=sc.nextInt();
        }

        System.out.println("What should be the sum?");
        int sum = sc.nextInt();

        System.out.println("There are "+calculate(sum, coins)+" possible ways!");
    }

    public static void main(String[] args) {
        //implementing threading
        Thread t1 = new Thread();
        Thread t2 = new Thread();
    }
}