

package booleancalculator;

/**
 * Name: David Plotzke
 * Date: Mar 6, 2017
 * Assignment:
 * Description:
 */
public class TruthTable {
    
    private final int variables;
    boolean[][] boolTable;
    int intTable[][];
    
    public TruthTable(int variables){
        this.variables = variables;
    }
    
    public void createBoolTab(){
        
        boolTable = new boolean [(int)(Math.pow(2, variables))]
                [variables + 1];
        
        for(int i = 0; i < variables; i++){
            int add = 0;
            for(int j = 0; j < (int)(Math.pow(2, variables)); j++){
                if((j)%(int)(Math.pow(2, i)) == 0){
                    add++;
                }
                if((add)%2 == 0){
                    boolTable[j][i] = true;
                }
            }
        }
    }
    
    public void printBoolTab(){
        
        for(int i = 0; i < Math.pow(2, variables); i++){
            for(int j = 0; j < variables + 1; j++){
                System.out.print(" " + Boolean.toString(boolTable[i][j]) + " ");
            }
            System.out.println();
        }
    }
    
    public void createIntTab(){
        intTable = new int [(int)(Math.pow(2, variables))]
                [variables + 1];
        
        for(int i = 0; i < variables; i++){
            int add = 0;
            for(int j = 0; j < (int)(Math.pow(2, variables)); j++){
                if((j)%(int)(Math.pow(2, i)) == 0){
                    add++;
                }
                if((add)%2 == 0){
                    intTable[j][i] = 1;
                }
            }
        }
    }
    
    public void convertToInt(){
        for(int i = 0; i < (int)(Math.pow(2, variables)); i++){
            if(boolTable[i][variables] == true){
                intTable[i][variables] = 1;
            }
            else
                intTable[i][variables] = 0;
        }
    }
    
    public void printIntTab(){
        for(int i = 0; i < Math.pow(2, variables); i++){
            for(int j = 0; j < variables + 1; j++){
                System.out.print(" " + Integer.toString(intTable[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
