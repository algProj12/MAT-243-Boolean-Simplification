package booleancalculator;



/**
 * Name: David Plotzke
 * Date: Apr 3, 2017
 * Assignment:
 * Description:
 */
public class BinaryTree {
    
    private int maxVal;
    private int minVal;
    private int permMax = 4;
    private int permMin = 0;
    private int index = 0;
    private int permIndex = 0;
    
    treeNode root = new treeNode(4);
    
    public void addValue(int addVal){
        
        maxVal = permMax;
        minVal = permMin;
        
        if(addVal <= permMax && addVal >= permMin){
            
            treeNode current;
            current = root;
            
            while(addVal != current.getKey()){
                if(addVal < current.getKey()){
                    if(current.getLeftBranch() != null){
                        current = current.getLeftBranch();
                        maxVal = minVal + ((minVal+maxVal)/2);
                    }
                    else{
                        treeNode next = new treeNode();
                        next.setKey(minVal + ((minVal+maxVal)/2));
                        current.setLeftBranch(next);
                        maxVal = minVal + ((minVal+maxVal)/2);
                        current = next;
                    }
                }
                 
                else if(addVal > current.getKey()){
                    if(current.getRightBranch() != null){
                        current = current.getRightBranch();
                        minVal = minVal + ((minVal+maxVal)/2);
                    }
                    else{
                        treeNode next = new treeNode();
                        next.setKey(minVal + ((minVal+maxVal)/2));
                        current.setRightBranch(next);
                        minVal = minVal + ((minVal+maxVal)/2);
                        current = next;
                    }
                }
                else
                    return;
                
            }
        }
        
        else if (addVal > permMax){
            if(permIndex >= index){
                permIndex++;
                treeNode temp = root;
                permMin = permMax;
                permMax *= 2;
                root = new treeNode(permMax);
            }
            index++;
            
            treeNode temp = root;
            permMin = permMax;
            permMax *= 2;
        }
        else{
            treeNode temp = root;
        }
    }
    
    public void findValue(int searchVal){
        
    }
    
    public void deleteVal(int searchVal){
        
    }
    
    
    
}
