package main.java.task2;

public class QuickSort {
    private BreadCrumbs crumbs;

    public QuickSort(BreadCrumbs crumbs) {
        this.crumbs = crumbs;
    }

    public void sort(int[] arr, int low, int high) {
        crumbs.addLowAndHighBorder(low, high);
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            sort(arr, low, pivotIndex - 1);
            sort(arr, pivotIndex + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
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
        return i + 1;
    }

    public BreadCrumbs getCrumbs() {
        return crumbs;
    }
}
