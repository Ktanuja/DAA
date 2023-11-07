import java.util.Scanner;

public class FibonacciCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to calculate the Fibonacci series: ");
        int n = scanner.nextInt();
        scanner.close();

        long startTime, endTime;
        long startMemory, endMemory;

        // Calculate and print Fibonacci series (Recursive)
        System.out.println("Fibonacci Series (Recursive):");
        startTime = System.nanoTime();
        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacciRecursive(i) + " ");
        }
        System.out.println();
        endTime = System.nanoTime();
        endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long recursiveTime = endTime - startTime;
        long recursiveSpace = endMemory - startMemory;

        // Calculate and print Fibonacci series (Iterative)
        System.out.println("Fibonacci Series (Iterative):");
        startTime = System.nanoTime();
        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        for (int i = 0; i <= n; i++) {
            System.out.print(fibonacciIterative(i) + " ");
        }
        System.out.println();
        endTime = System.nanoTime();
        endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long iterativeTime = endTime - startTime;
        long iterativeSpace = endMemory - startMemory;

        System.out.println("Time and Space Complexity (Recursive):");
        System.out.println("Time (ns): " + recursiveTime);
        System.out.println("Space (bytes): " + recursiveSpace);

        System.out.println("Time and Space Complexity (Iterative):");
        System.out.println("Time (ns): " + iterativeTime);
        System.out.println("Space (bytes): " + iterativeSpace);
    }

    public static long fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        } else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    public static long fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }

        long a = 0;
        long b = 1;

        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }

        return b;
    }
}
