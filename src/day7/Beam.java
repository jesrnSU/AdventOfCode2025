package day7;

public class Beam {
    private int startingIndex;
    private Beam leftChild;
    private Beam rightChild;

    public Beam(int index){
        this.startingIndex = index;
    }

    public void setChildren(int leftChildIndex, int rightChildIndex){
        leftChild = new Beam(leftChildIndex);
        rightChild = new Beam(rightChildIndex);
    }

    public int getStartingIndex() {
        return startingIndex;
    }

    public Beam getLeftChild() {
        return leftChild;
    }

    public Beam getRightChild() {
        return rightChild;
    }
}
