public class Main {

  static byte size = 8;
  static State finalState;
  static State[] open;
  static State[] closed;

  public static void main(String[] args) {
    int height = 0;
    byte[] finalStateArr = {1,2,3,4,5,6,7,0};
    finalState = new State(finalStateArr, height);

    open = new State[999]; // What should this number be?
    closed = new State[999]; // What should this number be?

    System.out.println(finalState.getHeur());

  }

}
