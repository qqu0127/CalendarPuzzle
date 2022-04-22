package component;


public class Table {

  private static final int WIDTH = 7;
  private static final int HEIGHT = 8;

  public static void main(String[] args) {
    Mask table = getInitialState();
    table.print();
    System.out.println("=====");
    table = createDestState(new int[][] {{0,0}, {1, 2}, {3, 4}});
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

  public static Mask createInitialState(int[][] points) {
    Mask.Builder builder  = new Mask.Builder(HEIGHT, WIDTH);
    builder.addPoint(0, WIDTH - 1);
    builder.addPoint(1, WIDTH - 1);
    for (int i = 0; i < 4; i++) {
      builder.addPoint(HEIGHT - 1, i);
    }
    for (int[] p : points) {
      builder.addPoint(p[0], p[1]);
    }
    return builder.build();
  }

  public static Mask createDestState(int[][] points) {
    Mask.Builder builder = new Mask.Builder(HEIGHT, WIDTH);
    for (int[] p : points) {
      builder.addPoint(p[0], p[1]);
    }
    return builder.build(1);
  }

}
