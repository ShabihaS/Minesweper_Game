package game;

import board.Board;
import java.util.Scanner;

public class Game {

  private Board board;
  private Scanner scanner;
  private boolean gameOver;

  public Game() {

    board = new Board(10, 10);
    scanner = new Scanner(System.in);
    gameOver = false;

  }

  public void start() {

    System.out.println("Welcome to Minesweeper!");
    System.out.println("Enter coordinates like: row,col");
    System.out.println();

    while (!gameOver) {

      board.render();

      System.out.println();
      System.out.print("Choose a cell: ");

      String input = scanner.nextLine();

      int[] coordinates = parseInput(input);

      if (coordinates == null) {

        System.out.println("Invalid input. Try again.");
        continue;

      }

      int row = coordinates[0];
      int col = coordinates[1];

      boolean safe = board.revealCell(row, col);

      if (!safe) {

        System.out.println();
        board.render();

        System.out.println("BOOM! You hit a mine!");
        gameOver = true;

      } else if (board.hasWon()) {

        System.out.println();
        board.render();

        System.out.println("Congratulations! You cleared the board!");
        gameOver = true;

      }

    }

    scanner.close();

  }

  private int[] parseInput(String input) {

    try {

      String[] parts = input.split(",");

      int row = Integer.parseInt(parts[0]);
      int col = Integer.parseInt(parts[1]);

      return new int[] { row, col };

    } catch (Exception e) {

      return null;

    }

  }

}