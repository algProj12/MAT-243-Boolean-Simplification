

package booleancalculator;

import java.io.*;
import java.util.Stack;

/**
 * Name: David Plotzke
 * Date: Mar 4, 2017
 * Assignment:
 * Description:
 */
public class BooleanCalculator {

    
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader 
                (new InputStreamReader(System.in));
        InFixParser parser;
        TruthTable refTable;
        
        String input,
               output,
               variables;  
        int intVar;
        
        input = stdin.readLine();
        parser = new InFixParser(input.replaceAll("\\s+", ""));
        
        output = parser.toPostFix();
        
        System.out.println("enter the number of variables");
        variables = stdin.readLine();
        
        intVar = Integer.parseInt(variables);
        refTable = new TruthTable(intVar);
        
        System.out.println(output);
        
       int[] sizeOfGroup;
        int[][][] allGroups;
        
        sizeOfGroup = new int[intVar + 1];
        allGroups = new int[intVar + 1][][];
        
        refTable.countBoolTab();
        
        // create an array holding the size of the group
        for(int i = 0; i < (int)(Math.pow(2, intVar)); i++){
            sizeOfGroup[refTable.numberOfOnes[i]]++;
        }
        
        // create an array to hold the group
        for(int i = 0; i < intVar + 1; i++){
            if(sizeOfGroup[i] != 0){
                allGroups[i] = new int[sizeOfGroup[i]][];
            }
        }
        
        int[] intRow;
        boolean[] boolRow;
        int[] counter = new int[intVar + 1];
        BinaryTree[][] terms = new BinaryTree[intVar + 1][];
        int[][] xVals = new int[intVar + 1][];
        int counter1 = 1;
        int count = 1;
        
        // generate array of terms
        for(int i = 0; i < sizeOfGroup.length; i++){
            terms[i] = new BinaryTree[sizeOfGroup[i]];
            xVals[i] = new int[sizeOfGroup[i]];
            
            for(int j = 0; j < terms[i].length; j++){
                BinaryTree temp = new BinaryTree();
                terms[i][j] = temp;
            }
            
        }
        
        
        for(int i = 0; i < (int)Math.pow(2, intVar); i++){
            
            boolRow = parseEquation(refTable.createBoolLine(i), intVar, output);
            
            if(boolRow[intVar] == true){
                
                intRow = refTable.createIntLine(i);
                
                // fill the tree with terms
                terms[refTable.numberOfOnes[i]][count].addValue(counter1);
                terms[refTable.numberOfOnes[i]][count].findValue(counter1).
                        setIntElement(counter1);
                        
                allGroups[refTable.numberOfOnes[i]][counter[refTable.
                        numberOfOnes[i]]] = intRow;
                
                counter[refTable.numberOfOnes[i]]++;
                count++;
            }
            counter1++;
        }
        
        int j = 1;
        int totalGroup = 0;
        
        for(int i = 0; i < sizeOfGroup.length; i++){
            totalGroup += sizeOfGroup[i];
        }
        
        
        
        for(int i = 0; i < allGroups.length-1; i++){
            BinaryTree temp = new BinaryTree();
            
            
            while(allGroups[i] == null || allGroups[j] == null){
                if(allGroups[i] == null){
                    i++;
                    j++;
                }
                else if(allGroups[j] == null){
                    j++;
                }
                
                
                
        
        /*
        double previousTime = 0;
        for(int i = 1; i < 32768; i++){
            refTable.createBoolLine(i);
            System.out.println(System.nanoTime() - previousTime);
            previousTime = System.nanoTime();
        }
        */
        
        /*
        testTree.addValue(1);
        testTree.addValue(9);
        testTree.addValue(6);
        */
        
        /*
        double previousTime = 0;
        for(int i = 0; i < 1000000; i++){
            testTree.addValue(i);
            System.out.println(testTree.findValue(i).getKey());
            System.out.println(System.nanoTime() - previousTime);
            previousTime = System.nanoTime();
        }
        */
        
        /*
        final int constVal = 15;
        int[] blah = new int[constVal];
        int[] blaha = new int[constVal];
        Random chocolate = new Random();
        
        
        for(int j = 0; j < constVal; j++){
           blah[j] = chocolate.nextInt(); 
           blaha[j] = chocolate.nextInt();
        }
        
        double beforeTime = System.nanoTime();
        for(int i = 0; i < constVal; i++){
            if(blah[i] != blaha[i]){
            }
        }
        System.out.println(System.nanoTime()-beforeTime);
        */
        
        
        /*
        refTable.createBoolLine(4);
        
        refTable.boolRow = parseEquation(refTable.boolRow, intVar, output);
        
        for(int i = 0; i < intVar+1; i++){
            String printVal = Boolean.toString(refTable.boolRow[i]);
            System.out.print(printVal);
        }
        */
        
        /*
        if(output.charAt(0) == 'a'){
            refTable.createBoolTab();
            
            refTable.createIntTab();
            
            refTable.boolTable = parseEquation(refTable.boolTable, intVar, output);
            
            refTable.convertToInt();
            refTable.printIntTab();
        }
        
        else{
            System.out.println(parseEquation(output, intVar));
        }
        */
        
        
    }
    
    private static boolean[] parseEquation(boolean[] refTable, int intVar, 
            String input){
        
        final Stack<Boolean> boolStack = new Stack<>();
        
        
        for(int j = 0; j < input.length(); j++){
            int counter = 0;
            char chara;
            boolean first;
            boolean second;
            chara = input.charAt(j);

            switch(chara){
                case '+':
                    first = boolStack.pop();
                    second = boolStack.pop();
                    boolStack.push(first || second);
                break;
                case '*':
                    first = boolStack.pop();
                    second = boolStack.pop();
                    boolStack.push(first && second);
                break;
                case '!':
                    boolStack.push(!boolStack.pop());
                break;
                default:
                    boolStack.push(refTable[counter]);
                    counter++;
            }
        }
        refTable[intVar] = boolStack.pop();
        return refTable;
    }
    
    /**
     * Description: determines the answers to a boolean equation of postfix
     * @param refTable
     * @param intVar
     * @param input
     * @return 
     */
    
    private static boolean[][] parseEquation(boolean[][] refTable, int intVar, 
            String input){
        
        final Stack<Boolean> boolStack = new Stack<>();
        
        for(int i = 0; i < Math.pow(2, intVar); i++){
            for(int j = 0; j < input.length(); j++){
                char chara;
                boolean first;
                boolean second;
                chara = input.charAt(j);

                switch(chara){
                    case '+':
                        first = boolStack.pop();
                        second = boolStack.pop();
                        boolStack.push(first || second);
                    break;
                    case '*':
                        first = boolStack.pop();
                        second = boolStack.pop();
                        boolStack.push(first && second);
                    break;
                    case '!':
                        boolStack.push(!boolStack.pop());
                    break;
                    default:
                        boolStack.push(refTable[i][(Character.
                                getNumericValue(chara))-10]);
                }
            }
            refTable[i][intVar] = boolStack.pop();
        }
        return refTable;
    }
    
    
