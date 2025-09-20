import java.util.Arrays;
public class Main
{
    public static int BinarySearch(int[] numbers, int numbersSize, int key) { // classic Binary search
   int mid = 0;
   int low = 0;
   int high = numbersSize - 1;
   
   while (high >= low) {
      mid = (high + low) / 2;
      if (numbers[mid] < key) {
         low = mid + 1;
      }
      else if (numbers[mid] > key) {
         high = mid - 1;
      }
      else {
         return numbers[mid];
      }
   }
   
   return -1;
}
public static int MatrixSearch(int[][] arr, int key){ //changed this to return an int instead of an array because that makes more sense
 // Step 1: Flatten the matrix into a 1D array
        int rows = arr.length;
        int cols = arr[0].length;
        int[] flatArray = new int[rows * cols]; //makes an array with the number of elements in the matrix
        int index = 0;

        for (int[] row : arr) { //advanced form of for loop, loops through the row part of the matrix
            for (int element : row) {
                flatArray[index++] = element;
            }
        };
        // Step 2: Sort the 1D array
        Arrays.sort(flatArray);
        return BinarySearch(flatArray, index, key);
}

	public static void main(String[] args) {
	int[][]	arr = {{1,  2, 3, 4},
                    {5,  6, 7, 8},
                    {9, 10,11,12}};
    int key = 6;
    int result = MatrixSearch(arr, key);
    System.out.println(result);
	}
}
