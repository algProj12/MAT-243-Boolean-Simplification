package booleancalculator;



/**
 * Name: David Plotzke
 * Date: Apr 3, 2017
 * Assignment:
 * Description:
 */
public class treeNode {
    private int key;
    private int intElement;
    private treeNode leftBranch;
    private treeNode rightBranch;

    public treeNode(){
        
    }
    
    public treeNode(int key){
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public int getIntElement() {
        return intElement;
    }

    public treeNode getLeftBranch() {
        return leftBranch;
    }

    public treeNode getRightBranch() {
        return rightBranch;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setIntElement(int intElement) {
        this.intElement = intElement;
    }

    public void setLeftBranch(treeNode leftBranch) {
        this.leftBranch = leftBranch;
    }

    public void setRightBranch(treeNode rightBranch) {
        this.rightBranch = rightBranch;
    }

    
}
