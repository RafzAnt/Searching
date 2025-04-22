import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a searching algorithm to perform:");
        System.out.println("1. Linear Search");
        System.out.println("2. Binary Search");
        System.out.println("3. Jump Search");
        System.out.println("4. Exponential Search");

        System.out.print("Enter your choice (1-4): ");
        int choice = scanner.nextInt();

        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();

        int result = -1;

        switch (choice) {
            case 1:
                result = linearSearch(arr, target);
                break;
            case 2:
                bubbleSort(arr); 
                result = binarySearch(arr, target, 0, n - 1);
                break;
            case 3:
                bubbleSort(arr); 
                result = jumpSearch(arr, target);
                break;
            case 4:
                bubbleSort(arr); 
                result = exponentialSearch(arr, target);
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(0);
        }

        if (result != -1) {
            System.out.println("Search result: Element is found at index " + result + ".");
        } else {
            System.out.println("Search result: Element is not found.");
        }
    }

    // Linear Search
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target)
                return i;
        }
        return -1;
    }

    // Binary Search
    public static int binarySearch(int[] arr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target)
                return mid;
            if (arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

    // Jump Search
    public static int jumpSearch(int[] arr, int target) {
        int n = arr.length;
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (arr[Math.min(step, n) - 1] < target) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }

        while (arr[prev] < target) {
            prev++;
            if (prev == Math.min(step, n))
                return -1;
        }

        if (arr[prev] == target)
            return prev;
        return -1;
    }

    // Exponential Search
    public static int exponentialSearch(int[] arr, int target) {
        if (arr[0] == target)
            return 0;
        int i = 1;
        while (i < arr.length && arr[i] <= target)
            i *= 2;
        return binarySearch(arr, target, i / 2, Math.min(i, arr.length - 1));
    }

    // Bubble sort 
    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}