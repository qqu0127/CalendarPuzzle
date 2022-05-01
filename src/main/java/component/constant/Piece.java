package component.constant;

import component.Mask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Piece {

  BIG_L(new Mask.Builder(3, 3)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(0, 2)
    .addPoint(1, 0)
    .addPoint(2, 0)),

  DOOR(new Mask.Builder(2, 3)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(0, 2)
    .addPoint(1, 0)
    .addPoint(1, 2)),

  LONG_L(new Mask.Builder(4, 2)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(1, 0)
    .addPoint(2, 0)
    .addPoint(3, 0)),

  LONG_TWO(new Mask.Builder(2, 4)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(0, 2)
    .addPoint(1, 2)
    .addPoint(1, 3)),

  T(new Mask.Builder(3, 3)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(0, 2)
    .addPoint(1, 1)
    .addPoint(2, 1)),

  THUMB(new Mask.Builder(3, 2)
    .addPoint(0, 0)
    .addPoint(1, 0)
    .addPoint(1, 1)
    .addPoint(2, 0)
    .addPoint(2, 1)),

  TWO(new Mask.Builder(3, 3)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(1, 1)
    .addPoint(2, 1)
    .addPoint(2, 2)),

  L(new Mask.Builder(3, 2)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(1, 0)
    .addPoint(2, 0)),

  ONE(new Mask.Builder(1, 4)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(0, 2)
    .addPoint(0, 3)),

  SHORT_TWO(new Mask.Builder(2, 3)
    .addPoint(0, 0)
    .addPoint(0, 1)
    .addPoint(1, 1)
    .addPoint(1, 2));

  public static final int MIN_SIZE = 4;
  public static final int MAX_SIZE = 5;

  private Set<Mask> _shapes;

  Piece(Mask.Builder builder) {
    _shapes = createShapes(builder);
  }

  public List<Mask> getShapes() {
    return new ArrayList<>(_shapes);
  }

  private static Set<Mask> createShapes(Mask.Builder input) {
    Set<Mask> shapes = new HashSet<>();
    Mask.Builder builder = input;
    for (int i = 0; i < 4; i++) {
      shapes.add(builder.build());
      builder = builder.rotate90();
    }
    builder = builder.mirror();
    for (int i = 0; i < 4; i++) {
      shapes.add(builder.build());
      builder = builder.rotate90();
    }
    return shapes;
  }
}
