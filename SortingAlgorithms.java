import java.util.Arrays;

public class SortingAlgorithms {

    // Initial service-time array specified in Part B
    private static final int[] INITIAL_ARRAY = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

    // ==========================================
    // TASK B1: SELECTION SORT
    // ==========================================
    public static void selectionSort(int[] inputArr) {
        int[] arr = inputArr.clone();
        int n = arr.length;
        int comparisons = 0;
        int swaps = 0;

        System.out.println("\n--- TASK B1: SELECTION SORT ---");
        System.out.println("Initial Array: " + Arrays.toString(arr));

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            if (i < 3) {
                System.out.println("Pass " + (i + 1) + ": " + Arrays.toString(arr));
            }
        }

        System.out.println("Final Sorted Array: " + Arrays.toString(arr));
        System.out.println("Total Comparisons: " + comparisons);
        System.out.println("Total Swaps: " + swaps);
    }

    // ==========================================
    // TASK B2: INSERTION SORT
    // ==========================================
    public static void insertionSort(int[] inputArr) {
        int[] arr = inputArr.clone();
        int n = arr.length;
        int comparisons = 0;
        int shifts = 0;

        System.out.println("\n--- TASK B2: INSERTION SORT ---");
        System.out.println("Initial Array: " + Arrays.toString(arr));

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;

            if (i <= 3) {
                System.out.println("Pass " + i + ": " + Arrays.toString(arr));
            }
        }

        System.out.println("Final Sorted Array: " + Arrays.toString(arr));
        System.out.println("Total Comparisons: " + comparisons);
        System.out.println("Total Shifts: " + shifts);
    }

    // ==========================================
    // TASK B3: MERGE SORT
    // ==========================================
    public static void mergeSort(int[] inputArr) {
        int[] arr = inputArr.clone();
        System.out.println("\n--- TASK B3: MERGE SORT ---");
        System.out.println("Initial Array: " + Arrays.toString(arr));

        mergeSortHelper(arr, 0, arr.length - 1);

        System.out.println("Final Sorted Array: " + Arrays.toString(arr));
    }

    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left >= right) { // Base case
            return;
        }

        int mid = left + (right - left) / 2;

        System.out.println("Divide: Indices " + left + " to " + mid + " and " + (mid + 1) + " to " + right);

        mergeSortHelper(arr, left, mid);
        mergeSortHelper(arr, mid + 1, right);

        merge(arr, left, mid, right);
        System.out.println("Merged Subarray (" + left + " to " + right + "): " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // ==========================================
    // TASK B4: QUICK SORT
    // ==========================================
    private static int partitionStage = 0;

    public static void quickSort(int[] inputArr) {
        int[] arr = inputArr.clone();
        partitionStage = 0;
        System.out.println("\n--- TASK B4: QUICK SORT ---");
        System.out.println("Pivot Rule: Last Element as Pivot");
        System.out.println("Initial Array: " + Arrays.toString(arr));

        quickSortHelper(arr, 0, arr.length - 1);

        System.out.println("Final Sorted Array: " + Arrays.toString(arr));
    }

    private static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        int pivotIndex = i + 1;
        partitionStage++;

        if (partitionStage <= 2) {
            System.out.println("\nPartition Stage " + partitionStage + ":");
            System.out.println("Pivot Chosen: " + pivot);
            System.out.println("Left Partition: " + Arrays.toString(Arrays.copyOfRange(arr, low, pivotIndex)));
            System.out.println("Right Partition: " + Arrays.toString(Arrays.copyOfRange(arr, pivotIndex + 1, high + 1)));
            System.out.println("Current Array State: " + Arrays.toString(arr));
        }

        return pivotIndex;
    }

    // ==========================================
    // MENU OPTION 9 INTEGRATION FUNCTION
    // ==========================================
    public static void executeOption9() {
        System.out.println("\n==========================================");
        System.out.println("   OPTION 9: SORTING SERVICE TIMES");
        System.out.println("==========================================");
        
        selectionSort(INITIAL_ARRAY);
        insertionSort(INITIAL_ARRAY);
        mergeSort(INITIAL_ARRAY);
        quickSort(INITIAL_ARRAY);
    }
}
