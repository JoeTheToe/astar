import java.lang.*;

public class State {

  byte[] stateArr;
  int height;

  public State (byte[] arr, int height) {
    this.stateArr = arr;
    this.height = height;
  }

  public int getHeight() {
    return this.height;
  }

  public int getHeur(State endState) {
    byte amntOutOfPlace = 0;
    byte[] startArr = this.stateArr;
    byte[] endArr = endState.stateArr;

    for (int i = 0; i < (this.stateArr).length; i++) {
      if (startArr[i] != endArr[i]) {
        for (int j = 0; j < (this.stateArr).length; j++) {
          if (endArr[j] == startArr[i]) {
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
 
 
}
