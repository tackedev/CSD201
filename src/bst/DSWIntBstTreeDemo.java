package bst;

/**
 *
 * @author tackedev
 * @since  Feb 22, 2021 4:38:46 PM
 */
public class DSWIntBstTreeDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DSWIntBstTree tree = new DSWIntBstTree();
        tree.add(20, 19, 7, 6, 5, 18, 16, 15, 14, 13, 12, 11, 10,
                9, 8, 4, 3, 2, 1);
        System.out.println("\nIntial tree:\n");
        tree.printAlign();
        tree.DSW_Balance();
        System.out.println("\nBalanced tree:\n");
        tree.printAlign();
    }

}
