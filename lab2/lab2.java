import java.util.*;

class lab2{

    static List<Integer> arr = new ArrayList<Integer>();
    static int k;

    //constructor
    lab2(){
        this.readNums();
    }
    
    //method to read n numbers
    void readNums(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your array elements with spaces as delimiters: ");
        String[] temp = sc.nextLine().split(" ");
        for(int i=0;i<temp.length;i++){
            arr.add(Integer.parseInt(temp[i]));
        }
        System.out.println("Enter the value of K: ");
        k = sc.nextInt();
    }
    
    //method to return the mode value
    static void getMaxFreq(){
        //initialize a hashmap to store the count of each unique value
        Map<Integer, Integer> dict = new HashMap<>();
        for(int i: arr){
            //check to see if the element is currently in the keys of the map
            if(!dict.containsKey(i)){
                dict.put(i, 1);
            }
            else{
                //incrementing the count by 1
                dict.put(i, dict.get(i)+1);
            }
        }
         
        //sorting the hashmap based on values
        List<Map.Entry<Integer, Integer>> list  = new LinkedList<>(dict.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>(){
            public int compare(Map.Entry<Integer, Integer> val1,
                               Map.Entry<Integer, Integer> val2)
                               {
                                return (val1.getValue()).compareTo(val2.getValue());
                               }
        });

        //creating a new hashmap with sorted values
        Map<Integer, Integer> new_dict = new LinkedHashMap<>();
        for (int i=list.size()-1;i>0;i--){
            Map.Entry<Integer, Integer> temp = list.get(i);
            new_dict.put(temp.getKey(), temp.getValue());
        }

        //printing the output
        System.out.println(k+" numbers with maximum frequency in the given array are: ");
        int counter = -1;
        for(int i: new_dict.keySet()){
            counter++;
            if(counter>=k){
                break;
            }
            System.out.print(i+" ");
        }
    }

    public static void main(String[] args){
        lab2 obj = new lab2();
        obj.getMaxFreq();
    }
}