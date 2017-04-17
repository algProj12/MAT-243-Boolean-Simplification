package booleancalculator;



/**
 * Name: David Plotzke
 * Date: Apr 3, 2017
 * Assignment:
 * Description:
 */
public class BinaryTree<T> {
    
    private int permMax = 4;
    private int permMin = 0;
    private int counter = 0;
    
    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
    
    private treeNode<T> root = new treeNode(4);
    
    public treeNode addNode(int addVal){
        
        counter++;
        
        int maxVal = permMax;
        int minVal = permMin;
        
        while(addVal >= maxVal || addVal < minVal){
            if(addVal >= maxVal && maxVal != 4){
                treeNode temp = root;
                permMin = permMax;
                permMax *= 2;

                minVal = maxVal;
                maxVal *= 2;

                root = new treeNode(permMax/2);
                root.setLeftBranch(temp);
            }
            
            else if(addVal < minVal){
                maxVal = minVal;
                minVal /= 2;
            }
            else{
                permMin = permMax;
                permMax *= 2;

                minVal = maxVal;
                maxVal *= 2;
            }
                
        }
            
        treeNode current;
        current = root;
        boolean first = true;


        while(addVal != current.getKey()){
            if(addVal < current.getKey()){
                if(current.getLeftBranch() != null){
                    
                    if(first != true){
                        maxVal = (minVal+maxVal)/2;
                    }
                    first = false;
                    current = current.getLeftBranch();
                }
                else{
                    if(first != true){
                        maxVal = (minVal+maxVal)/2;
                    }
                    treeNode next = new treeNode();
                    next.setKey((minVal+maxVal)/2);
                    current.setLeftBranch(next);
                    current = next;
                    first = false;
                }
            }

            else if(addVal > current.getKey()){
                if(current.getRightBranch() != null){
                    if(first != true)
                        minVal = (minVal+maxVal)/2;
                    first = false;
                    current = current.getRightBranch();
                }
                else{
                    if(first != true)
                        minVal = (minVal+maxVal)/2;
                    
                    treeNode next = new treeNode();
                    next.setKey((minVal+maxVal)/2);
                    current.setRightBranch(next);
                    current = next;
                    first = false;
                }
            }
            else{
                return current;
            }

        }
        return current;
    }
    
    public void addEleNode(int addVal, T element){
            addNode(addVal).setElement(element);
        }
    
    public void addTreeNode(int addVal, T element){
        
    }
    
    public void setElement(int searchVal, T element){
        treeNode current = root;
        
        while(current.getKey() != searchVal){
            if(searchVal > current.getKey()){
            current = current.getRightBranch();
            }
            else if(searchVal < current.getKey()){
                current = current.getLeftBranch();
            }
        }
        current.setElement(element);
    }
    
    public T getElement(int searchVal){
        treeNode current = root;
        
        while(current.getKey() != searchVal){
            if(searchVal > current.getKey()){
            current = current.getRightBranch();
            }
            else if(searchVal < current.getKey()){
                current = current.getLeftBranch();
            }
        }
        return (T)current.getElement();
    }
    
    public void deleteVal(int searchVal){
        treeNode current = root;
        
        while(current.getKey() != searchVal){
            if(searchVal > current.getKey()){
                current = current.getRightBranch();
            }
            else if(searchVal < current.getKey()){
                current = current.getLeftBranch();
            }
        }
        current.setElement(null);
    }
    
    
    
}
