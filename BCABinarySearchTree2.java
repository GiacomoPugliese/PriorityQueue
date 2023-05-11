import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BCABinarySearchTree2<E extends Comparable<E>> {
    public BCATreeNode<E> root = null;

    /**
     * insert element 'e' into the BST. If 'e' already exists in the tree, the count on the node will increase. Otherwise, a new node is added to the tree with a count of 1;
     * @param e
     */
    public void insert(E e){
        //node being inserted
        BCATreeNode<E> ret = new BCATreeNode<E>(e);

        //check for tree is empty case
        if(root == null){
            root = ret;
            return;
        }

        //initialize pointers
        BCATreeNode<E> current = root;
        BCATreeNode<E> parent = null;

        //iterate through binary tree
        while(current != null){
            parent = current;
            int compare = e.compareTo(current.element);
            if(compare == 0){
                current.incrementCount();
            }
            if(compare < 0){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }

        //final insertion (must be either to left or right of parent node)
        if(e.compareTo(parent.element) < 0){
            parent.left = ret;
        }
        else{
            parent.right = ret;
        }
    }

    /*
     * Returns the minimum element in the tree. Calls reursive private method as part of the implementation.
     * Throws NoSuchElementException if the tree is empty.
     */
    public E getMinimum() {
        if(root == null){
            throw new NoSuchElementException();
        }
        return getMinimum(root);
    }

    private E getMinimum(BCATreeNode<E> n){
        // Recursively find the minimum element in our tree!
        if(n.left == null){
            return n.element;
        }
        return getMinimum(n.left);
        
    }

    /**
     * Returns the largest element in the tree; implemented without recursion!
     */
    public E getMaximum() {
        if(root == null){
            throw new NoSuchElementException();
        }
        BCATreeNode<E> ret = root;
        while(ret.right != null){
            ret = ret.right;
        }
        return ret.element;
    }

    /**
     * @return a count of the number of elements in the tree. Implemented using recursion.
     */
    public int getSize() {
        return getSize(root);
    }

    private int getSize(BCATreeNode<E> n){
        if(n == null){
            return 0;
        }
        return 1 + getSize(n.left) + getSize(n.right);
    }

    /**
     * Determine if the element e is in the tree. Implemented using recusion. 
     */
    public boolean contains(E e){
        return contains(e, root);
    }

    private boolean contains(E e, BCATreeNode<E> n){
        if(n == null){
            return false;
        }
        if(n.equals(e)){
            return true;
        }
        return contains(e, n.left) || contains(e, n.right);
    }

    /**
     * Print the tree using an inorder traversal. 
     * The public method calls the partner recursive private method.
     */
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(BCATreeNode<E> n) {
        if(n == null){
            return;
        }

        printInOrder(n.left);
        System.out.println(n.element);
        printInOrder(n.right);
    }

    /**
     * Prints the tree using a preorder traversal. 
     * Implemented recusively.
     */
    public void printPreOrder(){
        printPreOrder(root);
    }

    private void printPreOrder(BCATreeNode<E> n){
        if(n == null){
            return;
        }

        System.out.println(n.element);
        printPreOrder(n.left);
        printPreOrder(n.right);
    }

    /**
     * Prints the tree using a postorder traversal.
     * Implements it recursively.
     */
    public void printPostOrder() {
        printPostOrder(root);
    }

    private void printPostOrder(BCATreeNode<E> n){
        if(n == null){
            return;
        }

        printPreOrder(n.left);
        printPreOrder(n.right);
        System.out.println(n.element);
    }

    /**
     * Returns a count of the number of leaf nodes in the tree. 
     * Implemented using recursion
     * @return
     */
    public int getLeafNodeCount() {
        return getLeafNodeCount(root);
    }

    private int getLeafNodeCount(BCATreeNode<E> n){
        if(n == null){
            return 0;
        }
       if(n.left == null && n.right == null){
            return 1;
       }
       return getLeafNodeCount(n.left) + getLeafNodeCount(n.right);

    }

    /**
     * Returns a list of leaf nodes, ordered from smallest to largest.
     * @return
     */
    public ArrayList<E> getLeafNodeList() {
        ArrayList<E> ret = new ArrayList<E>();
        getLeafNodeList(ret, root);
        return ret;
    }

    private void getLeafNodeList(ArrayList<E> list, BCATreeNode<E> n){
        if(n == null){
            return;
        }
        if(n.left == null && n.right == null){
            list.add(n.element);
            return;
        }
        getLeafNodeList(list, n.left);
        getLeafNodeList(list, n.right);
    }

    	/**
	 * Provides a "pretty" representation of the tree using a pre-order traversal
	 * 
	 * Indents 2 spaces for each level of the tree. Labels each traversal as right
	 * or left.
	 * Example:
	 * 
	 * Root: E (2)
	 *   Left: A (1)
	 *   Rght: S (2)
	 *     Left: Q (1)
	 *       Left: I (1)
	 *         Rght: O (1)
	 *           Left: N (1)
	 *     Rght: Y (1)
	 *       Left: U (1)
	 *         Left: T (1)
	 * 
	 */
	public String toString() {
		return printTree(root, "", "ROOT");
	}

	private String printTree (BCATreeNode<E> n, String indent, String prefix) {
		if(n == null){
            return "";
        }

        String ret = prefix + ": " + n.element + " (" + n.count + ")\n";
        ret += printTree(n.left, indent + "    ", "LEFT");
        ret += printTree(n.right, indent + "    ", "RIGHT");
        return ret;
	}

}
