import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BCABinarySearchTree<E extends Comparable<E>> {
    public BCATreeNode<E> root = null;

    /**
     * insert element 'e' into the BST. If 'e' already exists in the tree, the count on the node will increase. Otherwise, a new node is added to the tree with a count of 1;
     * @param e
     */
    public void insert(E e){
        // Create a new node
        BCATreeNode<E> newNode = new BCATreeNode<E>(e);

        // CASE 1: The root is null.
        if(root == null){
            root = newNode;
            return;
        }

        // Keep some pointers
        BCATreeNode<E> current = root;
        BCATreeNode<E> parent = null;

        // Keep traversing until current is null
        while(current != null){
            // Comparison 
            // using compareTo so that's why we have to extend Comparable interface
            int compare = e.compareTo(current.element);
            parent = current;

            // The two elements are the same
            if(compare == 0){
                current.incrementCount();
                return;
            }
            // new Node < currentNode
            else if(compare < 0){
                current = current.left;
            }
            // new Node > currentNode
            else {
                current = current.right;
            }
        }
        // Insert the node to the parent's left
        if(e.compareTo(parent.element) < 0){
            parent.left = newNode;
        }
        else {
            parent.right = newNode;
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

        // Call the private getMinimum()
        return getMinimum(root);
    }

    private E getMinimum(BCATreeNode<E> n){
        // Recursively find the minimum element in our tree!

        // Base case:
        if(n.left == null){
            return n.element;
        }

        // Recursive step:
        return getMinimum(n.left);
    }

    /**
     * Returns the largest element in the tree; implemented without recursion!
     */
    public E getMaximum() {
        // Check if the root null
        if(root == null){
            throw new NoSuchElementException();
        }

        BCATreeNode<E> n = root;

        while(n.right != null){
            n = n.right;
        }

        return n.element;
    }

    /**
     * @return a count of the number of elements in the tree. Implemented using recursion.
     */
    public int getSize() {
        return getSize(root);
    }

    private int getSize(BCATreeNode<E> n){
        // The tree is empty.
        if(n == null){
            return 0;
        }

        // Recusive step.
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
        // If the element we are looking for equals the current node we are at.
        if(n.element.equals(e)){
            return true;
        }

        // Recursive Step: Check the right side and left side for if the element is contained.
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

        // This is going all the way left.
        printInOrder(n.left);
        System.out.println(n.element);
        // Go to it's right.
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

        printPostOrder(n.left);
        printPostOrder(n.right);
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
        ArrayList<E> list = new ArrayList<>();
        getLeafNodeList(list, root);
        return list;
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
		if (n == null) {
			return "";
		}

		String s = indent + prefix + ": " + n.element + " (" + n.count + ")\n";
		s += printTree(n.left, indent + "  ", "LEFT");
		s += printTree(n.right, indent + "  ", "RGHT");
		return s;
	}

}
