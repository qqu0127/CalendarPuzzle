package controller;

import component.Mask;
import component.Piece;
import component.Table;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EnumSet;

public class TableSearcher {

  private static Deque<AttemptStep> findPath(
    StateManager manager, EnumSet<Piece> pieces, Deque<AttemptStep> steps) {
    if (pieces.isEmpty()) {
      return steps;
    }
    if (manager.getTable().countHoles() > pieces.size()) {
      return null;
    }

    Piece p = pieces.iterator().next();
    pieces.remove(p);

    for (Mask shape : p.getShapes()) {
      for (int i = 0; i < manager.getTable().getHeight(); i++) {
        for (int j = 0; j < manager.getTable().getWidth(); j++) {
          if (manager.getTable().get(i, j) != 0 || !manager.attemptPlace(shape, i, j)) {
            // if invalid position or can't fit, try next position
            continue;
          }
          // already placed shape at (i, j)
          steps.addLast(new AttemptStep(shape, i, j));
          Deque<AttemptStep> attempted = findPath(manager, pieces, steps);
          if (attempted != null) {
            // got a solution
            return attempted;
          }
          // back-track
          steps.pollLast();
          manager.revertPlacement(shape, i, j);
        }
      }
    }
    pieces.add(p);
    return null;
  }

  public static void main(String[] args) {
    System.out.println("start:");
    Mask start = Table.createInitialState(new int[][]{
      {0, 3}, {4, 6}, {7, 4}
    });

    start.print();
    System.out.println("=====");
    System.out.println("dest:");
    Mask dest = Table.createDestState(new int[][] {});
    dest.print();
    System.out.println("=====");

    long time = System.currentTimeMillis();
    Deque<AttemptStep> steps =
      findPath(new StateManager(start, false), EnumSet.allOf(Piece.class), new ArrayDeque<>());
    time = System.currentTimeMillis() - time;
    System.out.println("Found solution in " + time + " ms.");

    for (AttemptStep step : steps) {
      System.out.println(step);
    }
  }

}
