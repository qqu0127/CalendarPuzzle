package component.constant;

public enum Day {

  SUN(6, 3),
  MON(6, 4),
  TUE(6, 5),
  WED(6, 6),
  THU(7, 4),
  FRI(7, 5),
  SAT(7, 6);

  private int _row;
  private int _col;

  Day(int row, int col) {
    _row = row;
    _col = col;
  }

  public int[] getPos() {
    return new int[]{_row, _col};
  }
}
