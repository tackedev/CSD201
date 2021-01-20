package llpkg;

/**
 *
 * @author tackedev
 * @since Jan 18, 2021 9:24:30 PM
 */
public class LL_Element {
    private Comparable data;
    private LL_Element next;
    private LL_Element previous;

    public LL_Element(Comparable data) {
        this.data = data;
        next = previous = null;
    }

    public Comparable getData() {
        return data;
    }

    public void setData(Comparable data) {
        this.data = data;
    }

    public LL_Element getNext() {
        return next;
    }

    public void setNext(LL_Element next) {
        this.next = next;
    }

    public LL_Element getPrevious() {
        return previous;
    }

    public void setPrevious(LL_Element previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
