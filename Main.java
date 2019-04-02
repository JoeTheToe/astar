// Joseph Tobin - 16163842
// Neale Conway - 16192206
// John Long - 12132306

import javax.swing.JOptionPane;
import java.util.*;


public class Main {

	static byte size = 8;
	static State finalState;
	static State startState;
	static ArrayList<State> open;
	static ArrayList<State> closed;
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

		// MAIN GAME LOOP WILL BE IMPLEMENTED BELOW, FOR NOW IT JUST SHOWS THE FIRST MOVE

		startState = new State(inputStateArr, height);
		finalState = new State(finalStateArr, height);
		open = new ArrayList<State>();
		closed = new ArrayList<State>();

		open.add(startState);

		while (open.length != 0) { // while open is not empty
			State current = open.get(0); // get leftmost which should be best heuristic value
			if (current == finalState) { break; } // when final state reached

			open.remove(0);// remove current from open list
			closed.add(current); // add current to the closed list

			byte[] moves = current.getMoves();
			ArrayList<State> list = new ArrayList<State>();
			list = current.getStates(moves); // generate children


			for (int i = 0; i < list.length; i++) { // for each child do ...
				if () { // if child is not on closed
					// add to open list
					// calculate heuristic...
					// .....
				}
			}


		}
		/*byte[] moves = startState.getMoves();
		ArrayList<State> list = new ArrayList<State>();
		list = startState.getStates(moves);
		if (validEndState && validStartState) {
		print(moves);

		for(int j = 0; j < list.size(); j++){
			System.out.println(list.get(j).getHeur(finalState));
		}
	}*/

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
						JOptionPane.showMessageDialog(
			        null, "Duplicates found", "Error", JOptionPane.ERROR_MESSAGE);
						isDuplicate = true;
					}
				}
				if (!isDuplicate) {
					return true;
				}
			} else {
				JOptionPane.showMessageDialog(
	        null, "Input must be numbers in the range 0-8, space separated", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(
        null, "Input must be 9 numbers", "Error", JOptionPane.ERROR_MESSAGE);
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
