public class Main {

  public static void main(String[] args) {
    int height = 4;
    byte[] x = {1,2,3};
    State s = new State(x, height);

    System.out.println(s.getHeight());
  }

}
