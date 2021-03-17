package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author tackedev
 * @since 3/17/21 2:35 PM
 */
public class IntSorterTest {
    public static final int SELECTION = 1;
    public static final int INSERTION = 2;
    public static final int BUBBLE = 3;
    public static final int QUICK1 = 4;
    public static final int QUICK2 = 5;
    public static final int HEAP = 6;
    public static final int MERGE = 7;
    public static final int RADIX = 8;

    public static final int N = 100000;

    public static void genArray(int[] a, int n) {
        Random rand = new Random((System.currentTimeMillis()));
        for (int i = 0; i < n; i++)
            a[i] = rand.nextInt(1000);
    }

    public static void print(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            System.out.format("%3d,", a[i]);
            if (i > 0 && (i+1)%30 == 0)
                System.out.println();
        }
        System.out.println();
    }

    public static boolean checkAsc(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i-1])
                return false;
        }
        return true;
    }

    public static long measure(int[] a, int n, int method) {
        long t1 = System.currentTimeMillis();
        switch (method) {
            case SELECTION:
                IntSorter.selectionSort(a, n);
                break;
            case INSERTION:
                IntSorter.insertionSort(a, n);
                break;
            case BUBBLE:
                IntSorter.bubbleSort(a, n);
                break;
            case QUICK1:
                IntSorter.quickSort1(a, n);
                break;
            case QUICK2:
                IntSorter.quickSort2(a, n);
                break;
            case HEAP:
                IntSorter.heapSort(a, n);
                break;
            case MERGE:
                IntSorter.mergeSort(a, n);
                break;
            case RADIX:
                IntSorter.radixSort(a, n);
                break;
        }
        long t2 = System.currentTimeMillis();
        return t2 - t1;
    }

    public static void main(String[] args) {
        int[] ar = new int[N];
        genArray(ar, N);
        /*System.out.println("Original array:");
        print(ar, N);*/

        System.out.println("Waiting for sorting " + N + " elements.\n");
        long t;
        int[] a;

        /* Selection sort */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, SELECTION);
        System.out.print("Selection sort, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Insertion sort */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, INSERTION);
        System.out.print("Insertion sort, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Bubble sort */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, BUBBLE);
        System.out.print("Bubble sort, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Quick sort 1 */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, QUICK1);
        System.out.print("Quick sort 1, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Quick sort 2 */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, QUICK2);
        System.out.print("Quick sort 2, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Heap sort */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, HEAP);
        System.out.print("Heap sort, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Merge sort */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, MERGE);
        System.out.print("Merge sort, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));

        /* Radix sort */
        a = Arrays.copyOf(ar, N);
        t = measure(a, N, RADIX);
        System.out.print("Radix sort, Time case: " + t + " milisec. ");
        System.out.println("Ascending order? " + checkAsc(a, N));
    }
}
