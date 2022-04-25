# CalendarPuzzle

A solver for Calendar Puzzle.

## Description
This project provides an algorithm approach to solve the calendar puzzle.

### About the puzzle

<img src="https://m.media-amazon.com/images/I/71HJ41A8d7L._AC_SL1500_.jpg" width="500" alt="calendar puzzle"/>

There are total 10 pieces that can form any day of a year.
<img src="https://raw.githubusercontent.com/qqu0127/CalendarPuzzle/master/site/pieces.jpg" width="500" alt="pieces"/>

<img src="https://raw.githubusercontent.com/qqu0127/CalendarPuzzle/master/site/example.jpg" width="500" alt="example"/>

## Introduction

Each piece and table are encoded as 2D array. A DFS with edge-cutting algorithm is implemented to speed up the search. 

The average runtime (for single input) is ~500ms.

## Getting Started

### Dependencies

* java 1.8+
* maven

### Installing

* Clone the project
* Use pre-built binary
  * ```
      chmod +x bin/solver.sh
      ./bin/solver.sh <MONTH> <DAY> <DATE>
    ```
* Build from source
    ```
    mvn clean package
    ```
  * Run commandline
      ```
      java -cp target/CalendarPuzzle-1.0.jar cli.PuzzleSolverCli <MONTH> <DAY> <DATE>

      e.g.
      java -cp target/CalendarPuzzle-1.0.jar cli.PuzzleSolverCli APR 15 FRI
      ```
### Help

Both table and puzzle pieces are indexed from upperleft corner.
The solution is represented by a list of step, consisting of:
* the mask of the puzzle piece to place
* the position on the table that aligns with the upperleft point of the puzzle piece *mask*.

Some example output:
```
// Each step consists of a coordinate to place, and the exact shape of the piece
// Such "Thumb" shape piece should be placed at position (0, 0) in such direction
 (0, 0)
 [1, 0]
 [1, 1]
 [1, 1]
```
```
// NOTICE (0, 2) aligns with the position of the upper-left corner of the 2D mask, 
// even though it's empty for actual the piece
 (0, 2)
 [0, 0, 1]
 [0, 0, 1]
 [1, 1, 1]
```

## Authors

Qi (Quincy) Qu
