package component;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Impl class to represent the 2D shape.
 */
public class Mask {

  private int _width;
  private int _height;
  private List<List<Integer>> _shape;

  Mask(int height, int width, List<Pair<Integer, Integer>> points, int baseVal) {
    _width = width;
    _height = height;
    _shape = createInitShape(height, width, baseVal);
    for (Pair<Integer, Integer> p : points) {
      _shape.get(p.getKey()).set(p.getValue(), 1 - baseVal);
    }
  }

  Mask(int height, int width, List<List<Integer>> shape) {
    _width = width;
    _height = height;
    _shape = shape;
  }

  public List<List<Integer>> getShape() {
    return _shape;
  }

  public int get(int row, int col) {
    return _shape.get(row).get(col);
  }

  public List<Integer> countHoles() {
    List<List<Boolean>> visited = new ArrayList<>();
    for (int i = 0; i < _height; i++) {
      List<Boolean> row = new ArrayList<>();
      for (int j = 0; j < _width; j++) {
        row.add(false);
      }
      visited.add(row);
    }

    List<Integer> holes = new ArrayList<>();
    for (int i = 0; i < _height; i++) {
      for (int j = 0; j < _width; j++) {
        if (visited.get(i).get(j) || get(i, j) != 0) {
          continue;
        }
        holes.add(flood(visited, i, j));
      }
    }
    return holes;
  }

  private int flood(List<List<Boolean>> visited, int row, int col) {
    if (row >= _height || col >= _width || row < 0 || col < 0
      || visited.get(row).get(col) || get(row, col) != 0) {
      return 0;
    }
    visited.get(row).set(col, true);
    return 1 + flood(visited, row + 1, col)
      + flood(visited, row - 1, col)
      + flood(visited, row, col + 1)
      + flood(visited, row, col - 1);
  }

  public Mask clone() {
    List<List<Integer>> shape = new ArrayList<>();
    for (List<Integer> row : _shape) {
      shape.add(new ArrayList<>(row));
    }
    return new Mask(_height, _width, shape);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Mask mask = (Mask) o;
    return _width == mask._width &&
      _height == mask._height &&
      _shape.equals(mask._shape);
  }

  @Override
  public int hashCode() {
    return Objects.hash(_width, _height, _shape);
  }

  public int getWidth() {
    return _width;
  }

  public int getHeight() {
    return _height;
  }

  public void print() {
    for (List<Integer> row : _shape) {
      System.out.println(row);
    }
  }

  private static List<List<Integer>> createInitShape(int height, int width, int value) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<Integer> row = new ArrayList<>(Collections.nCopies(width, value));
      result.add(row);
    }
    return result;
  }

  public static class Builder {
    private final int _width;
    private final int _height;
    private List<Pair<Integer, Integer>> _points = new ArrayList<>();

    public Builder(int height, int width) {
      _width = width;
      _height = height;
    }

    private Builder(int height, int width, List<Pair<Integer, Integer>> points) {
      _width = width;
      _height = height;
      _points = new ArrayList<>(points);
    }

    public Builder mirror() {
      return new Builder(_height, _width,
        _points.stream()
          .map(p -> new Pair<>(p.getKey(), _width - p.getValue() - 1))
          .collect(Collectors.toList()));
    }

    public Builder rotate90() {
      return new Builder(_width, _height,
        _points.stream()
          .map(p -> new Pair<>(p.getValue(), _height - 1 - p.getKey()))
          .collect(Collectors.toList()));
    }

    public Builder addPoint(int row, int col) {
      _points.add(new Pair<>(row, col));
      return this;
    }


    public Mask build() {
      return build(0);
    }

    public Mask build(int baseVal) {
      return new Mask(_height, _width, _points, baseVal);
    }
  }
}
