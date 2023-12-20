import java.util.Scanner;
import java.util.Arrays;

interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}

abstract class RainySeasonConservation implements WaterConservationSystem{
    public abstract int calculateTrappedWater(int[] blockHeights);
}

class CityBlockConservation extends RainySeasonConservation{
    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        int start=0;
        int end=blockHeights.length;
        int totalWater=0;
        //getting the start index
        for (int i:blockHeights){
            if(i==0)
                start += 1;
            else
                break;
        }
        
        //getting the end index
        for (int i = blockHeights.length-1;i>=0;i--){
            if(blockHeights[i]==0)
                end -= 1;
            else
                break;
        }

        //find the index with the maximum height
        int maxIdx = 0;
        for (int i = 0; i < blockHeights.length; i++) {
            if (blockHeights[i] > blockHeights[maxIdx])
                maxIdx = i;
        }

        //array that has buildings from start to max
        int max = blockHeights[start];
        int[] allBuildingsLeft = Arrays.copyOfRange(blockHeights, start+1, maxIdx);

        //calculating the total trapped water from the left
        for (int i:allBuildingsLeft){
            if (i<max){
                totalWater += max-i;
            }
            else{
                max=i;
            }
        }

        //array that has buildings from max to end
        int[] allBuildingsRight = Arrays.copyOfRange(blockHeights, max+1, end);
        max = allBuildingsRight[allBuildingsRight.length-1];
        
        //calculating the total trapped water from the right
        for (int i=allBuildingsRight.length-2;i>=0;i--){
            if (allBuildingsRight[i]<max){
                totalWater += max-i;
            }
            else{
                max=i;
            }
        }

        return totalWater;
    }
}

public class question2{
    public static void main(String[] args) {
        //take user input
        Scanner sc = new Scanner(System.in);
        System.out.println("How many buildings are there in the city?");
        int n_buildings = sc.nextInt();
        sc.nextLine();
        int[] buildings = new int[n_buildings];
        System.out.println("Enter the heights of the buildings (in integer):");
        for (int i=0;i<n_buildings;i++){
            buildings[i] = sc.nextInt();
        }
        //calculating the water that can be conserved
        CityBlockConservation obj = new CityBlockConservation();
        int waterConserved = obj.calculateTrappedWater(buildings);

        //display the amount of water saved
        System.out.println("You can conserve "+waterConserved+" units of water!");
        sc.close();
    }
}