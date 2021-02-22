package bst;

/**
 *
 * @author tackedev
 * @since  Feb 22, 2021 10:08:25 AM
 */
public class DSWIntBstTree extends IntBstTree {
    
    // gr: grandparent
    // par: parent
    // ch: child
    
    public void rotateRight(IntBstNode gr, IntBstNode par, IntBstNode ch) {
        if (par != root) {
            ch.setFather(gr);
            gr.setRight(ch);
        }
        par.setLeft(ch.getRight());
        if (par.getLeft() != null) {
            par.getLeft().setFather(par);
        }
        ch.setRight(par);
        par.setFather(ch);
    }
    
    private int createBackbone() {
        if (root == null) {
            return 0;
        }
        int numNodes = 0;
        IntBstNode tmp = root, gr, par, ch;
        
        while (tmp != null) {
            while (tmp.getLeft() != null) {
                gr = tmp.getFather();
                par = tmp;
                ch = tmp.getLeft();
                rotateRight(gr, par, ch);
                if (gr == null) {
                    root = ch;
                    root.setFather(null);
                }
                tmp = ch;
            }
            tmp = tmp.getRight();
            numNodes++;
        }
        return numNodes;
    }
    
    public void rotateLeft(IntBstNode gr, IntBstNode par, IntBstNode ch) {
        if (gr == null) {
            root = ch;
        } else {
            gr.setRight(ch);
        }
        ch.setFather(gr);
        par.setRight(ch.getLeft());
        if (par.getRight() != null) {
            par.getRight().setFather(par);
        }
        ch.setLeft(par);
        par.setFather(ch);
    }
    
    private void createBalancedTree(int numNodes) {
        int n = numNodes;
        double log2nPlus1 = Math.log(n+1)/Math.log(2);
        int l = (int) log2nPlus1;
        int m = (int) Math.pow(2, l) - 1;
        
        IntBstNode gr = null, par = root, ch = par.getRight();
        for (int i = 0; i < n-m; i++) {
            rotateLeft(gr, par, ch);
            gr = ch;
            par = gr.getRight();
            ch = par.getRight();
        }
        
        while (m > 1) {
            m /= 2;
            
            gr = null; par = root; ch = par.getRight();
            for (int i = 0; i < m; i++) {
                if (ch != null) {
                    rotateLeft(gr, par, ch);
                    gr = ch;
                    par = gr.getRight();
                    if (par != null) {
                        ch = par.getRight();
                    } else {
                        ch = null;
                    }
                }
            }
        }
    }
    
    public void DSW_Balance() {
        if (root != null) {
            int numNodes = createBackbone();
            createBalancedTree(numNodes);
        }
    }
    
}
