package booleancalculator;



/**
 * Name: David Plotzke
 * Date: Apr 3, 2017
 * Assignment:
 * Description:
 */
public class BinaryTree {
    
    private int permMax = 4;
    private int permMin = 0;
    
    private treeNode root = new treeNode(4);
    
    public void addValue(int addVal){
        
        int maxVal = permMax;
        int minVal = permMin;
        
        while(addVal > maxVal || addVal < minVal){
            if(addVal > maxVal && maxVal != 4){
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
            else
                return;

        }
        
    }
    
    public treeNode findValue(int searchVal){
        treeNode current = root;
        
        while(current.getKey() != searchVal){
            if(searchVal > current.getKey()){
            current = current.getRightBranch();
            }
            else if(searchVal < current.getKey()){
                current = current.getLeftBranch();
            }
        }
        return current;
    }
    
    public void deleteVal(int searchVal){
        
    }
    
    
    
}
