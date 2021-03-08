package simple_graphs.com;

import java.util.ArrayList;

/**
 *
 * @author tackedev
 * @param <T>
 * @since  Mar 1, 202:12:48 PM
 */
public class OrderedList<T> extends ArrayList<Comparable> {
    
    public T search(T x) {
        if (this.isEmpty()) {
            return null;
        }
        int first = 0, last = this.size()-1;
        while (first <= last) {
            int mid = (first + last)/2;
            Comparable result = this.get(mid);
            int d = ((Comparable) x).compareTo(result);
            if (d == 0) { 
                return (T)result;
            }
            if (d < 0) {
                last = mid - 1;
            } else {
                first = mid + 1;
            }
        }
        return null;
    }
    
    public void addItem(Comparable x) {
        if (this.isEmpty()) {
            this.add(x);
        } else {
            int n = this.size(), i;
            for (i = n-1; n >= 0; i--) {
                Comparable t = (Comparable) this.get(i);
                if (t.compareTo(x) <= 0) break;
            }
            this.add(i+1, x);
        }
    }
}
