
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
			return;
		}

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
				returnPath2(current);
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
			}

		}
	}

	public static void printList(ArrayList<State> list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(Arrays.toString(list.get(i).getArr()));
		}
	}

	public static void returnPath2(State current) {
		String s1 = Arrays.toString(current.getArr());
		String s2 = "";
		for (int i = 1; i < 27; i+=3) {
			if (i != 7 && i != 16) {
				s2+= s1.charAt(i) + " ";
			} else {
				s2+= s1.charAt(i) + "\n";
			}
		} s2+= "\n";

		while (true) {
			try {
				State newCurrent = current.getAncestor();
				String s3 = Arrays.toString(newCurrent.getArr());
				for (int i = 1; i < 27; i+=3) {
					if (i != 7 && i != 16) {
						s2+= s3.charAt(i) + " ";
					} else {
						s2+= s3.charAt(i) + "\n";
					}
				} s2+= "\n";
				current = newCurrent;
			} catch (NullPointerException e) {
				for (int i = s2.length(); i >= 1; i-=19) {
					System.out.println(s2.substring(i-19,i));
				}
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
