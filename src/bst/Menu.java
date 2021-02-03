package bst;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tackedev
 */
public class Menu {
    private final String title;
    private final ArrayList<String> options = new ArrayList<>();
    private final Scanner sc = new Scanner(System.in);

    public Menu(String title) {
        this.title = title;
    }
    
    public void addOption(String option) {
        options.add(option);
    }
    
    public void print() {
        System.out.println("--------- " + title + " ---------");
        options.forEach(option -> {
            System.out.println(option);
        });
    }
    
    public int getChoice() {
        int size = options.size();
        int choice = 0;
        while (choice == 0) {
            System.out.print("Enter your choice [1-" + size + "]: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Try again!");
            }
        }
        return choice;
    }
}