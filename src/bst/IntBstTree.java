package bst;

import java.util.LinkedList;

/**
 *
 * @author tackedev
 * @since  Feb 1, 2021 10:07:51 AM
 */
public class IntBstTree {
    private IntBstNode root;

    public IntBstTree() {
        root = null;
    }
    
    // add
    public boolean add(int x) {
        IntBstNode newNode = new IntBstNode(x);
        
        if (root == null) {
            root = newNode;
        } else {
            IntBstNode pAfter = null;
            IntBstNode pBefore = root;
            
            // determine where to add the node
            while (pBefore != null && pBefore.getKey() != x) {
                pAfter = pBefore;
                if (x < pBefore.getKey()) {
                    pBefore = pBefore.getLeft();
                } else {
                    pBefore = pBefore.getRight();
                }
            }
            
            // checking duplication of x
            if (pBefore != null) {
                return false;
            }

            // pBefore == null, pAfter == father
            newNode.setFather(pAfter);
            if (x < pAfter.getKey()) {
                pAfter.setLeft(newNode);
            } else {
                pAfter.setRight(newNode);
            }
        }
        return true;
    }
    
    public void add(int... a) {
        for (int x: a) {
            add(x);
        }
    }
    
    // traversals
    public void printLevelBased() {
        if (root == null) {
            System.out.println("\nEmpty tree.\n");
        } else {
            LinkedList<IntBstNode> queue = new LinkedList<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                IntBstNode node = queue.removeFirst();
                System.out.print(node + ", ");
                if (node.getLeft() != null) {
                    queue.addLast(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.addLast(node.getRight());
                }
            }
        }
    }
    
    private void printNLR(IntBstNode p) {
        if (p != null) {
            System.out.print(p + ", ");
            printNLR(p.getLeft());
            printNLR(p.getRight());
        }
    }
    
    public void printNLR() {
        if (root == null) {
            System.out.print("\nEmpty tree.\n");
        } else {
            printNLR(root);
        }
    }
    
    private void printLNR(IntBstNode p) {
        if (p != null) {
            printLNR(p.getLeft());
            System.out.print(p + ", ");
            printLNR(p.getRight());
        }
    }
    
    public void printLNR() {
        if (root == null) {
            System.out.print("\nEmpty tree.\n");
        } else {
            printLNR(root);
        }
    }
    
    private void printAlign(IntBstNode p, int nSpace) {
        if (p != null) {
            for (int i = 0; i < nSpace; i++) {
                System.out.print(" ");
            }
            System.out.println(p);
            printAlign(p.getLeft(), nSpace+3);
            printAlign(p.getRight(), nSpace+3);
        }
    }
    
    public void printAlign() {
        if (root == null) {
            System.out.println("\nEmpty tree\n");
        } else {
            printAlign(root, 0);
        }
    }
    
    private void printRNL(IntBstNode p) {
        if (p != null) {
            printRNL(p.getRight());
            System.out.print(p + ", ");
            printRNL(p.getLeft());
        }
    }
    
    public void printRNL() {
        if (root == null) {
            System.out.println("\nEmpty tree.\n");
        } else {
            printRNL(root);
        }
    }
    
    private void printPostOrder(IntBstNode p) {
        if (p != null) {
            printPostOrder(p.getLeft());
            printPostOrder(p.getRight());
            System.out.print(p + ", ");
        }
    }
    
    public void printPostOrder() {
        if (root == null) {
            System.out.println("\nEmpty tree.\n");
        } else {
            printPostOrder(root);
        }
    }
    
    // search
    public IntBstNode search(int x) {
        IntBstNode result = root;
        while (result != null) {
            if (x == result.getKey()) {
                return result;
            } else if (x < result.getKey()) {
                result = result.getLeft();
            } else {
                result = result.getRight();
            }
        }
        return null;
    }
    
    public int getMin() {
        if (root == null) {
            throw  new RuntimeException("Empty tree!");
        }
        IntBstNode p = root;
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p.getKey();
    }
    
    public int getMax() {
        if (root == null) {
            throw  new RuntimeException("Empty tree!");
        }
        IntBstNode p = root;
        while (p.getRight() != null) {
            p = p.getRight();
        }
        return p.getKey();
    }
    
