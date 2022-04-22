# CalendarPuzzle

A solver for Calendar Puzzle.

## Description

This project provides an algorithm approach to solve the calendar puzzle. 

Each piece and table are encoded as 2D array. A DFS with edge-cutting is implemented to speed up the search. 

The average time spent for a given day is ~500ms.

## Getting Started

### Dependencies

* maven

### Installing

* Clone the project
* Build project with
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

## Authors

Qi (Quincy) Qu
