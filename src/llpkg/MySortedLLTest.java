package llpkg;

/**
 *
 * @author tackedev
 * @since Jan 20, 2021 7:14:09 AM
 */
public class MySortedLLTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MySortedLL list = new MySortedLL();
        list.add(9, 5, 1, 2, 6, 7, 8, 7, 3, 4, 0);
        
        MyIterator it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();
        
        LL_Element result = list.search(8);
        if (result != null) {
            System.out.println("8 existed.");
        } else {
            System.out.println("8 doesn't existed!");
        }
        
        list.remove(8);
        System.out.println("After 8 was remove:");
        it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
        System.out.println();
    }

}