    public int getHeight() {
        class Node_Level {
            IntBstNode node;
            int level;

            public Node_Level(IntBstNode node, int level) {
                this.node = node;
                this.level = level;
            }
            
        }
        
        if (root == null) {
            return 0;
        }
        LinkedList<Node_Level> queue = new LinkedList<>();
        int result = 0;
        queue.addLast(new Node_Level(root, 1));
        while (!queue.isEmpty()) {
            Node_Level v = queue.remove();
            int curLevel = v.level;
            if (result < curLevel) {
                result = curLevel;
            }
            IntBstNode left = v.node.getLeft();
            IntBstNode right = v.node.getRight();
            if (left != null) {
                queue.add(new Node_Level(left, curLevel + 1));
            }
            if (right != null) {
                queue.add(new Node_Level(right, curLevel + 1));
            }
        }
        return result;
    }
    
    public double getAverage() {
        int n = 0;
        double sum = 0;
        if (root == null) {
            return 0;
        } else {
            LinkedList<IntBstNode> queue = new LinkedList<>();
            queue.addLast(root);
            while (!queue.isEmpty()) {
                IntBstNode node = queue.removeFirst();
                n++;
                sum += node.getKey();
                if (node.getLeft() != null) {
                    queue.addLast(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.addLast(node.getRight());
                }
            }
        }
        return sum/n;
    }
    
    private boolean removeLeaf(IntBstNode leaf) {
        if (leaf == null || !leaf.isLeaf()) {
            return false;
        }
        if (leaf == root) {
            root = null;
        } else {
            IntBstNode father = leaf.getFather();
            if (father.getLeft() == leaf) {
                father.setLeft(null);
            } else {
                father.setRight(null);
            }
        }
        return true;
    }
    
    private boolean removeOneChildNode(IntBstNode delNode) {
        if (delNode == null || !delNode.hasOneChild()) {
            return false;
        }
        if (delNode == root) {
            if (root.getLeft() == null) {
                // remove right
                root = root.getRight();
            } else {
                // remove left
                root = root.getLeft();
            }
            root.setFather(null);
        } else {
            IntBstNode grandFather = delNode.getFather();
            
            IntBstNode grandChild;
            if (delNode.getLeft() == null) {
                // child is right
                grandChild = delNode.getRight();
            } else {
                // child is left
                grandChild = delNode.getLeft();
            }
            
            if (grandFather.getLeft() == delNode) {
                // solving in left side
                grandFather.setLeft(grandChild);
            } else {
                // solving in right side
                grandFather.setRight(grandChild);
            }
            grandChild.setFather(grandFather);
        }
        return true;
    }
    
    private boolean deleteByMerging(IntBstNode delNode) {
        if (!delNode.hasTwoChildren()) {
            return false;
        }
        
        IntBstNode leftGrandChild = delNode.getLeft();
        IntBstNode rightGrandChild = delNode.getRight();
       
        // determin the rightMost node of the left subtree
        IntBstNode rightMost = leftGrandChild;
        while (rightMost.getRight() != null) {
            rightMost = rightMost.getRight();
        }
        
        // connect rightGrandChild to rightMost of leftGrandChild
        rightMost.setRight(rightGrandChild);
        rightGrandChild.setFather(rightMost);
        
        // remove delNode
        if (delNode == root) {
            root = root.getLeft();
            root.setFather(null);
        } else {
            IntBstNode grandFather = delNode.getFather();
            if (grandFather.getLeft() == delNode) {
                // solving left side
                grandFather.setLeft(leftGrandChild);
            } else {
                // solving right side
                grandFather.setRight(leftGrandChild);
            }
            leftGrandChild.setFather(grandFather);
        }
        return true;
    }
    
    public boolean deleteByMerging(int x) {
        IntBstNode delNode = search(x);
        if (delNode == null) {
            return false;
        }
        if (delNode.isLeaf()) {
            return removeLeaf(delNode);
        }
        if (delNode.hasOneChild()) {
            return removeOneChildNode(delNode);
        }
        return deleteByMerging(delNode);
    }
    
    private boolean deleteByCopying(IntBstNode delNode) {
        if (!delNode.hasTwoChildren()) {
            return false;
        }
        
        IntBstNode rightMost = delNode.getLeft();
        while (rightMost.getRight() != null) {
            rightMost = rightMost.getRight();
        }
        
        delNode.setKey(rightMost.getKey());
        
        if (rightMost.isLeaf()) {
            removeLeaf(rightMost);
        } else if (rightMost.hasOneChild()) {
            removeOneChildNode(rightMost);
        }
        return true;
    }
    
    public boolean deleteByCopying(int x) {
        IntBstNode delNode = search(x);
        if (delNode == null) {
            return false;
        }
        if (delNode.isLeaf()) {
            return removeLeaf(delNode);
        }
        if (delNode.hasOneChild()) {
            return removeOneChildNode(delNode);
        }
        return deleteByCopying(delNode);
    }
}
