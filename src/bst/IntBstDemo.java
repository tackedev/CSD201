package bst;

import java.util.Scanner;

/**
 *
 * @author tackedev
 * @since  Feb 2, 2021 8:56:42 PM
 */
public class IntBstDemo {
    
    private static Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu("Binary Seach Tree");
        menu.addOption("1. Add new key");
        menu.addOption("2. Maximum value");
        menu.addOption("3. Minimum value");
        menu.addOption("4. Average value");
        menu.addOption("5. Tree's height");
        menu.addOption("6. Breadth-first output");
        menu.addOption("7. Align output");
        menu.addOption("8. Preoder output");
        menu.addOption("9. Inorder-LNR output");
        menu.addOption("10. Inorder-RNL output");
        menu.addOption("11. Postorder output");
        menu.addOption("12. Searching a key");
        menu.addOption("13. Removing a key by mearging");
        menu.addOption("14. Removing a key by mearging");
        menu.addOption("15. exit");
        
        IntBstTree tree = new IntBstTree();
        
        tree.add(32, 11, 57, 6, 18, 40, 80, 2, 8, 16, 22, 35, 50, 70, 90,
                1, 5, 10, 15, 17, 34, 37, 45, 85, 3);
        int choice;
        int x;
        do {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    x = input("Input a key: ");
                    if (tree.add(x) == true) {
                        output("Adding " + x + " successfully");
                    } else {
                        output("Adding fail");
                    }
                    break;
                case 2:
                    output("Max value: " + tree.getMax());
                    break;
                case 3:
                    output("Min value: " + tree.getMin());
                    break;
                case 4:
                    output("Avg. value: " + tree.getAverage());
                    break;
                case 5:
                    output("Tree's height: " + tree.getHeight());
                    break;
                case 6:
                    output("Breadth-first/level-based output:");
                    tree.printLevelBased();
                    break;
                case 7:
                    output("Tree in aligned format: ");
                    tree.printAlign();
                    break;
                case 8:
                    output("Tree in preorder list: ");
                    tree.printNLR();
                    break;
                case 9:
                    output("Inorder-LNR output: ");
                    tree.printLNR();
                    break;
                case 10:
                    output("Inorder-RNL output:");
                    tree.printRNL();
                    break;
                case 11:
                    output("Postorder output:");
                    tree.printPostOrder();
                case 12:
                    x = input("Input searched value: ");
                    IntBstNode node = tree.search(x);
                    if (node == null) {
                        output("Not found!");
                    } else {
                        output("Found: " + node.getKey());
                    }
                    break;
                case 13:
                    x = input("Input deleted key: ");
                    if (tree.deleteByMerging(x)) {
                        output("Delete successfully");
                    } else {
                        output("Deleting fail!");
                    }
                    break;
                case 14:
                    x = input("Input deleted key: ");
                    if (tree.deleteByCopying(x)) {
                        output("Delete successfully");
                    } else {
                        output("Deleting fail!");
                    }
                    break;
            }
        } while (choice != 15);
    }
    
    private static int input(String msg) {
        System.out.print("\n" + msg);
        return Integer.parseInt(sc.nextLine());
    }
    
    private static void output(String msg) {
        System.out.println("\n" + msg + "\n");
    }
}
