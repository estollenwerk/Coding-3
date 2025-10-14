import java.util.Arrays;
import java.util.Random;
public class Main
{
    // creates a random array
     public static int[] randomUnique(int start, int end, int total){ 
        //creating an array of size total that has random numbers added into it between the range of start and end
        Random random = new Random();
        int[] numbersArray = new int[total];
        for(int i = 0; i < total; i++){
            numbersArray[i] = random.nextInt(end - start + 1) + start;
        }
        return numbersArray;
    }
    // This is the insertionSort we are using to measure against shellSort
    public static void insertionSort2(int[] numbers) {
      for (int i = 1; i < numbers.length; i++) {
         int j = i;
         while (j > 0 && numbers[j] < numbers[j - 1]) {
            // Swap numbers[j] and numbers [j - 1]
            int temp = numbers[j];
            numbers[j] = numbers[j - 1];
            numbers[j - 1] = temp;
            j--;
         }
      }
    }
    // This is the insertionSort used inside of shellSort
    public static void insertionSort(int[] numbers, int startIndex, int gap) {
   for (int i = startIndex + gap; i < numbers.length; i += gap) {
      int j = i;
      while (j - gap >= startIndex && numbers[j] < numbers[j - gap]) {
         // Swap numbers[j] and numbers [j - gap]
         int temp = numbers[j];
         numbers[j] = numbers[j - gap];
         numbers[j - gap] = temp;
         j -= gap;
      }
   }
   //This is the shellSort that finds the number of partitions as well as the gap values
   //stores them in an array to be used
}
public static int[] ShellSort2(int[] arr){
    int j = 0;
    for(int i = 0; i < arr.length; i++){
        if ((int)Math.pow(2, i - 1) < arr.length){
            j++;
        }
    }

    int[] gapArray = new int[j];
    for(int k = 0; k < gapArray.length; k++){
        gapArray[k] = (int)Math.pow(2, j - 1);
        j--;
    }

    return ShellSort(arr, gapArray);
}
// This is the shellSort that actually sorts the array
public static int[] ShellSort(int[] arr, int[] intervals){
        for (int g = 0; g < intervals.length; g++) {
      for (int i = 0; i < intervals[g]; i++) {
         insertionSort(arr, i, intervals[g]);
      }
   }
   return arr;
    }
    
public static void main(String[] args) {
    int start = 10;
    int end = 40;
    int total = 10;

    int[] unSorted = randomUnique(start, end, total);
    ArrayDuplicator duplicator = new ArrayDuplicator();
    ArrayPair duplicated = duplicator.duplicateAndReturnBoth(unSorted);
        long start1 = System.currentTimeMillis();
        long start2 = System.nanoTime();
    int[] sorted = ShellSort2(unSorted);
        long finish1 = System.currentTimeMillis();;
        long finish2 = System.nanoTime();
        long start3 = System.currentTimeMillis();
        long start4 = System.nanoTime();
    insertionSort2(duplicated.duplicate); //
        long finish3 = System.currentTimeMillis();;
        long finish4 = System.nanoTime();
long timeElapsed1 = finish1 - start1;
long timeElapsed2 = finish2 - start2;
long timeElapsed3 = finish3 - start3;
long timeElapsed4 = finish4 - start4;

    System.out.println("Sorted shellSort: ");
    System.out.println(Arrays.toString(sorted));
    System.out.println("Time in milliseconds: " + timeElapsed1);
    System.out.println("Time in nanoseconds: " + timeElapsed2);

    System.out.println("Sorted selectionSort: ");
    System.out.println(Arrays.toString(duplicated.duplicate));
    System.out.println("Time in milliseconds: " + timeElapsed3);
    System.out.println("Time in nanoseconds: " + timeElapsed4);
}
}
//These two classes are used to duplicate an array and return both the original array and the duplicate
class ArrayPair {
    public int[] original;
    public int[] duplicate;

    public ArrayPair(int[] original, int[] duplicate) {
        this.original = original;
        this.duplicate = duplicate;
    }
}

class ArrayDuplicator {
    public ArrayPair duplicateAndReturnBoth(int[] input) {
        int[] copy = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            copy[i] = input[i];
        }
        return new ArrayPair(input, copy);
    }
}
