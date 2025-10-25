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
public static void Merge(int[] numbers,int i, int j, int k) {
   int mergedSize = k - i + 1;                // Size of merged partition
   int mergePos = 0;                          // Position to insert merged number
   int leftPos = 0;                           // Position of elements in left partition
   int rightPos = 0;                          // Position of elements in right partition
   int[] mergedNumbers = new int[mergedSize];   // Dynamically allocates temporary array
                                         // for merged numbers
   
   leftPos = i;                           // Initialize left partition position
   rightPos = j + 1;                      // Initialize right partition position
   
   // Add smallest element from left or right partition to merged numbers
   while (leftPos <= j && rightPos <= k) {
      if (numbers[leftPos] <= numbers[rightPos]) {
         mergedNumbers[mergePos] = numbers[leftPos];
         leftPos++;
      }
      else {
         mergedNumbers[mergePos] = numbers[rightPos];
         rightPos++;
         
      }
      mergePos++;
   }
   
   // If left partition is not empty, add remaining elements to merged numbers
   while (leftPos <= j) {
      mergedNumbers[mergePos] = numbers[leftPos];
      leftPos++;
      mergePos++;
   }
   
   // If right partition is not empty, add remaining elements to merged numbers
   while (rightPos <= k) {
      mergedNumbers[mergePos] = numbers[rightPos];
      rightPos++;
      mergePos++;
   }
   
   // Copy merge number back to numbers
   for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
      numbers[i + mergePos] = mergedNumbers[mergePos];
   }
}

public static int[] MergeSort(int[] numbers, int i, int k){
   int j = 0;
   if (i < k) {
      j = (i + k) / 2;  // Find the midpoint in the partition
      
      // Recursively sort left and right partitions
      MergeSort(numbers, i, j);
      MergeSort(numbers, j + 1, k);
      
      // Merge left and right partition in sorted order
      Merge(numbers, i, j, k);
      
   }
   return numbers;
}
	public static void main(String[] args) {
	    int start = 20;
	    int end = 500;
	    int total = 100000;
	int[] unSorted = randomUnique(start, end, total);
	int i = 0;
	int k = unSorted.length - 1;
	long start1 = System.currentTimeMillis();
    long start2 = System.nanoTime();
    int[] sorted = MergeSort(unSorted, i, k);
    long finish1 = System.currentTimeMillis();;
    long finish2 = System.nanoTime();
    long timeElapsed1 = finish1 - start1;
    long timeElapsed2 = finish2 - start2;
    System.out.println("Milliseconds: " + timeElapsed1);
    System.out.println("Nanoseconds: " + timeElapsed2);
    for(int j = 0; j < sorted.length; j++){
    System.out.print(sorted[j] + " ");
}
System.out.println(); // Add newline after printing
	}
}
