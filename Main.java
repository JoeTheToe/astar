import java.io.IOException;

import javax.imageio.IIOException;
import javax.swing.JOptionPane;

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
		
			try {
				while (!validStartState) {
					String start = JOptionPane
							.showInputDialog("Enter start state, 9 unique numbers from 0 to 8 seperated by a space");
					if(start == null) {
						JOptionPane.showMessageDialog(null, "Program terminated");
						validStartState = true;
						validEndState = true;
					}
					else
						validStartState = validateInput(start, inputStateArr);
				}

				for (int j = 0; j < inputStateArr.length; j++) {
					System.out.println(inputStateArr[j]);
				}
				while (!validEndState) {
					String end = JOptionPane.showInputDialog(
							"Enter end/final state, 9 unique numbers from 0 to 8 seperated by a space");
					if(end == null) {
						JOptionPane.showMessageDialog(null, "Program terminated");
						validEndState = true;
					} else
						validEndState = validateInput(end, finalStateArr);
				}
			} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Incorrect input");
			}

		startState = new State(inputStateArr, height);
		finalState = new State(finalStateArr, height);
		open = new State[999]; // What should this number be? Not sure but maybe an arraylist here would be
								// better if we don't know the size
		closed = new State[999]; // What should this number be? Not sure but maybe an arraylist here would be
									// better if we don't know the size
		byte[] moves = startState.getMoves();
		print(moves);
		System.out.println(finalState.getHeur());

	}

	public static boolean validateInput(String input, byte[] stateArr) {
		boolean valid = false;
		boolean duplicate = false;
		String inputValidator = "([0-8]\\s){8}[0-8]{1}";

		String[] inputArr = input.split("\\s+");

		for (int i = 0; i < inputArr.length; i++) {
			stateArr[i] = Byte.parseByte(inputArr[i]);
		}

		for (int j = 0; j < stateArr.length && !duplicate; j++) {
			for (int k = j + 1; k < stateArr.length && !duplicate; k++) {
				if (stateArr[j] == stateArr[k])
					duplicate = true;
			}
		}

		if (input.matches(inputValidator) && !duplicate && input != null) {
			valid = true;
		} else{
			JOptionPane.showMessageDialog(null, "Incorrect input");
		}

		return valid;
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
