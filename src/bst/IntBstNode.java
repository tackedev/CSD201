package bst;

/**
 *
 * @author tackedev
 * @since  Feb 1, 2021 9:58:51 AM
 */
public class IntBstNode {
    private int key;
    private IntBstNode left, right;
    private IntBstNode father;

    public IntBstNode(int key) {
        this.key = key;
        left = right = father = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public IntBstNode getLeft() {
        return left;
    }

    public void setLeft(IntBstNode left) {
        this.left = left;
    }

    public IntBstNode getRight() {
        return right;
    }

    public void setRight(IntBstNode right) {
        this.right = right;
    }

    public IntBstNode getFather() {
        return father;
    }

    public void setFather(IntBstNode father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "" + key;
    }
    
    public boolean isLeaf() {
        return left == null && right == null;
    }
    
    public boolean hasOneChild() {
        return (left == null && right != null) || (left != null && right == null);
    }
    
    public boolean hasTwoChildren() {
        return left != null && right != null;
    }
}
