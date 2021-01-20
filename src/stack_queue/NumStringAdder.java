package stack_queue;

import java.util.Stack;

/**
 *
 * @author tackedev
 * @since Jan 20, 2021 7:25:09 AM
 */
public class NumStringAdder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String num1 = "592";
        String num2 = "3784";
        String sum = add(num1, num2);
        System.out.println(sum);
    }
    
    public static int add(int carry, char d1, char d2, Stack result) {
        int num1 = Character.getNumericValue(d1);
        int num2 = Character.getNumericValue(d2);
        int sum = carry + num1 + num2;
        
        int newCarry = 0;
        if (sum > 10) {
            newCarry = 1;
            sum -= 10;
        }
        result.add(Character.forDigit(sum, 10));
        return newCarry;
    }
    
    public static String add(String num1, String num2) {
        Stack<Character> stack1 = new Stack();
        Stack<Character> stack2 = new Stack();
        
        for (int i = 0; i < num1.length(); i++) {
            stack1.push(num1.charAt(i));
        }
        for (int i = 0; i < num2.length(); i++) {
            stack2.push(num2.charAt(i));
        }
        
        Stack<Character> resultStk = new Stack();
        char d1, d2;
        int carry = 0;
        while (!stack1.empty() || !stack2.empty()) {
            d1 = !stack1.empty() ? stack1.pop() : '0';
            d2 = !stack2.empty() ? stack2.pop() : '0';
            carry = add(carry, d1, d2, resultStk);
        }
        
        String result = "";
        while (!resultStk.empty()) {
            result += resultStk.pop();
        }
        return result;
    }

}
