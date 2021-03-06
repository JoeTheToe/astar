
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

	public static void main(String[] args) {
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
			for (int i = 0; i < tempArr1.length; i++) {
				inputStateArr[i] = Byte.valueOf(tempArr1[i]);
			}

			// DEBUGGING PURPOSES
			/*
			 * for (int j = 0; j < inputStateArr.length; j++) {
			 * System.out.println(inputStateArr[j]); }
			 */

			while (!validEndState) {
				end = JOptionPane
						.showInputDialog("Enter end/final state, 9 unique numbers from 0 to 8 seperated by a space");
				validEndState = validateInput(end);
			}

			// INIT END State
			String[] tempArr2 = end.split("\\s+");
			for (int i = 0; i < tempArr2.length; i++) {
				finalStateArr[i] = Byte.valueOf(tempArr2[i]);
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Program terminated");
		}

		// DEBUGGING PURPOSES
		/*
		 * for (int j = 0; j < finalStateArr.length; j++) {
		 * System.out.println(finalStateArr[j]); }
		 */

		// MAIN GAME LOOP WILL BE IMPLEMENTED BELOW, FOR NOW IT JUST SHOWS THE FIRST
		// MOVE

		startState = new State(inputStateArr, 0);
		finalState = new State(finalStateArr, 0);
		open = new ArrayList<State>();
		closed = new ArrayList<State>();

		open.add(startState);

		while (open.size() != 0) {

			// find the node on the open list with the lowest f value, call it current
			State current = open.get(0); // assuming leftmost is the best heuristic value

			if (open.size() == 1) { // first iteration, therefore no ancestor so height is 0;
				height = 0;
			} else {
				height = (current.getAncestor()).getHeight() + 1;
			}

			// remove from the open list and add to closed
			open.remove(0);
			closed.add(current);

			if (Arrays.equals(current.getArr(), finalState.getArr())) {
				System.out.println("final state reached");
				//returnPath(current, closed);
				//returnPath2(current);
				System.out.println("start of closed list");
				printList2(closed);
				System.out.println("end of closed list");
				break;
			}

			// generate children
			byte[] moves = current.getMoves();
			ArrayList<State> list = new ArrayList<State>();
			list = current.getStates(moves, height, current); // generate children

			// for each successor
			main: for (int i = 0; i < list.size(); i++) {

				for (int j = 0; j < closed.size(); j++) { // VERSION 2 OF BELOW
					if (Arrays.equals((closed.get(j).getArr()),(list.get(i).getArr()))) {
						continue main;
					}
				}

				if (closed.contains(list.get(i))) { // if successor is already evaluated ignore
					continue;
				}

				int tempG = current.getHeight() + (list.get(i).getHeight() - current.getHeight()); // + distance between
																									// successor and
																									// current

			  for (int j = 0; j < open.size(); j++) { // VERSION 2 OF BELOW
						if (Arrays.equals((open.get(j).getArr()),(list.get(i).getArr()))) {
							continue main;
						}
					}

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
						if (s1.getF(finalState) > s2.getF(finalState))
							return 1;
						if (s1.getF(finalState) < s2.getF(finalState))
							return -1;
						return 0;
					}
				});

				

				/*if (height > 1) {
				System.out.println((open.get(0)).getF(finalState));
				System.out.println((open.get(1)).getF(finalState));
				System.out.println("--");
			}*/


			}
			

			/*
			 * byte[] moves = startState.getMoves(); ArrayList<State> list = new
			 * ArrayList<State>(); list = startState.getStates(moves); if (validEndState &&
			 * validStartState) { print(moves);
			 *
			 * for(int j = 0; j < list.size(); j++){
			 * System.out.println(list.get(j).getHeur(finalState)); } }
			 */

			// System.out.println(startState.getHeur(finalState));

		}
	
	}

	public static void returnPath(State current, ArrayList<State> closed) {
		for(int j = 0; j < closed.size(); j++) {
			for(int k = 0; k < closed.get(j).getArr().length; k++) {
				System.out.print(closed.get(j).getArr()[k] + " ");
				System.out.print(closed.get(j).getArr()[k+1] + " ");
				System.out.println(closed.get(j).getArr()[k+2]);
			}
			System.out.println();
		}
	}

	public static void printList(ArrayList<State> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(Arrays.toString(list.get(i).getArr()));
		}
	}
	
	public static void printList2(ArrayList<State> list) {
		for (int i = 0; i < list.size(); i++) {
			byte[] arr = list.get(i).getArr();
			for(int j=0; j < arr.length; j+=3) {
				System.out.print(arr[j]+" ");
				System.out.print(arr[j+1]+" ");
				System.out.println(arr[j+2]);
			}
			System.out.println();
		}
	}

	public static void returnPath2(State current) {
		System.out.println(Arrays.toString(current.getArr()));
		while (true) {
			try {
				State newCurrent = current.getAncestor();
				System.out.println(Arrays.toString(newCurrent.getArr()));
				current = newCurrent;
			} catch (NullPointerException e) {
				break;
			}
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
						JOptionPane.showMessageDialog(null, "Duplicates found", "Error", JOptionPane.ERROR_MESSAGE);
						isDuplicate = true;
					}
				}
				if (!isDuplicate) {
					return true;
				}
			} else {
				JOptionPane.showMessageDialog(null, "Input must be numbers in the range 0-8, space separated", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Input must be 9 numbers", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	public static void print(byte[] moves) {
		String[] directions = { "West", "East", "South", "North" };

		for (int i = 0; i < directions.length; i++) {
			if (moves[i] > 0) {
				System.out.println(moves[i] + " to the " + directions[i]);
			}
		}
	}

}
