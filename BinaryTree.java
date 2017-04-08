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
    
    private treeNode root = new treeNode(2);
    
    public void addValue(int addVal){
        
        int maxVal = permMax;
        int minVal = permMin;
        
        while(addVal > maxVal || addVal < minVal){
            if(addVal > minVal){
                treeNode temp = root;
                permMin = permMax;
                permMax *= 2;

                minVal = maxVal;
                maxVal *= 2;

                root = new treeNode(permMax/2);
                root.setLeftBranch(temp);
            }
            
            else{
                maxVal = minVal;
                minVal /= 2;
            }
        }
        

            
        treeNode current;
        current = root;
        boolean first = true;


        while(addVal != current.getKey()){
            if(addVal < current.getKey()){
                if(current.getLeftBranch() != null){
                    current = current.getLeftBranch();
                    maxVal = (minVal+maxVal)/2;
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
                    minVal = (minVal+maxVal)/2;
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
    
    public void findValue(int searchVal){
        
    }
    
    public void deleteVal(int searchVal){
        
    }
    
    
    
}
