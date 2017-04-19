
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
        BinaryTree<BinaryTree<Integer>> testTree = new BinaryTree();
        
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
//        double previousTime = System.nanoTime();
        
        
        /*
        for(int i = 0; i < 1000000; i++){
            BinaryTree<Integer> blah = new BinaryTree();
            Integer blaha = new Integer(i);
            testTree.addNode(i);
            testTree.setElement(i, blah);
            testTree.getElement(i).addEleNode(i, blaha);
        }
        */
        
//        /*
    //--------------------------------------------------


    BinaryTree<Integer[]>[] allGroups = new BinaryTree[intVar + 1];
    BinaryTree<BinaryTree<Integer>>[] terms = new BinaryTree[intVar + 1];

    refTable.countBoolTab();

    //store binarytree objects in each of the groups
    for(int i = 0; i < intVar + 1; i++){
        BinaryTree<Integer[]> arrayTemp = new BinaryTree();
        BinaryTree<BinaryTree<Integer>> temp = new BinaryTree();

        allGroups[i] = arrayTemp;
        terms[i] = temp;
    }


    //-----------------------------------------------------


    int[] intRow;
    Integer[] integerRow;
    boolean[] boolRow;
    int[] counter = new int[intVar + 1];



    for(int i = 0; i < (int)Math.pow(2, intVar); i++){

        boolRow = parseEquation(refTable.createBoolLine(i), intVar, output);

        if(boolRow[intVar] == true){

            intRow = refTable.createIntLine(i);

            integerRow = new Integer[intRow.length];

            for(int j = 0; j < intRow.length; j++){
                integerRow[j] = intRow[j];
            }

            // fill the tree with terms
            terms[refTable.numberOfOnes[i]].addNode(counter[refTable.
                    numberOfOnes[i]]+1);

            BinaryTree temp = new BinaryTree();

            terms[refTable.numberOfOnes[i]].setElement(counter[refTable.
                    numberOfOnes[i]]+1, temp);

            terms[refTable.numberOfOnes[i]].getElement(counter[refTable.
                    numberOfOnes[i]]+1).addEleNode(terms[refTable.
                            numberOfOnes[i]].getElement(counter[refTable.
                            numberOfOnes[i]]+1).getCounter()+1, i+1);
                    

            allGroups[refTable.numberOfOnes[i]].addEleNode(counter[refTable.
                    numberOfOnes[i]]+1, integerRow);

                    

            counter[refTable.numberOfOnes[i]]++;
            
        }
        if(i == (int)Math.pow(2, intVar)-1 ){
                System.out.println("blah");
            }
    }



    //---------------------------------------------------------

    int groupCounter = 0; 
    int count = 0;

    for(int i = 0; i < intVar + 1; i++){
        if(allGroups[i].getCounter() != 0){
            groupCounter++;
        }
    }

    BinaryTree<Integer[]>[] finalAllGroups = new BinaryTree[groupCounter];
    BinaryTree<BinaryTree<Integer>>[] finalTerms = new BinaryTree[groupCounter];
    BinaryTree<Integer[]>[] xVals = new BinaryTree[groupCounter];

    for(int i = 0; i < groupCounter; i++){
        BinaryTree<Integer[]> xTemp = new BinaryTree();
        xVals[i] = xTemp;
    }

    for(int i = 0; i < intVar + 1; i++){
        if(allGroups[i].getCounter() !=0){
            finalAllGroups[count] = allGroups[i];
            finalTerms[count] = terms[i];
            count++;
        }
    }

    allGroups = null;
    terms = null;

    //----------------------------------------------------------

    int tempSize;
    int iterations = finalAllGroups.length;
    int counter1 = 0;

    // create the list of combined factors
    for(int o = 0; o < iterations - 1; o++){
        groupCounter--;

        BinaryTree<Integer[]>[] nextAllGroups = new BinaryTree[groupCounter];
        BinaryTree<BinaryTree<Integer>>[] nextTerms = new BinaryTree
                [groupCounter];
        BinaryTree<Integer[]>[] nextXVals = new BinaryTree[groupCounter];



        for(int i = 0; i < finalAllGroups.length-1; i++){

            counter1 = 0;


            // choose the groups

            BinaryTree<Integer[]> arrayTemp = new BinaryTree();
            BinaryTree<BinaryTree<Integer>> temp = new BinaryTree();
            BinaryTree<Integer[]> xTemp = new BinaryTree();



            for(int k = 1; k < finalAllGroups[i].getCounter()+1; k++){
                for(int l = 1; l < finalAllGroups[i+1].getCounter()+1; l++){
                    
                    int count1 = 1;
                    count = 1;
                    boolean sameVal = false;
                    boolean xTrue = true;
                    
                    // edited for exit should xTrue equal false before the end
                    // of the string
                    for(int m = 0; m < o && xTrue == true; m++){
                        xTrue = xVals[i].getElement(k)[m].equals(xVals[i+1].
                                getElement(l)[m]);
                    }


                    while(finalTerms[i+1].getElement(l).getElement(1)> 
                            finalTerms[i].getElement(k).getElement(1) && 
                            (finalTerms[i+1].getElement(l).getElement(1) -
                            finalTerms[i].getElement(k).getElement(1)) > 
                            count){

                        count *= 2;
                    }


                    // determine if it is equal
                    if(xTrue && count == finalTerms[i+1].getElement(l).
                        getElement(1)-finalTerms[i].getElement(k).
                        getElement(1)){
                        
                        int diffVal = 0;

                        // determining what index of the term is different
                        while(finalAllGroups[i].getElement(k)[diffVal].
                                equals(finalAllGroups[i+1].getElement(l)
                                [diffVal])){

                            diffVal++;
                        }
                        
                        Integer[] temp2 = new Integer[finalAllGroups[i].
                                getElement(k).length];
                        
                        arrayTemp.addEleNode(counter1+1, temp2);
                        
                        for(int r = 0; r < finalAllGroups[i].getElement(k).
                                length; r++){
                            arrayTemp.getElement(counter1+1)[r] = finalAllGroups
                            [i].getElement(k)[r]; 
                        }
                        
                        arrayTemp.getElement(counter1+1)[diffVal] = 2;
                        
                        if(o > 0){
                            for(int r = 1; r < arrayTemp.getCounter() && sameVal
                                    == false; r++){
                                
                                
                                sameVal = true;
                                for(int s = 0; s < arrayTemp.getElement(r).length 
                                        && sameVal == true; s++){
                                    sameVal = arrayTemp.getElement(arrayTemp.
                                            getCounter())[s].equals
                                            (arrayTemp.getElement(r)[s]); 
                                }
                            }
                        }
                        
                        if(sameVal == false){
                            // setting the new term to a temp residence

                            BinaryTree<Integer> temp1 = new BinaryTree();

                            temp.addEleNode(counter1+1, temp1);


                            // merging the term values
                            for(int r = 1; r < finalTerms[i].getElement(k).getCounter()+1; r++){

                                temp.getElement(counter1+1).addEleNode(count1, 
                                finalTerms[i].getElement(k).getElement(r));
                                count1++;
                            }

                            for(int r = 1; r < finalTerms[i+1].getElement(l).getCounter()+1; r++){
                                temp.getElement(counter1+1).addEleNode(count1, 
                                finalTerms[i+1].getElement(l).getElement(r));
                                count1++;
                            }

                            if(o == 0){
                                Integer[] arrTemp = new Integer[intVar];
                                xVals[i].addEleNode(counter1+1, arrTemp);
                                xVals[i].getElement(counter1+1)[o] = diffVal;
                                xTemp.addEleNode(counter1+1, xVals[i].getElement(counter1+1));
                            }
                            else{
                                xVals[i].getElement(k)[o] = diffVal;

                                xTemp.addEleNode(counter1+1, xVals[i].getElement(k));
                            }

                            counter1++;
                        }
                        else
                            arrayTemp.deleteVal(counter1+1);
                    }
                }
           }

            if(i == 0 && o == 1){
                System.out.println("blah");
            }
            
            if(i == 1 && o == 0){
                System.out.println("blah");
            }
            if(counter1 != 0){
                nextAllGroups[i] = arrayTemp;
                nextTerms[i] = temp;
                nextXVals[i] = xTemp;
                finalAllGroups[i] = null;
                finalTerms[i] = null;
                xVals[i] = null;

            }

        }
        if(counter1 != 0){
            finalAllGroups = nextAllGroups;
            finalTerms = nextTerms;
            xVals = nextXVals;
        }
    }
        
