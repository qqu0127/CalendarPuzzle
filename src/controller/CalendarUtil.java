package controller;

import component.Mask;
import component.Table;
import component.constant.Day;
import component.constant.Month;

public class CalendarUtil {

  public static Mask createInitialState(Month month, Day day, int date) {
    return Table.createInitialState(new int[][] {month.getPos(), day.getPos(), computeDayPos(date)});
  }

  private static int[] computeDayPos(int num) {
    return new int[] {2 + (num - 1) / 7, (num - 1) % 7};
  }
}
