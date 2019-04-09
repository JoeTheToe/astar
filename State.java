import java.util.*;

public class State {

  byte[] stateArr;
  int height;
  State ancestor;

  public State (byte[] arr, int height) {
    this.stateArr = arr;
    this.height = height;
  }

  public State (byte[] arr, int height, State ancestor) {
    this.stateArr = arr;
    this.height = height;
    this.ancestor = ancestor;
  }

  public byte[] getArr() {
    return this.stateArr;
  }

  public int getHeight() {
    return this.height;
  }

  public State getAncestor() {
    return this.ancestor;
  }

  public int getHeur(State endState) {
    byte amntOutOfPlace = 0;
    byte[] startArr = this.stateArr;
    byte[] endArr = endState.stateArr;

    for (int i = 0; i < (this.stateArr).length; i++) {
      if (startArr[i] != endArr[i]) {
        for (int j = 0; j < (this.stateArr).length; j++) {
          if ((endArr[j] == startArr[i]) && (endArr[j] != 0 && startArr[i] != 0)) {
            //System.out.println("j: " + j + " i: " + i);
            amntOutOfPlace += Math.abs(j - i);
          }
        }
      }
    }
    return amntOutOfPlace;
  }

public byte[] getMoves(){
	// West, East, South, North
	byte[] moves = {0,0,0,0};
	int index = -1;

	for (int i = 0; (i < (this.stateArr).length) && (index == -1); i++) {
        if (this.stateArr[i] == 0) {
            index = i;
        }
    }
	//west
	if(index != 2 && index != 5 && index != 8){
		moves[0] = (this.stateArr[index + 1]);
	}

	//east
	if(index != 0 && index != 3 && index != 6 ){
		moves[1] = (this.stateArr[index - 1]);

	}

	//south
	if(index > 2){
		moves[2] = (this.stateArr[index - 3]);

	}
	//north
	if(index < 6){
		moves[3] = (this.stateArr[index + 3]);

	}

	return moves;

  }

public ArrayList<State> getStates(byte[] arr, int height, State ancestor){

	ArrayList<State> list = new ArrayList<State>();

	for(int j = 0; j < 4; j++){

		if(arr[j] > 0){
			int cnt1 = 0;
			int cnt2 = 0;
			byte[] newState = new byte[((this.stateArr).length)];

			for(int n = 0; n < ((this.stateArr).length); n++){
				newState[n] = this.stateArr[n];
			}

			for (int l = 0; l < ((this.stateArr).length); l++){
				if (this.stateArr[l] == 0){
					cnt1 = l;
				}
			}

			for (int m = 0; m < ((this.stateArr).length); m++){
				if (this.stateArr[m] == arr[j]){
					cnt2 = m;
				}
			}

			newState[cnt1] = this.stateArr[cnt2];
			newState[cnt2] = this.stateArr[cnt1];

			State nextState = new State(newState, height, ancestor);
			list.add(nextState);
		}
	}
	return list;
}
}
