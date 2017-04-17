package booleancalculator;



/**
 * Name: David Plotzke
 * Date: Apr 3, 2017
 * Assignment:
 * Description:
 */
public class treeNode<T> {
    private int key;
    private treeNode leftBranch;
    private treeNode rightBranch;
    private T element;
    
    public treeNode(){
        
    }
    
    public treeNode(int key){
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setLeftBranch(treeNode leftBranch) {
        this.leftBranch = leftBranch;
    }

    public void setRightBranch(treeNode rightBranch) {
        this.rightBranch = rightBranch;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public treeNode getLeftBranch() {
        return leftBranch;
    }

    public treeNode getRightBranch() {
        return rightBranch;
    }

    public T getElement() {
        return element;
    }
    
    

    
}

}
