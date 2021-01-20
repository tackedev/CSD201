package stack_queue;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author tackedev
 * @since Jan 20, 2021 7:53:04 AM
 */
public class PrefixEvaluator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String exp = "* (3) + * (5) (6) * (3) (4)";
        System.out.println(evaluate(exp));
    }
    
    public static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
    
    public static double evaluate(String op, double n1, double n2) {
        if (op.equals("+")) {
            return n1 + n2;
        }
        if (op.equals("-")) {
            return n1 - n2;
        }
        if (op.equals("*")) {
            return n1 * n2;
        }
        if (op.equals("/")) {
            if (n2 == 0) {
                throw new RuntimeException("Divide by 0!");
            }
            return n1 / n2;
        }
        throw new RuntimeException("Operator is not supported!");
    }
    
    public static double evaluate(String exp) {
        StringTokenizer st = new StringTokenizer(exp, "() ");
        Stack<String> expList = new Stack();
        while (st.hasMoreTokens()) {
            expList.push(st.nextToken());
        }
        
        Stack<Double> stack = new Stack();
        
        double n1, n2;
        double result = 0;
        while (!expList.empty()) {
            String part = expList.pop();
            if (!isOperator(part)) {
                stack.push(Double.parseDouble(part));
            } else {
                n1 = stack.pop();
                n2 = stack.pop();
                result = evaluate(part, n1, n2);
                stack.push(result);
            }
        }
        return result;
    }
}
