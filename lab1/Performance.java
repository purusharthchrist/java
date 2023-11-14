//importing the necessary class
import java.util.*;

//creating the performance class
class Performance{
    
    //initializing the variables
    int[] mark = new int[60];
    //class constructor
    Performance(){
        //read the marks
        this.readMarks();
        //call the display function
        this.display();
    }

    //method to read the marks of students
    void readMarks(){
        //creating a variable to store the user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the marks of 10 students(between 0 and 100): ");
        
        //looping over the input data
        for(int i=0; i<10; i++){
            do{
                //storing the marks in `mark` array
                this.mark[i] = sc.nextInt();

                //checking to see if the given conditions are satisfied
                if (this.mark[i]<0 || this.mark[i]>100){
                    System.out.println("Please enter the marks between 0 and 100: ");
                }
            
            //if the given conditions are not satisfied, we need to run the loop again
            } while (this.mark[i]<0 || this.mark[i]>100);
        }
    }

    //method to return the highest mark scored
    int highestMark(){
        //creating a variable to store the highest marks and storing the first element of the array in it
        int highest = this.mark[0];

        for(int i=1; i<10; i++){
            //checking to see whether the current element is greater than the highest value(yet)
            if(this.mark[i]>highest){
                highest = this.mark[i];
            }
        }
        return highest;
    }


    //method to return the lowest mark scored
    int leastMark(){
        //this will follow a similar structure to `highestMark()`
        int lowest = this.mark[0];

        for(int i=1; i<10; i++){
            if(this.mark[i]<lowest){
                lowest = this.mark[i];
            }
        }
        return lowest;
    }

    //method to return the mode value
    List<Integer> getMode(){
        //initialize a list to store the mode values
        List<Integer> modes = new ArrayList<>();
        //initialize a variable to store the maximum count
        int max_count = 0;
        //initialize a hashmap to store the marks along with their count
        Map<Integer, Integer> dict = new HashMap<>();
        //initialize a counter for breaking the loop
        int counter = 0;

        for(int i: this.mark){
            //check to see if the element is currently in the keys of the map
            if(!dict.containsKey(i)){
                dict.put(i, 1);
            }
            else{
                //incrementing the count by 1
                dict.put(i, dict.get(i)+1);
            }

            //update max_count if the current count is greater
            max_count = Math.max(max_count, dict.get(i));

            //check and increment the counter
            if(counter>10){
                break;
            }
            counter++;
        }
        
        //add all keys with count equal to max_count to the list
        for(Map.Entry<Integer, Integer> i: dict.entrySet()){
            if(i.getValue() == max_count){
                modes.add(i.getKey());
            }
        }
        return modes;
    }

    //method to return the count of the mode value
    int getfreqMode(){
        //initialize a variable to store the maximum count
        int max_count = 0;
        //initialize a hashmap to store the marks along with their count
        Map<Integer, Integer> dict = new HashMap<>();
        //initialize a counter for breaking the loop
        int counter = 0;
        
        for(int i: this.mark){
            //check to see if the element is currently in the keys of the map
            if(!dict.containsKey(i)){
                dict.put(i, 1);
            }
            else{
                //incrementing the count by 1
                dict.put(i, dict.get(i)+1);
            }

            //update max_count if the current count is greater
            if(dict.get(i) > max_count){
                max_count = dict.get(i);
            }

            //check and increment the counter
            if(counter>10){
                break;
            }
            counter++;
        }

        return max_count;
    }

    //method to display all the values
    void display(){
        int highest_marks = this.highestMark();
        int lowest_marks = this.leastMark();
        List<Integer> mode = this.getMode();
        int mode_val = this.getfreqMode();
        System.out.println("Highest Mark: "+highest_marks);
        System.out.println("Lowest Mark: "+lowest_marks);
        System.out.println("Mode Values: "+mode);
        System.out.println("Count of mode value: "+mode_val);
    }
}

//lab1 class
class lab1{
    //main function
    public static void main(String[] args){
        //instantiate an object of the Performace class
        new Performance();
    }
}