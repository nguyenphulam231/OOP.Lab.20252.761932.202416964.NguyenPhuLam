package exercise6_6;
import java.util.Scanner;

public class MatrixAdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Use constant matrices");
        System.out.println("2. Enter matrices from keyboard");
        System.out.print("Your choice: ");
        int choice = sc.nextInt();

        int rows, cols;
        int[][] matrix1, matrix2;

        if (choice == 1) {
            rows = 2;
            cols = 2;
            matrix1 = new int[][]{{1, 2}, {3, 4}};
            matrix2 = new int[][]{{5, 6}, {7, 8}};
        } else {
            System.out.print("Number of rows: ");
            rows = sc.nextInt();
            System.out.print("Number of columns: ");
            cols = sc.nextInt();

            matrix1 = new int[rows][cols];
            matrix2 = new int[rows][cols];

            System.out.println("Enter matrix 1:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.printf("matrix1[%d][%d] = ", i, j);
                    matrix1[i][j] = sc.nextInt();
                }
            }

            System.out.println("Enter matrix 2:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.printf("matrix2[%d][%d] = ", i, j);
                    matrix2[i][j] = sc.nextInt();
                }
            }
        }

        int[][] sumMatrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sumMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        System.out.println("\nSum matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(sumMatrix[i][j] + "\t");
            }
            System.out.println();
        }

        sc.close();
    }
}