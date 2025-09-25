import java.util.Arrays;
public class Main
{
    public static int[] matrixSearch(int [][] arr, int key){
        int rows = arr.length;
        int cols = arr[0].length;
        int low = 0;
        int high = rows * cols - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int midRow = mid / cols;
            int midCol = mid % cols;

            if (arr[midRow][midCol] == key) {
                return new int[]{midRow, midCol}; // AI suggestion to optimize the algorithm
            } else if (arr[midRow][midCol] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new int[]{-1, -1}; // AI helped me fix the return error
    }
	public static void main(String[] args) {
			int[][]	arr = {{1, 2, 3, 4},
                            {5, 6, 7, 8},
                            {9, 10, 11, 12}};
            int key = 7;
            int[] result = matrixSearch(arr, key);
            System.out.println(Arrays.toString(result));
	}
}
