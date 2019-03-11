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
}
