package hash;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author tackedev
 * @since Mar 14, 2021 11:08:00 AM
 */
public class CharCounter extends HashMap<Integer, Integer>{
    /* <ASCII, count> */
    private int numOfChars;

    public CharCounter() {
        super();
        numOfChars = 0;
    }
    
    public CharCounter(String filename) {
        super();
        numOfChars = 0;
        try {
            FileReader fr = new FileReader(filename);
            int c;
            while ((c = fr.read()) != -1) {
                if (!this.containsKey(c)) {
                    this.put(c, 1);
                } else {
                    this.put(c, this.get(c) + 1);
                }
                numOfChars++;
            }
            fr.close();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void print() {
        this.keySet().forEach(cObj -> {
            Integer value = this.get(cObj);
            System.out.println((char) cObj.intValue() + ", " + value + ", " + 1.0*value/numOfChars);
        });
    }
    
    public static void main(String[] args) {
        CharCounter counter = new CharCounter("laychong.txt");
        counter.print();
    }
}
