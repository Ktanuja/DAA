import java.util.Scanner;

public class NQueensBacktracking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the chessboard (N): ");
        int n = scanner.nextInt();
        scanner.close();

        long startTime = System.nanoTime();
        long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        solveNQueens(n);

        long endTime = System.nanoTime();
        long endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long executionTime = endTime - startTime;
        long memoryUsed = endMemory - startMemory;

        System.out.println("Time taken (ns): " + executionTime);
        System.out.println("Space used (bytes): " + memoryUsed);
    }

    public static void solveNQueens(int n) {
        int[] queens = new int[n];
        placeQueens(queens, 0, n);
    }

    public static void placeQueens(int[] queens, int row, int n) {
        if (row == n) {
            printSolution(queens);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(queens, row, col)) {
                queens[row] = col;
                placeQueens(queens, row + 1, n);
            }
        }
    }

    public static boolean isSafe(int[] queens, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queens[i] == col || queens[i] - i == col - row || queens[i] + i == col + row) {
                return false;
            }
        }
        return true;
    }

    public static void printSolution(int[] queens) {
        int n = queens.length;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (queens[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
