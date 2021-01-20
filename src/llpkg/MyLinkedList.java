package llpkg;

/**
 *
 * @author tackedev
 * @since Jan 18, 2021 9:28:07 PM
 */
public class MyLinkedList {
    LL_Element head;
    LL_Element tail;

    public MyLinkedList() {
        head = tail = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public LL_Element addFirst(LL_Element element) {
        if (head == null) {
            head = tail = element;
        } else {
            element.setPrevious(null);
            element.setNext(head);
            head.setPrevious(element);
            head = element;
        }
        return element;
    }
    
    public void addFirst(Comparable... group) {
        for (Comparable data: group) {
            addFirst(new LL_Element(data));
        }
    }
    
    public LL_Element addLast(LL_Element element) {
        if (head == null) {
            head = tail = element;
        } else {
            element.setPrevious(tail);
            element.setNext(null);
            tail.setNext(element);
            tail = element;
        }
        return element;
    }
    
    public void addLast(Comparable... group) {
        for (Comparable data: group) {
            addLast(new LL_Element(data));
        }
    }
    
    public LL_Element addAfter(LL_Element element, LL_Element p) {
        if (p == null || p == tail) {
            return addLast(element);
        }
        
        LL_Element pAfter = p.getNext();
        element.setPrevious(p);
        element.setNext(pAfter);
        p.setNext(element);
        pAfter.setPrevious(element);
        return element;
    }
    
    public LL_Element addBefore(LL_Element element, LL_Element p) {
        if (p == null || p == head) {
            return addFirst(element);
        }
        
        LL_Element pBefore = p.getPrevious();
        element.setPrevious(pBefore);
        element.setNext(p);
        pBefore.setNext(element);
        p.setPrevious(element);
        return element;
    }
    
    public LL_Element search(Comparable x) {
        LL_Element t; 
        for (t = head; t != null; t = t.getNext()) {
            if (t.getData().compareTo(x) == 0) {
                return t;
            }
        }
        return null;
    }
    
    public LL_Element removeFirst() {
        if (head == null) {
            return null;
        }
        
        LL_Element result = head;
        if (head == tail) {
            head = tail = null;
        } else {
            LL_Element newHead = head.getNext();
            newHead.setPrevious(null);
            head = newHead;
        }
        return result;
    }
    
    public LL_Element removeLast() {
        if (tail == null) {
            return null;
        }
        
        LL_Element result = tail;
        if (head == tail) {
            head = tail = null;
        } else {
            LL_Element newTail = tail.getPrevious();
            newTail.setNext(null);
            tail = newTail;
        }
        return result;
    }
    
    private LL_Element remove(LL_Element element) {
        if (element == null) {
            return null;
        }
        if (element == head) {
            removeFirst();
        }
        if (element == tail) {
            removeLast();
        }
        LL_Element pBefore = element.getPrevious();
        LL_Element pAfter = element.getNext();
        pBefore.setNext(pAfter);
        pAfter.setPrevious(pBefore);
        return element;
    }
    
    public LL_Element remove(Comparable x) {
        return remove(search(x));
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
