package controller;

import component.Mask;
import component.Piece;
import component.Table;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class StateManager {

  private Mask _table;
  private Deque<AttemptStep> _history = new ArrayDeque<>();

  // add operation log as a stack/queue

  public StateManager() {
    _table = Table.getInitialState();
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
    _history.push(new AttemptStep(shape, row, col));
    return true;
  }

  public Deque<AttemptStep> getHistory() {
    return _history;
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
    _history.pop();
    return true;
  }

  public static void main(String[] args) {
    StateManager manager = new StateManager();
    boolean result = manager.attemptPlace(Piece.BIG_L.getShapes().get(3), 0, 3);
    result = result && manager.attemptPlace(Piece.BIG_L.getShapes().get(2), 2, 2);
    System.out.println(result);
    manager.getTable().print();

    System.out.println(manager.getHistory().peek());
  }
}
