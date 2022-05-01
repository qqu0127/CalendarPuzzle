package component;

import component.constant.Day;
import component.constant.Month;
import component.constant.Piece;

import java.util.List;

public class CalendarUtil {

  public static Mask createInitialState(Month month, Day day, int date) {
    return Table.createInitialState(new int[][] {month.getPos(), day.getPos(), computeDayPos(date)});
  }

  private static int[] computeDayPos(int num) {
    return new int[] {2 + (num - 1) / 7, (num - 1) % 7};
  }

  /**
   * Validation on size of the holes for early rejection.
   * @param holes size of the holes on the table.
   * @return true if holes can't be filled by the pieces.
   */
  public static boolean earlyReject(List<Integer> holes) {
    int minHoleSize = holes.stream().min(Integer::compareTo).get();

    if (minHoleSize < Piece.MIN_SIZE) {
      return true;
    }
    return minHoleSize < Piece.MIN_SIZE * 2 && minHoleSize > Piece.MAX_SIZE;
  }
}
