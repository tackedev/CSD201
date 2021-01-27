package backtracking.core;

/**
 *
 * @author tackedev
 * @since Jan 25, 2021 3:43:41 PM
 */
public class IndexDomain<E> {
    
    private RealDomain<E> realDomain;
    private int n;
    private int curIndex;

    public IndexDomain(RealDomain realDomain) {
        this.realDomain = realDomain;
        n = realDomain.size();
        curIndex = -1;
    }

    public RealDomain<E> getRealDomain() {
        return realDomain;
    }

    public void setRealDomain(RealDomain<E> realDomain) {
        this.realDomain = realDomain;
    }
    
    public int nextIndex() {
        if (this == null) {
            throw new RuntimeException("Domain is null");
        }
        return ++curIndex;
    }
    
    public boolean hasNext() {
        return curIndex + 1 < n;
    }
    
    public void reset() {
        curIndex = -1;
    }
    
    public E getRealValue(int i) {
        if (curIndex == -1) {
            return null;
        }
        return realDomain.get(i);
    }
}
