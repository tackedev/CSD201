package sort;

import java.util.LinkedList;

/**
 * @author tackedev
 * @since 3/16/21 2:06 PM
 */
public class IntSorter {

    /**
     * complexity O(1)
     * @param a array
     * @param i first index
     * @param j second index
     */
    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /* --- Selection Sort ---*/
    /**
     * complexity: O(n)
     * @param a array
     * @param first first position
     * @param last last position
     * @return index of min value from first to last
     */
    private static int getMinIndex(int[] a, int first, int last) {
        int minIndex = first;
        for (int i = first + 1; i <= last; i++) {
            if (a[minIndex] > a[i]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * complexity O(n^2)
     * @param a array
     * @param n  cardinality
     */
    public static void selectionSort(int[] a, int n) {
        int minIndex;
        for (int i = 0; i < n; i++) {
            minIndex = getMinIndex(a, i, n - 1);
            swap(a, minIndex, i);
        }
    }

    /* --- Insertion Sort ---*/
    /**
     * complexity O(n^2)
     * @param a array
     * @param n cardinality
     */
    public static void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int tmp = a[i];
            int j = i - 1;
            for (; j >= 0 && a[j] > tmp; j--) {
                a[j+1] = a[j];
            }
            a[j+1] = tmp;
        }
    }

    /* --- Bubble Sort ---*/
    /**
     * complexity O(n^2)
     * @param a array
     * @param n cardinality
     */
    public static void bubbleSort(int[] a, int n) {
        for (int i = 0; i < n - 1 ; i++) {
            for (int j = n-1; j > i; j--) {
                if (a[j] < a[j-1]) {
                    swap(a, j, j-1);
                }
            }
        }
    }

    /* --- Quick Sort 1---*/
    /**
     * @param a array
     * @param first first position
     * @param last last position
     */
    private static void quickSort1(int[] a, int first, int last) {
        int mid = (first + last)/2;
        swap(a, first, mid);

        int lower = first + 1, upper = last;
        int pivote = a[first];
        while (lower <= upper) {
            while (lower <= upper && pivote > a[lower]) lower++;
            while (lower <= upper && pivote < a[upper]) upper--;
            if (lower < upper)
                swap(a, lower++, upper--);
            else
                lower++;
        }

        swap(a, upper, first);
        if (first < upper-1) quickSort1(a, first, upper-1);
        if (upper+1 < last) quickSort1(a, upper+1, last);
    }

    /**
     * complexity O(nlogn)
     * @param a array
     * @param n cardinality
     */
    public static void quickSort1(int[] a, int n) {
        quickSort1(a, 0, n-1);
    }

    /* --- Quick Sort 2 ---*/
    /**
     * @param a first integer
     * @param b second integer
     * @param c third integer
     * @return median of 3 integer
     */
    private static int median(int a, int b, int c) {
        if ((a > b && a < c) || (a > c && a < b))
            return a;
        if ((b > a && b < c) || (b > c && b < a))
            return b;
        return c;
    }

    /**
     * @param a array
     * @param first first position
     * @param last last position
     */
    private static void quickSort2(int[] a, int first, int last) {
        if (first >= last) return;

        int mid = (first + last) / 2;
        int pivote = median(a[first], a[last], a[mid]);
        int lower = first, upper = last;
        while (lower <= upper) {
            while (lower <= upper && pivote > a[lower]) lower++;
            while (lower <= upper && pivote < a[upper]) upper--;
            if (lower < upper)
                swap(a, lower++, upper--);
            else
                lower++;
        }

        quickSort2(a, first, upper);
        quickSort2(a, upper+1, last);
    }

    /**
     * complexity O(nlogn)
     * @param a array
     * @param n cardinality
     */
    public static void quickSort2(int[] a, int n) {
        quickSort2(a, 0, n-1);
    }

    /* --- Merge Sort ---*/
    private static int[] temp; // Temporary array for merging

    /**
     * Merging 2 array segment in order
     * @param a array
     * @param first first position of large array segment
     * @param last  last position of large array segment
     */
    private static void merge(int[] a, int first, int last) {
        int mid = (first + last)/2;
        int i = first;
        int j = mid + 1;
        int k = 0;

        // merging to temp[] array
        while (i <= mid && j <= last) {
            temp[k++] = (a[i] < a[j]) ? a[i++] : a[j++];
        }
        while (i <= mid) temp[k++] = a[i++];
        while (j <= last) temp[k++] = a[j++];

        // recovery temp[] to a[]
        k = 0; i = first;
        while (i <= last) a[i++] =  temp[k++];
    }

    /**
     * calling recursion mergeSort
     * @param a array
     * @param first first position
     * @param last last position
     */
    private static void mergeSort(int[] a, int first, int last) {
        int mid = (first + last)/2;
        if (first < mid) mergeSort(a, first, mid);
        if (mid+1 < last) mergeSort(a, mid+1, last);
        merge(a, first, last);
    }

    /**
     * complexity O(nlogn)
     * @param a array
     * @param n cardinality
     */
    public static void mergeSort(int[] a, int n) {
        if (n < 2) return;
        temp = new int[n];
        mergeSort(a, 0, n-1);
    }

    /* --- Heap Sort ---*/
    /**
     * move the largest value to the end if group
     * complexity O(logn)
     * @param a array
     * @param first first position
     * @param last last position
     */
    private static void moveDown(int[] a, int first, int last) {
        // first is the father node
        // leftChild = 2*father + 1, rightChild = 2*father + 2
        int largest = 2*first + 1; // suppose that largest is left child
        while (largest <= last) {
            // checking right child is greater than left child
            if (largest < last && a[largest] < a[largest+1]) largest++;
            if (a[first] < a[largest]) {
                swap(a,first, largest);
                first = largest;
                largest = 2*first + 1;
            } else {
                largest = last + 1;
            }
        }
    }

    /**
     * complexity O(nlogn)
     * @param a array
     * @param n cardinality
     */
    public static void heapSort(int[] a, int n) {
        // transfer the initial array to max heap
        for (int i = n/2 - 1; i >= 0; i--)
            moveDown(a, i, n-1);

        for (int i = n-1; i >= 1; i--) {
            swap(a, 0, i); // move the largest value to the end of group
            moveDown(a, 0, i-1); // transfer the remainder to max heap
        }
    }

    /* --- Radix Sort ---*/
    private static final int RADIX = 10;

    /**
     * @param n number
     * @return length of a number
     */
    private static int countDigits(int n) {
        int count = 0;
        while (n/10 != 0) {
            count++;
            n /= 10;
        }
        return count+1;
    }

    /**
     * @param a array
     * @return max absolute value in array
     */
    private static int maxAbsolute(int[] a) {
        int result = Integer.MIN_VALUE;
        for (int x: a) {
            if (x < 0) x = -x;
            if (result < x) result = x;
        }
        return result;
    }

    /**
     * complexity O(n*maxLength(value))
     * @param a array
     * @param n cardinality
     */
    public static void radixSort(int[] a, int n) {
        int longest = maxAbsolute(a);
        int digits = countDigits(longest);
        LinkedList<Integer>[] queues = new LinkedList[RADIX];
        for (int d = 0; d < RADIX; d++) {
            queues[d] = new LinkedList<>();
        }
        for (int d = 1, factor = 1; d <= digits; factor *= RADIX, d++) {
            // Move elements in array to suitable queues base on the d_th digit
            for (int j = 0; j < n; j++) {
                queues[(a[j]/factor) % RADIX].addLast(a[j]);
            }
            // Move values in queues to the array
            for (int j = 0, k = 0; j < RADIX; j++) {
                while (!queues[j].isEmpty())
                    a[k++] = queues[j].removeFirst();
            }
        }
    }
}