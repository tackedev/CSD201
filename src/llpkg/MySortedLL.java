package llpkg;

/**
 *
 * @author tackedev
 * @since Jan 20, 2021 7:00:21 AM
 */
public class MySortedLL {
    
    LL_Element head;
    LL_Element tail;

    public MySortedLL() {
        head = tail = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public LL_Element ceiling(Comparable x) {
        LL_Element t = head;
        while (t != null && t.getData().compareTo(x) < 0) {
            t = t.getNext();
        }
        return t;
    }
    
    public LL_Element search(Comparable x) {
        LL_Element result =  ceiling(x);
        if (result == null) { 
            return null;
        }
        return result.getData().equals(x) ? result : null;
    }
    
    public LL_Element add(Comparable x) {
        LL_Element element = new LL_Element(x);
        LL_Element after = ceiling(x);
        if (head == null) {
            head = tail = element;
        } else if (after == null) {
            element.setNext(null);
            element.setPrevious(tail);
            tail.setNext(element);
            tail = element;
        } else if (after == head) {
            element.setNext(head);
            element.setPrevious(null);
            head.setPrevious(element);
            head = element;
        } else {
            LL_Element before = after.getPrevious();
            element.setNext(after);
            element.setPrevious(before);
            after.setPrevious(element);
            before.setNext(element);
        }
        return element;
    }
    
    public void add(Comparable... group) {
        for (Comparable data: group) {
            add(data);
        }
    }
    
    private LL_Element remove(LL_Element ref) {
        if (ref == head && head == tail) {
            head = tail = null;
            return ref;
        }
        LL_Element before = ref.getPrevious();
        LL_Element after = ref.getNext();
        
        if (ref == head) {
            after.setPrevious(null);
            head = after;
        } else if (ref == tail) {
            before.setNext(null);
            tail = before;
        } else {
            before.setNext(after);
            after.setPrevious(before);
        }
        return ref;
    }
    
    public LL_Element remove(Comparable x) {
        LL_Element ref = search(x);
        if (ref != null) {
            remove(ref);
        }
        return ref;
    }
    
    private class Traverser implements  MyIterator<Comparable> {
        LL_Element currentRef;

        public Traverser() {
            currentRef = new LL_Element(null);
            currentRef.setNext(head);
        }

        @Override
        public boolean hasNext() {
            return currentRef.getNext() != null;
        }

        @Override
        public Comparable next() {
            currentRef = currentRef.getNext();
            return currentRef.getData();
        }
    }
    
    public MyIterator iterator() {
        if (head == null) {
            return null;
        }
        return new Traverser();
    }

}