package controller;

import component.Mask;

import java.util.List;

public class StateManager {

  private Mask _table;

  public StateManager(Mask initState) {
    _table = initState;
  }

  public boolean isShapeFitIn(Mask shape, int row, int col) {
    if (shape.getWidth() + col > _table.getWidth()
      || shape.getHeight() + row > _table.getHeight()) {
      return false;
    }
    List<List<Integer>> cur = _table.getShape();
    for (int i = 0; i < shape.getHeight(); i++) {
      for (int j = 0; j < shape.getWidth(); j++) {
        if (shape.getShape().get(i).get(j) != 0 && cur.get(i + row).get(j + col) > 0) {
          return false;
        }
      }
    }
    return true;
  }

  public Mask getTable() {
    return _table;
  }

  public boolean attemptPlace(Mask shape, int row, int col) {
    if (!isShapeFitIn(shape, row, col)) {
      return false;
    }
    List<List<Integer>> cur = _table.getShape();
    for (int i = 0; i < shape.getHeight(); i++) {
      for (int j = 0; j < shape.getWidth(); j++) {
        if (shape.getShape().get(i).get(j) != 0) {
          cur.get(i + row).set(j + col, shape.getShape().get(i).get(j));
        }
      }
    }
    return true;
  }

  public boolean revertPlacement(Mask shape, int row, int col) {
    List<List<Integer>> cur = _table.getShape();
    for (int i = 0; i < shape.getHeight(); i++) {
      for (int j = 0; j < shape.getWidth(); j++) {
        if (shape.getShape().get(i).get(j) != 0) {
          cur.get(i + row).set(j + col, 0);
        }
      }
    }
    return true;
  }
}
