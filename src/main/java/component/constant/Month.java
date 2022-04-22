package component.constant;

public enum Month {

  JAN(0, 0),
  FEB(0, 1),
  MAR(0, 2),
  APR(0, 3),
  MAY(0, 4),
  JUNE(0, 5),
  JULY(1, 0),
  AUG(1, 1),
  SEPT(1, 2),
  OCT(1, 3),
  NOV(1, 4),
  DEC(1, 5);

  private int _row;
  private int _col;
  Month(int row, int col) {
    _row = row;
    _col = col;
  }

  public int[] getPos() {
    return new int[]{_row, _col};
  }
}
