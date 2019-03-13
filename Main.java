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
		boolean validStartState = false;
		boolean validEndState = false;

		while (!validStartState && !validEndState) {
			if (!validStartState) {
				String start = JOptionPane
						.showInputDialog("Enter start state, 9 unique numbers from 0 to 8 seperated by a space");
				validStartState = validateInput(start, inputStateArr);
			}
			if (!validEndState) {
				String end = JOptionPane
						.showInputDialog("Enter end/final state, 9 unique numbers from 0 to 8 seperated by a space");
				validEndState = validateInput(end, finalStateArr);
			}
		}

		// finalState = new State(finalStateArr, height);
		open = new State[999]; // What should this number be?
		closed = new State[999]; // What should this number be?

		System.out.println(finalState.getHeur());

	}

	public static boolean validateInput(String input, int[] stateArr) {
		boolean valid = false;
		boolean duplicate = false;
		String inputValidator = "([0-8]\\s){8}[0-8]";

		String[] inputArr = input.split("\\s+");

		for (int i = 0; i < inputArr.length; i++) {
			stateArr[i] = Integer.parseInt(inputArr[i]);
		}

		for (int j = 0; j < stateArr.length && !duplicate; j++) {
			for (int k = j + 1; k < stateArr.length && !duplicate; k++) {
				if (stateArr[j] == stateArr[k])
					duplicate = true;
			}
		}

		if (input.matches(inputValidator) && !duplicate) {
			valid = true;
		} else {
			JOptionPane.showMessageDialog(null, "Incorrect input");
		}

		return valid;
	}

}
