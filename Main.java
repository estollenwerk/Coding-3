import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static int[] randomUnique(int start, int end, int total) { // used AI to find a working randomUnique
        Random random = new Random();
        int[] numbersArray = new int[total];
        Set<Integer> used = new HashSet<>();

        for (int i = 0; i < total; i++) {
            int num;
            do {
                num = random.nextInt(end - start + 1) + start;
            } while (used.contains(num));
            numbersArray[i] = num;
            used.add(num);
        }

        return numbersArray;
    }

    public static void bubbleSort(int[] arr) { //Used AI to make a bubble sort method for me
        if (arr == null || arr.length < 2) return;

        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }

    public static void selectionSort(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            int indexSmallest = i;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] < numbers[indexSmallest]) {
                    indexSmallest = j;
                }
            }

            int temp = numbers[i];
            numbers[i] = numbers[indexSmallest];
            numbers[indexSmallest] = temp;
        }
    }

    public static void main(String[] args) {
        int beginning = 100;
        int after = 300;
        int total = 100;

        int[] original = randomUnique(beginning, after, total);
        ArrayDuplicator duplicator = new ArrayDuplicator();
        ArrayPair duplicated = duplicator.duplicateAndReturnBoth(original);
        long start1 = System.currentTimeMillis();
        long start2 = System.nanoTime();
        selectionSort(original);
        long finish1 = System.currentTimeMillis();
        long finish2 = System.nanoTime();
        long start3 = System.currentTimeMillis();
        long start4 = System.nanoTime();
        bubbleSort(duplicated.duplicate);
        long finish3 = System.currentTimeMillis();;
        long finish4 = System.nanoTime();
long timeElapsed1 = finish1 - start1;
long timeElapsed2 = finish2 - start2;
long timeElapsed3 = finish3 - start3;
long timeElapsed4 = finish4 - start4;
    System.out.println(timeElapsed1);
    System.out.println(timeElapsed2);
    System.out.println(timeElapsed3);
    System.out.println(timeElapsed4);
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
