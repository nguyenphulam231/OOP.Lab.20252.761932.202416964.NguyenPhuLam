package exercise6_5;

import java.util.Arrays;
import java.util.Scanner;

public class SortSumAvg {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr;

        System.out.println("Options");
        System.out.println("1. Constant array");
        System.out.println("2. Enter array from keyboard");
        System.out.print("Your choice (1/2): ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            arr = new int[]{1789, 2035, 1899, 1456, 2013};
            System.out.println("You chose constant array");
        } else {
            System.out.print("Member number of the array ");
            int n = scanner.nextInt();
            arr = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("i-mem " + i + ": ");
                arr[i] = scanner.nextInt();
            }
        }
        
        System.out.println("\nYour array " + Arrays.toString(arr));

        Arrays.sort(arr);
        System.out.println("Sorted array " + Arrays.toString(arr));

        int sum = 0;
        for (int value : arr) {
            sum += value;
        }
        double average = (double) sum / arr.length;

        System.out.println("----------------------");
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        
        scanner.close(); 
    }
}