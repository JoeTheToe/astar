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

  public int getHeur() {
    byte amntOutOfPlace = 0;
    // Check up to second last index if any out of amntOutOfPlace
    // Last value will be 0 so no need to check
    for (int i = 0; i < (this.stateArr).length - 1; i++) {
      if (this.stateArr[i] != i+1) {
        amntOutOfPlace++;
      }
    }
    return amntOutOfPlace;
  }
}
