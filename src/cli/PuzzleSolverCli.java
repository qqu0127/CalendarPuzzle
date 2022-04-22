package cli;

import component.Mask;
import component.constant.Day;
import component.constant.Month;
import component.constant.Piece;
import controller.AttemptStep;
import controller.CalendarUtil;
import controller.TableSearcher;

import java.util.EnumSet;

import static component.constant.Day.FRI;

public class PuzzleSolverCli {


  public static void main(String[] args) {
    long time = System.currentTimeMillis();
    run(Month.APR, FRI, 29);
    time = System.currentTimeMillis() - time;
    System.out.println("Found solution in " + time + " ms.");
  }

  private static void run(Month month, Day day, int date) {
    Mask start = CalendarUtil.createInitialState(month, day, date);
    System.out.println("Initial state:");
    start.print();
    System.out.println("==========");
    TableSearcher searcher = new TableSearcher(start, EnumSet.allOf(Piece.class));
    for (AttemptStep step : searcher.findPath()) {
      System.out.println(step);
    }
  }

}
