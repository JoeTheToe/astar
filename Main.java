import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class Main {

  static byte size = 8;
  static State finalState;
  static State[] open;
  static State[] closed;
  static int[] inputStateArr = new int[9];
  static int[] finalStateArr = new int[9];

  public static void main(String[] args) {
    int height = 0;
    String inputValidator = "([0-8]\\s){8}[0-8]";
    boolean validStartState = false;
    boolean validEndState = false;
    boolean contains = false;

    // Input validation
    while(!validStartState) {
    	String input = JOptionPane.showInputDialog("Enter start state, 9 unique numbers from 0 to 8 seperated by a space");
    	String[] inputArr = input.split("\\s+");
    	
    	for(int i=0; i < inputArr.length; i++) {
    		inputStateArr[i] = Integer.parseInt(inputArr[i]);
    	}
    	
    	for(int j=0; j < inputStateArr.length && !contains; j++) {
    		for(int k=j+1; k < inputStateArr.length && !contains; k++) {
    			if(inputStateArr[j] == inputStateArr[k]) contains = true;
    		}
    	}
    	
    	if(input.matches(inputValidator) && !contains) {
    		validStartState = true;
    	} else {
    		JOptionPane.showMessageDialog(null, "Incorrect input");
    	}
    }
    
    while(!validEndState) {
    	String input = JOptionPane.showInputDialog("Enter end/final state, 9 unique numbers from 0 to 8 seperated by a space");
    	String[] inputArr = input.split("\\s+");
    	
    	for(int i=0; i < inputArr.length; i++) {
    		finalStateArr[i] = Integer.parseInt(inputArr[i]);
    	}
    	
    	for(int j=0; j < finalStateArr.length && !contains; j++) {
    		for(int k=j+1; k < finalStateArr.length && !contains; k++) {
    			if(finalStateArr[j] == finalStateArr[k]) contains = true;
    		}
    	}
    	
    	if(input.matches(inputValidator) && !contains) {
    		validEndState = true;
    	} else {
    		JOptionPane.showMessageDialog(null, "Incorrect input");
    	}
    }
//    finalState = new State(finalStateArr, height);
    open = new State[999]; // What should this number be?
    closed = new State[999]; // What should this number be?

    System.out.println(finalState.getHeur());

  }

}
