package stack_queue;

import java.util.Stack;

/**
 *
 * @author tackedev
 * @since Jan 20, 2021 7:19:07 AM
 */
public class Converter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n = 106;
        System.out.println(convert(n, 2) + "b");
        System.out.println(convert(n, 8) + "q");
        System.out.println(convert(n, 10) + "d");
        System.out.println(convert(n, 16) + "h");
    }
    
    public static String convert(int n, int base) {
        Stack<Integer> stack = new Stack();
        do {
            stack.push(n % base);
            n /= base;
        } while (n > 0);
        
        String result = "";
        while (!stack.empty()) {
            int value = stack.pop();
            result += Character.forDigit(value, base);
        }
        return result;
    }

}
