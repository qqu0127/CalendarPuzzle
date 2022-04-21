package controller;

import component.Mask;
import component.Piece;
import component.Table;

import java.util.Deque;
import java.util.List;

public class StateManager {

  private Mask _table;
  private boolean _reverse = false;

  public StateManager() {
    this(Table.getInitialState(), false);
  }

  public StateManager(Mask initState, boolean reverse) {
    _table = initState;
    _reverse = reverse;
  }

  public boolean isShapeFitIn(Mask shape, int row, int col) {
    if (shape.getWidth() + col > _table.getWidth()
      || shape.getHeight() + row > _table.getHeight()) {
      return false;
    }
    List<List<Integer>> cur = _table.getShape();
    for (int i = 0; i < shape.getHeight(); i++) {
      for (int j = 0; j < shape.getWidth(); j++) {
        if (!_reverse && shape.getShape().get(i).get(j) != 0 && cur.get(i + row).get(j + col) > 0) {
          return false;
        }
        if (_reverse && shape.getShape().get(i).get(j) != 0 && cur.get(i + row).get(j + col) == 0) {
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
          cur.get(i + row).set(j + col, _reverse ? 0 : shape.getShape().get(i).get(j));
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
          cur.get(i + row).set(j + col, _reverse ? shape.getShape().get(i).get(j) : 0);
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    StateManager manager = new StateManager(Table.createDestState(new int[][] {{5, 5}, {6, 6}}), true);
    boolean result = manager.attemptPlace(Piece.BIG_L.getShapes().get(3), 0, 3);
    result = result && manager.attemptPlace(Piece.BIG_L.getShapes().get(2), 2, 2);
    System.out.println(result);
    manager.getTable().print();
    System.out.println("=======");

    manager.revertPlacement(Piece.BIG_L.getShapes().get(2), 2, 2);
    manager.getTable().print();
    manager.revertPlacement(Piece.BIG_L.getShapes().get(3), 0, 3);
    System.out.println("=======");
    manager.getTable().print();
  }
}
