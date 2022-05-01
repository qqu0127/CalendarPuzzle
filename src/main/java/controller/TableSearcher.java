package controller;

import component.CalendarUtil;
import component.Mask;
import component.constant.Piece;

import java.util.*;

public class TableSearcher {

  private final StateManager _manager;
  private final Set<Piece> _pieces;

  public TableSearcher(Mask initState, Set<Piece> pieces) {
    _manager = new StateManager(initState);
    _pieces = pieces;
  }

  public List<AttemptStep> findPath() {
    List<AttemptStep> steps = findPath(EnumSet.copyOf(_pieces), new LinkedList<>());
    Collections.sort(steps);
    return steps;
  }

  private List<AttemptStep> findPath(Set<Piece> pieces, LinkedList<AttemptStep> steps) {
    if (pieces.isEmpty()) {
      return steps;
    }
    List<Integer> holes = _manager.getTable().countHoles();
    if (holes.size() > pieces.size()
      // cutting edge with connectivity check
      || CalendarUtil.earlyReject(holes)) {
      return null;
    }

    Piece p = pieces.iterator().next();
    pieces.remove(p);

    for (Mask shape : p.getShapes()) {
      for (int i = 0; i < _manager.getTable().getHeight(); i++) {
        for (int j = 0; j < _manager.getTable().getWidth(); j++) {
          if (_manager.getTable().get(i, j) != 0 || !_manager.attemptPlace(shape, i, j)) {
            // if invalid position or can't fit, try next position
            continue;
          }
          // already placed shape at (i, j)
          steps.addLast(new AttemptStep(shape, i, j));
          List<AttemptStep> attempted = findPath(pieces, steps);
          if (attempted != null) {
            // got a solution
            return attempted;
          }
          // back-track
          steps.pollLast();
          _manager.revertPlacement(shape, i, j);
        }
      }
    }
    pieces.add(p);
    return null;
  }
}
