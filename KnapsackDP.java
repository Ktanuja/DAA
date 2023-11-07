import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class KnapsackDP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        int[] weights = new int[n];
        int[] profits = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight for item " + (i + 1) + ": ");
            weights[i] = scanner.nextInt();
            System.out.print("Enter profit for item " + (i + 1) + ": ");
            profits[i] = scanner.nextInt();
        }

        System.out.print("Enter the maximum weight capacity of the knapsack: ");
        int capacity = scanner.nextInt();
        scanner.close();

        long startTime, endTime;
        long startMemory, endMemory;

        // Calculate maximum profit using dynamic programming
        startTime = System.nanoTime();
        startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        KnapsackResult result = knapsackDynamicProgramming(weights, profits, capacity, n);

        endTime = System.nanoTime();
        endMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        long executionTime = endTime - startTime;
        long memoryUsed = endMemory - startMemory;

        System.out.println("Maximum Profit (Dynamic Programming): " + result.maxProfit);
        System.out.println("Items selected: " + result.selectedItems);
        System.out.println("Time taken (ns): " + executionTime);
        System.out.println("Space used (bytes): " + memoryUsed);
    }

    public static class KnapsackResult {
        int maxProfit;
        List<Integer> selectedItems;

        KnapsackResult(int maxProfit, List<Integer> selectedItems) {
            this.maxProfit = maxProfit;
            this.selectedItems = selectedItems;
        }
    }

    public static KnapsackResult knapsackDynamicProgramming(int[] weights, int[] profits, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(profits[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Tracking the selected items
        int maxProfit = dp[n][capacity];
        List<Integer> selectedItems = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0 && maxProfit > 0; i--) {
            if (maxProfit != dp[i - 1][w]) {
                selectedItems.add(i);
                maxProfit -= profits[i - 1];
                w -= weights[i - 1];
            }
        }

        return new KnapsackResult(dp[n][capacity], selectedItems);
    }
}
