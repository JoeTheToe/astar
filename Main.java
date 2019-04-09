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
		int height = -1;
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

		startState = new State(inputStateArr, 0);
		finalState = new State(finalStateArr, 0);
		open = new ArrayList<State>();
		closed = new ArrayList<State>();
		ArrayList<State> pathTaken = new ArrayList<State>();

		open.add(startState);

		while (open.size() != 0) {
			height++;
			// find the node on the open list with the lowest f value, call it current
			State current = open.get(0); // assuming leftmost is the best heuristic value

			// remove from the open list and add to closed
			open.remove(0);
			closed.add(current);

			if (current.getArr() == finalState.getArr()) { // when final state reached, backtrack and return path
				returnPath(current, closed);
				break;
			}

			// generate children
			byte[] moves = current.getMoves();
			ArrayList<State> list = new ArrayList<State>();
			list = current.getStates(moves, height, current); // generate children

			// for each successor
			for (int i = 0; i < list.size(); i++) {
				if (closed.contains(list.get(i))) { // if successor is already evaluated ignore
					continue;
				}

				int tempG = current.getHeight() + (list.get(i).getHeight() - current.getHeight()); // + distance between successor and current

				if (open.contains(list.get(i))) {
					if (tempG > list.get(i).getHeight()) {
						continue;
					}
				}
				open.add(list.get(i));

				// re order open list in heuristic value order
				Collections.sort(open, new Comparator<State>() {
				    @Override
				    public int compare(State s1, State s2) {
				        if (s1.getHeur(finalState) > s2.getHeur(finalState))
				            return 1;
				        if (s1.getHeur(finalState) < s2.getHeur(finalState))
				            return -1;
				        return 0;
				    }
				});
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
	}
	
	public static void returnPath(State current, ArrayList<State> closed) {
		boolean ancestors = true;
		while(ancestors) {
			
		}
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
