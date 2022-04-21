package component;


public class Table {

  public static final int WIDTH = 7;
  public static final int HEIGHT = 8;

  public static void main(String[] args) {
    Mask table = getInitialState();
    table.print();
  }

  public static Mask getInitialState() {
    Mask.Builder builder  = new Mask.Builder(HEIGHT, WIDTH);
    builder.addPoint(0, WIDTH - 1);
    builder.addPoint(1, WIDTH - 1);
    for (int i = 0; i < 4; i++) {
      builder.addPoint(HEIGHT - 1, i);
    }
    return builder.build();
  }

}
