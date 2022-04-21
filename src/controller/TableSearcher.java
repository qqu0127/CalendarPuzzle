package controller;

import component.Mask;
import component.Piece;
import component.Table;

import java.util.Deque;

public class TableSearcher {
  private Mask _start = Table.getInitialState();
  private Mask _dest;
  private StateManager _manager;

  private Deque<AttemptStep> _steps;


  public boolean dfs(Deque<AttemptStep> step) {
    // for each piece.shape, at each possible pos, up to 8 * 49 per level, total 10 levels

    return true;
  }

}
