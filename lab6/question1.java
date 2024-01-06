import java.util.*;

public class question1 extends Thread{
    public static void main(String[] args) throws InterruptedException{
        //taking user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of coins: ");
        int n = sc.nextInt();
        int[] coins = new int[n];
        System.out.println("What should be the sum?");
        int sum = sc.nextInt();

        //creating the first thread
        Thread t1 = new Thread(()->{
            synchronized(coins)
                {
                    //taking user input
                    for(int i=0; i<n; i++){
                        System.out.println("Enter the denomination of coin:"+i);
                        coins[i]=sc.nextInt();
                    }
                }
        });

        //creating the second thread
        Thread t2 = new Thread(()->{
            synchronized(coins)
            {
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
                System.out.println("There are "+ways[sum]+" possible ways!");
            }
        });

        //starting the threads
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