//        */
        
        /*
        
        BinaryTree groups = new BinaryTree();
 
        
        refTable.countBoolTab();
        boolean[] boolRow;
        int[] intRow;
        int counter = 0;
        int count = 0;
        BinaryTree terms = new BinaryTree();
        
        
        for(int i = 0; i < (int)(Math.pow(2, intVar)); i++){
            
            boolRow = parseEquation(refTable.createBoolLine(i), intVar, output);
            
            if(boolRow[intVar] == true){
                
                intRow = refTable.createIntLine(i);
                
                groups.
            }
            counter++;
        }
        
        counter = 1;
        
        for(int i = 0; i < groups.length-1; i++){
            
            BinaryTree temp = new BinaryTree();
            
            for(int j = 1; j < groups.length; j++){
                while(groups[i] == null || groups[j] == null){
                    if(groups[i] == null){
                        i++;
                        j++;
                    }
                    else if(groups[j] == null){
                        j++;
                    }
                }
                
                count += groups[i].getCounter();
                
                for(int k = 0; k < groups[i].getCounter(); k++){
                    for(int l = 0; l < groups[l].getCounter(); l++){
                        if(counter < terms[j].findValue(k).getIntElement()){
                            counter *= 2;
                        }
                        else if(counter == groups[j].getCounter()){
                            for(int m = 0; m < intVar - 1; m++){
                                if(groups[i].findValue(k).getElement()[m] != 
                                        groups[j].findValue(l).getElement()[m]){
                                    boolean[] current = new boolean[intVar];
                                    current = groups[i].findValue(k).getElement();
                                    current[m] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        
        
        
        
        
        
        double previousTime = 0;
        for(int i = 1; i < 32768; i++){
            refTable.createBoolLine(i);
            System.out.println(System.nanoTime() - previousTime);
            previousTime = System.nanoTime();
        }
        */
        
        /*
        testTree.addNode(1);
        testTree.addNode(9);
        testTree.addNode(6);
        */
        
        /*
        double previousTime = 0;
        for(int i = 0; i < 1000000; i++){
            testTree.addNode(i);
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
    
    /**
     * Description: determines the answers to a boolean equation of postfix
     * @param refTable
     * @param intVar
     * @param input
     * @return 
     */
    
    /*
    
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
    
    */
    
    private static boolean[] parseEquation(boolean[] refTable, int intVar, 
            String input){
        
        final Stack<Boolean> boolStack = new Stack<>();
        
        int counter = 0;
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
                    boolStack.push(refTable[counter]);
                    counter++;
            }
        }
        refTable[intVar] = boolStack.pop();
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
