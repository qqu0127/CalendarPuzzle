package controller;

import component.Mask;

import java.util.List;

public class AttemptStep implements Comparable<AttemptStep> {

  private Mask _shape;
  private int _row;
  private int _col;

  public AttemptStep(Mask shape, int row, int col) {
    _shape = shape;
    _row = row;
    _col = col;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(String.format("(%s, %s)\n", _row, _col));
    for (List<Integer> row : _shape.getShape()) {
      builder.append(row.toString());
      builder.append("\n");
    }
    return builder.toString();
  }

  @Override
  public int compareTo(AttemptStep other) {
    if (_row != other._row) {
      return _row - other._row;
    }
    return _col - other._col;
  }
}
