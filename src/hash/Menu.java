package hash;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tackedev
 * @since Mar 14, 2021 11:54:39 AM
 */
public class Menu extends ArrayList<String> {
    
    private final Scanner sc = new Scanner(System.in);
    
    public int getUserChoice() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + "-" + this.get(i));
        }
        System.out.print("Choose operation:");
        return Integer.parseInt(sc.nextLine());
    }
}
