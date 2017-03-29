

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
    
    /**
     * Description: determines the answer to a mathematical equation of postfix
     * @param input
     * @param intVar
     * @return 
     */
    
    public static double parseEquation(String input, int intVar){
        
        final Stack<Double> doubleStack = new Stack<>();
        
        for(int i = 0; i < Math.pow(2, intVar); i++){
            for(int j = 0; j < input.length(); j++){
                char chara;
                chara = input.charAt(j);
                double topStack;

                switch(chara){
                    case '+':
                        doubleStack.push(doubleStack.pop() + doubleStack.pop());
                    break;
                    case '*':
                        doubleStack.push(doubleStack.pop()*doubleStack.pop());
                    break;
                    case '-':
                        topStack = doubleStack.pop();
                        doubleStack.push(doubleStack.pop()-topStack);
                    break;
                    case '/':
                        topStack = doubleStack.pop();
                        
                        doubleStack.push(doubleStack.pop()/topStack);
                    break;
                    default:
                        doubleStack.push(Double.parseDouble(Character.toString(chara)));
                }
            }
        }
        return doubleStack.pop();
    }
}
