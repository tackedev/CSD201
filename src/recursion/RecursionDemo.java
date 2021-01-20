package recursion;

/**
 *
 * @author tackedev
 * @since Jan 20, 2021 1:22:34 PM
 */
public class RecursionDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Factorial test
        System.out.println(factorial(5));
        
        // Fibonacci test
        System.out.println(fibo(8));
        
        // Arithmeic progression
        System.out.println(ap(5, 3, 2));
        
        // Geometric progression
        System.out.println(gp(5, 1, 3));
        
        int[] a = {1, 5, 9, 7, 2, 10, 19};
        
        // sum of intergral array
        System.out.println(sum(a, 7));
        
        // max of intergral array
        System.out.println(max(a, 7));
        
        // min of intergral array
        System.out.println(min(a, 7));
        
    }
    
    public static double factorial(int n) {
        if (n < 2) return 1;
        return n*factorial(n-1);
    }
    
    public static int fibo(int n) {
        if (n < 3) return 1;
        return fibo(n-1) + fibo(n-2);
    }
    
    // Arithmetic progression
    public static double ap(int n, double a, double r) {
        if (n == 1) return a;
        return ap(n-1, a, r) + r;
    }
    
    // Geometric progression
    public static double gp(int n, double a, double q) {
        if (n == 1) return a;
        return gp(n-1, a, q) * q;
    }
    
    public static int sum(int[] a, int n) {
        if (n == 0) return 0;
       return sum(a, n-1) + a[n-1];
    }
    
    public static int max(int[] a, int n) {
        if (n == 1) return a[0];
        int m = max(a, n-1);
        return m > a[n-1] ? m : a[n-1];
    }
    
    public static int min(int[] a, int n) {
        if (n == 1) return a[0];
        int m = min(a, n-1);
        return m < a[n-1] ? m : a[n-1];
    }
}
