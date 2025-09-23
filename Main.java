import java.util.Arrays;
import java.util.Random;
public class Main
{
    public static int[] randomUnique(int start, int end, int total){
        //creating an array of size total that has random numbers added into it between the range of start and end
        Random random = new Random();
        int[] numbersArray = new int[total];
        for(int i = 0; i < total; i++){
            numbersArray[i] = random.nextInt(end - start + 1) + start;
        }
        //checking for duplicate and replacing it with a new number if it is duplicate
        for(int j = 0; j < numbersArray.length; j++){ 
            for(int k = j + 1; k < numbersArray.length; k++){
                if(numbersArray[j] == numbersArray[k]){
                    numbersArray[j] = random.nextInt(end - start + 1) + start;
                }
            }
        }
        return numbersArray;
    }
    //scaning array for the target numer and returning true if it is in there and false if it isnt
    public static boolean scan(int[] input, int target){
        for(int i = 0; i < input.length; i++){
            if(input[i] == target){
                return true;
            }
        }
        return false;
    }
    public static boolean stor(int[] input, int target) {
    int largest = input[0];
        for (int i = 1; i < input.length; i++) {
        if (input[i] > largest) {
            largest = input[i];
        }
        }

    int[] bigArray = new int[largest + 1];
        for (int j = 0; j < input.length; j++) {
        bigArray[input[j]] = 1;
        }

        if (target >= 0 && target <= largest) {
        return bigArray[target] == 1;
        }
    return false;
}
   
	public static void main(String[] args) {
	    int begining = 1;
	    int ending = 50;
	    int group = 6;
	    int tracker = 12;
	    int[] result = randomUnique(begining, ending, group);
		System.out.println(Arrays.toString(result));
		boolean hello = scan(result, tracker);
		System.out.println(hello);
		boolean goodbye = stor(result, 9);
		System.out.println(goodbye);
	}
}
