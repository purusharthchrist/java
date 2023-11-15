import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

class AlphabetWarGame{
    String fight_str;
    String l_str;
    String r_str;
    int al_strength;

    AlphabetWarGame(String str, int strength){
        this.fight_str = str;
        this.al_strength = strength;
        //call `winner()` method for default strengths
        if(strength==1){
            int mid = str.length()/2;
            String result = this.winner(str.substring(0, mid), str.substring(mid));
            System.out.println(result);
        }
        //call `winner()` method for custom strenghts
        else{
            int mid = str.length()/2;
            String result = this.winner(str.substring(0, mid), str.substring(mid), 0);
            System.out.println(result);
        }
    }

    AlphabetWarGame(String left_str, String  right_str, int strength){
        this.l_str = left_str;
        this.r_str = right_str;
        this.al_strength = strength;
        //call `winner()` method for default strengths
        if(strength==1){
            String result = this.winner(left_str, right_str);
            System.out.println(result);
        }
        //call `winner()` method for custom strenghts
        else{
            String result = this.winner(left_str, right_str, 0);
            System.out.println(result);
        }
    }

    //method that returns the result for default strengths
    String winner(String left_str, String right_str){
        Map<String, Integer> l_map = new HashMap<>();
        l_map.put("w", 4);
        l_map.put("p", 3);
        l_map.put("b", 2);
        l_map.put("s", 1);

        Map<String, Integer> r_map = new HashMap<>();
        r_map.put("m", 4);
        r_map.put("q", 3);
        r_map.put("d", 2);
        r_map.put("z", 1);

        //initializing variables for strengths
        int l_strength = 0;
        int r_strength = 0;

        //create array of strings to be iterated through
        String[] left_array = left_str.split("");
        String[] right_array = right_str.split("");

        //calculating the left and right strengths
        for (String i: left_array){
            if(l_map.containsKey(i)){
                l_strength += l_map.get(i);
            }
        }
        for (String i: right_array){
            if(r_map.containsKey(i)){
                r_strength += r_map.get(i);
            }
        }

        //check to see which side wins
        if(r_strength>l_strength){
            return "Right side wins!";
        }
        else if(l_strength>r_strength){
            return "Left side wins!";
        }
        return "Let's fight again!";
    }

    //method that returns the result for custom strengths
    String winner(String left_str, String right_str, int strength){
        //alphabets that will have strenghts
        Scanner sc = new Scanner(System.in);
        String temp;
        //taking input for the left string
        System.out.println("Enter 4 alphabets that will have strength on the left side followed by their strength: ");
        System.out.println("Example: ");
        System.out.println("a");
        System.out.println("4");
        System.out.println("Name your warriors!");
        //creating a hashmap that will store the string strengths
        Map<String, Integer> l_map = new HashMap<>();
        for(int i=0;i<4;i++){
            temp = sc.next();
            //adding the string along with its value to the left map
            l_map.put(temp, sc.nextInt());
        }

        //taking input for the right string
        System.out.println("Enter 4 alphabets that will have strength on the right side followed by their strength: ");
        System.out.println("Example: ");
        System.out.println("a");
        System.out.println("4");
        System.out.println("Name your warriors!");
        //creating a hashmap that will store the string strengths
        Map<String, Integer> r_map = new HashMap<>();
        for(int i=0;i<4;i++){
            temp = sc.next();
            //adding the string along with its value to the right map
            r_map.put(temp, sc.nextInt());
        }

        //initializing variables for strengths
        int l_strength = 0;
        int r_strength = 0;

        //create array of strings to be iterated through
        String[] left_array = left_str.split("");
        String[] right_array = right_str.split("");

        //calculating the left and right strengths
        for (String i: left_array){
            if(l_map.containsKey(i)){
                l_strength += l_map.get(i);
            }
        }
        for (String i: right_array){
            if(r_map.containsKey(i)){
                r_strength += r_map.get(i);
            }
        }

        //check to see which side wins
        if(r_strength>l_strength){
            return "Right side wins!";
        }
        else if(l_strength>r_strength){
            return "Left side wins!";
        }
        return "Let's fight again!";
    }
}

//driver code
class lab1_program2{
    public static void main(String[] args){
        //take input from the user
        Scanner in = new Scanner(System.in);
        //initialize variables to store user input
        int res = -1;
        int res_strength = -1;

        System.out.println("Which operation would you like to proceed with (1 or 2)?");
        System.out.println("1 - A string");
        System.out.println("2 - Left and right strings separately\n");

        //wait for a valid input
        do{
            res = in.nextInt();
            if(res!=1 && res!=2){
                System.out.println("Please enter a valid choice (Either 1 or 2): ");
            }
        } while(res != 1 && res != 2);

        //give user the option to customize strength
        System.out.println("Do you want to use the default strengths or customize them (1 or 2)?");
        System.out.println("1 - Default strength");
        System.out.println("2 - Customized strength");
        //wait for a valid input
        do{
            res_strength = in.nextInt();
            if(res_strength!=1 && res_strength!=2){
                System.out.println("Please enter a valid choice (Either 1 or 2): ");
            }
        } while(res_strength != 1 && res_strength != 2);
        //create switch cases for different inputs to `res`
        switch(res){
            //when user wants to enter a string
            case 1:
                System.out.println("Enter your string: ");
                String fight = in.next();
                AlphabetWarGame obj = new AlphabetWarGame(fight, res_strength);
                break;
            //when user wants to give the left and right strings
            case 2:
                System.out.println("Enter the left string: ");
                String left = in.next();
                System.out.println("Enter the right string: ");
                String right = in.next();
                AlphabetWarGame obj_2 = new AlphabetWarGame(left, right, res_strength);
                break;
        }
    }
}