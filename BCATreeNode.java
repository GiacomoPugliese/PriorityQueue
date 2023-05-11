public class BCATreeNode<E extends Comparable<E>> {
    public E element;
    public int count;
    public BCATreeNode<E> left = null;
    public BCATreeNode<E> right = null;

    public BCATreeNode(E element){
        this.element = element;
        this.count = 1;
    }

    public void incrementCount() {
        count++;
    }



}
