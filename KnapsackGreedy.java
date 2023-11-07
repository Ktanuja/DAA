import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Item implements Comparable<Item> {
    int weight;
    int profit;
    int index;

    Item(int weight, int profit, int index) {
        this.weight = weight;
        this.profit = profit;
        this.index = index;
    }

    public int compareTo(Item other) {
        return other.profit / other.weight - this.profit / this.weight;
    }
}

public class KnapsackGreedy {
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

        List<Integer> selectedItems = new ArrayList<>();
        long startTime = System.nanoTime();
        int maxProfit = knapsackGreedy(weights, profits, capacity, n, selectedItems);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("Maximum Profit (Greedy Approach): " + maxProfit);
        System.out.println("Items selected: " + selectedItems);
        System.out.println("Execution Time: " + executionTime + " nanoseconds");

        scanner.close();
    }

    // Greedy Knapsack Algorithm with a time complexity of O(n log n)
    public static int knapsackGreedy(int[] weights, int[] profits, int capacity, int n, List<Integer> selectedItems) {
        // Sort the items based on the profit-to-weight ratio
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], profits[i], i + 1);
        }
        long sortingStartTime = System.nanoTime();
        Arrays.sort(items); // O(n log n) time complexity
        long sortingEndTime = System.nanoTime();
        long sortingTime = sortingEndTime - sortingStartTime;

        int maxProfit = 0;
        int currentWeight = 0;

        for (int i = 0; i < n; i++) {
            if (currentWeight + items[i].weight <= capacity) {
                maxProfit += items[i].profit;
                currentWeight += items[i].weight;
                selectedItems.add(items[i].index);
            } else {
                int remainingCapacity = capacity - currentWeight;
                maxProfit += (int) (items[i].profit * ((double) remainingCapacity / items[i].weight));
                break;
            }
        }

        System.out.println("Sorting Time: " + sortingTime + " nanoseconds");

        return maxProfit;
    }
}
