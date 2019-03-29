import javax.swing.JOptionPane;
import java.util.*;


public class Main {

	static byte size = 8;
	static State finalState;
	static State startState;
	static State[] open;
	static State[] closed;
	static byte[] inputStateArr = new byte[9];
	static byte[] finalStateArr = new byte[9];

	public static void main(String[] args)  {
		int height = 0;
		boolean validStartState = false;
		boolean validEndState = false;
		String start = "";
		String end = "";
		
		try {

				while (!validStartState) {
					start = JOptionPane
							.showInputDialog("Enter start state, 9 unique numbers from 0 to 8 seperated by a space"); 
						validStartState = validateInput(start);
				}

				// INIT START State
				String[] tempArr1 = start.split("\\s+");
				for (int i = 0; i < tempArr1.length; i++ ){
					inputStateArr[i] = Byte.valueOf(tempArr1[i]);
				}

				// DEBUGGING PURPOSES
				/*for (int j = 0; j < inputStateArr.length; j++) {
					System.out.println(inputStateArr[j]);
				}*/

				while (!validEndState) {
					end = JOptionPane.showInputDialog(
							"Enter end/final state, 9 unique numbers from 0 to 8 seperated by a space");
						validEndState = validateInput(end);
				}

				// INIT END State
				String[] tempArr2 = end.split("\\s+");
				for (int i = 0; i < tempArr2.length; i++ ){
					finalStateArr[i] = Byte.valueOf(tempArr2[i]);
				}
		} catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Program terminated");;
		}

				// DEBUGGING PURPOSES
				/*for (int j = 0; j < finalStateArr.length; j++) {
					System.out.println(finalStateArr[j]);
				}*/

		startState = new State(inputStateArr, height);
		finalState = new State(finalStateArr, height);
		//open = new State[999]; 
		//closed = new State[999]; 
		byte[] moves = startState.getMoves();
		ArrayList<State> list = new ArrayList<State>();
		list = startState.getStates(moves);
		print(moves);
		
		for(int j = 0; j < list.size(); j++){
			System.out.println(list.get(j).getHeur(finalState));
		}
		
		//System.out.println(startState.getHeur(finalState));

	}

	public static boolean validateInput(String input) {
		String regex = "([0-8]\\s){8}[0-8]{1}";
		String[] inputArr = input.split("\\s+");
		String found = "";
		boolean isDuplicate = false;

		if (inputArr.length == 9) { 
			if (input.matches(regex)) { 
				for (int i = 0; i < inputArr.length && !isDuplicate; i++) { // Check duplicate values
					if (!(found.contains(inputArr[i]))) {
						found += inputArr[i];
					} else {
						isDuplicate = true;
					}
				}
				if (!isDuplicate) {
					return true;
				}
			}
		}
		return false;
	}

	public static void print(byte[] moves){
		String[] directions = {"West","East","South","North"};

		for(int i = 0; i < directions.length; i++){
			if(moves[i] > 0){
				System.out.println(moves[i] + " to the " + directions[i]);
			}
		}
  }

}
