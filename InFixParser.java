

package booleancalculator;

/**
 * Name: David Plotzke
 * Date: Mar 4, 2017
 * Assignment:
 * Description:
 */

import java.util.Stack;

public class InFixParser {
    private final String input;
    private final Stack<Character> stack = new Stack<>();
    int lastOrder = 0;
    private String output = "";
    
    InFixParser(String input){
        this.input = input;
    }
    
    /**
     * Description: converts to postfix notation 
     * @return 
     */
    
    public String toPostFix(){
        int counter = 0;
        for(int i = 0; i < input.length();i++){
            char chara;
            chara = input.charAt(i);
            
            switch(chara){
                case '+': case '-':
                    isOperator(chara,1);
                    lastOrder = 1;
                break;
                case '*': case '/':
                    isOperator(chara,2);
                    lastOrder = 2;
                break;
                case '(':
                    stack.push(chara);
                break;
                case ')':
                    isParen();
                break;
                case '!':
                    counter++;
                break;
                default:
                    if(counter > 0){
                        output += chara + "!";
                        counter--;
                    }
                    else
                        output += chara;
                    
            }
        }
        
        if(stack.peek().equals('(') || stack.peek().equals(')'))
            System.out.println("mismatched parenthesis");
        else{
            while(!stack.isEmpty()){
                output += stack.pop();
            }
        }
        return output;
    }
    
    /**
     * Description: determines what to do based on the operator
     * @param chara
     * @param priority 
     */
    
    private void isOperator(char chara, int priority){
        if(!stack.isEmpty() && stack.peek() != '('){
            if(lastOrder >= priority)
                output += stack.pop();
        }
        stack.push(chara);
    }
    
    /**
     * Description: determines what to do if you have an outer parenthesis
     */
    
    private void isParen(){
        while(!(stack.peek() == '(') && !stack.isEmpty()){
            output += stack.pop();
        }
        if(stack.isEmpty()){
            System.out.println("mismatched parenthesis");
        }
        else
            stack.pop();
    }
}
