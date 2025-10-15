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
        return numbersArray;
    }
    public static int partition(int[] numbers, int lowIndex, int highIndex) {
   // Pick middle element as the pivot
   int midpoint = lowIndex + (highIndex - lowIndex) / 2;
   int pivot = numbers[midpoint];
   
   boolean done = false;
   while (!done) {
      // Increment lowIndex while numbers[lowIndex] < pivot
      while (numbers[lowIndex] < pivot) {
         lowIndex++;
      }
      
      // Decrement highIndex while pivot < numbers[highIndex]
      while (pivot < numbers[highIndex]) {
         highIndex--;
      }
      
      // If lowIndex and highIndex have met or crossed each other, then
      // partitioning is done
      if (lowIndex >= highIndex) {
         done = true;
      }
      else {
         // Swap array elements at lowIndex and highIndex
         int temp = numbers[lowIndex];
         numbers[lowIndex] = numbers[highIndex];
         numbers[highIndex] = temp;

         // Update lowIndex and highIndex
         lowIndex++;
         highIndex--;
      }
   }

   // highIndex is the last index in the left partition
   return highIndex;
}
public static void quicksort(int[] numbers, int startIndex, int endIndex) {
   // Only attempt to sort the array segment if there are
   // at least 2 elements
   if (endIndex <= startIndex) {
      return;
   }
          
   // Partition the array segment
   int high = partition(numbers, startIndex, endIndex);

   // Recursively sort the left segment
   quicksort(numbers, startIndex, high);

   // Recursively sort the right segment
   quicksort(numbers, high + 1, endIndex);
}
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
	    int start = 20;
	    int end = 40;
	    int total = 10;
	    int startIndex = 0;
	    int endIndex = 9;
	int[] unSorted = randomUnique(start, end, total);
    ArrayDuplicator duplicator = new ArrayDuplicator();
    ArrayPair duplicated = duplicator.duplicateAndReturnBoth(unSorted);
    long start1 = System.currentTimeMillis();
    long start2 = System.nanoTime();
    ShellSort2(duplicated.duplicate);
    long finish1 = System.currentTimeMillis();;
    long finish2 = System.nanoTime();
    long start3 = System.currentTimeMillis();
    long start4 = System.nanoTime();
    quicksort(unSorted, startIndex, endIndex);
    long finish3 = System.currentTimeMillis();;
    long finish4 = System.nanoTime();
long timeElapsed1 = finish1 - start1;
long timeElapsed2 = finish2 - start2;
long timeElapsed3 = finish3 - start3;
long timeElapsed4 = finish4 - start4;
System.out.println("shellSort");
System.out.println("Milliseconds:" + timeElapsed1);
System.out.println("Nanoseconds:" + timeElapsed2);
System.out.println("quickSort");
System.out.println("Milliseconds:" + timeElapsed3);
System.out.println("Nanoseconds:" + timeElapsed4);
	}
}
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
