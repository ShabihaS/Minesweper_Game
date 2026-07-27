package board;

import cell.Cell;
import java.util.Random;

public class Board {

  private int SIZE;
  private int NUMBER_OF_MINES;

  private Cell[][] grid;

  // Added for game state
  private boolean gameOver = false;
  private boolean won = false;

  // Constructor
  public Board(int size, int numberOfMines) {

    SIZE = size;
    NUMBER_OF_MINES = numberOfMines;

    grid = new Cell[SIZE][SIZE];

    createGrid();
    placeMines();
    calculateAdjacentMines();

  }

  // Create all cells
  private void createGrid() {

    for (int row = 0; row < SIZE; row++) {

      for (int col = 0; col < SIZE; col++) {

        grid[row][col] = new Cell();

      }
    }
  }

  // Randomly place mines
  private void placeMines() {

    Random random = new Random();

    int minesPlaced = 0;

    while (minesPlaced < NUMBER_OF_MINES) {

      int row = random.nextInt(SIZE);
      int col = random.nextInt(SIZE);

      if (!grid[row][col].isMine()) {

        grid[row][col].setMine(true);
        minesPlaced++;

      }
    }
  }

  // Calculate numbers around each cell
  private void calculateAdjacentMines() {

    for (int row = 0; row < SIZE; row++) {

      for (int col = 0; col < SIZE; col++) {

        if (!grid[row][col].isMine()) {

          int count = countAdjacentMines(row, col);

          grid[row][col].setAdjacentMineCount(count);

        }
      }
    }
  }

  // Check the 8 surrounding cells
  private int countAdjacentMines(int row, int col) {

    int count = 0;

    for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {

      for (int colOffset = -1; colOffset <= 1; colOffset++) {

        int neighbourRow = row + rowOffset;
        int neighbourCol = col + colOffset;

        // ignore current cell
        if (rowOffset == 0 && colOffset == 0) {
          continue;
        }

        // check boundaries
        if (isValidPosition(neighbourRow, neighbourCol)) {

          if (grid[neighbourRow][neighbourCol].isMine()) {
            count++;
          }
        }
      }
    }

    return count;
  }

  // Check if position exists
  private boolean isValidPosition(int row, int col) {

    return row >= 0
        && row < SIZE
        && col >= 0
        && col < SIZE;

  }

  // Reveal selected cell
  public boolean revealCell(int row, int col) {

    // Stop game if already finished
    if (gameOver || won) {
      return false;
    }

    Cell cell = grid[row][col];

    // Mine clicked
    if (cell.isMine()) {

      gameOver = true;

      revealAllMines();

      return false;

    }

    cell.reveal();

    // Check if player won
    if (checkWin()) {

      won = true;

    }

    return true;

  }

  // Reveal all mines after losing
  private void revealAllMines() {

    for (int row = 0; row < SIZE; row++) {

      for (int col = 0; col < SIZE; col++) {

        grid[row][col].reveal();

      }
    }
  }

  // Check win condition
  private boolean checkWin() {

    for (int row = 0; row < SIZE; row++) {

      for (int col = 0; col < SIZE; col++) {

        Cell cell = grid[row][col];

        // If any safe cell is hidden, player has not won
        if (!cell.isMine() && !cell.isRevealed()) {

          return false;

        }

      }
    }

    return true;

  }

  // Display board
  public void render() {

    for (int row = 0; row < SIZE; row++) {

      for (int col = 0; col < SIZE; col++) {

        Cell cell = grid[row][col];

        if (cell.isRevealed()) {

          if (cell.isMine()) {

            System.out.print(" * ");

          } else {

            System.out.print(
                " " + cell.getAdjacentMineCount() + " ");

          }

        } else {

          System.out.print(" ? ");

        }

      }

      System.out.println();

    }

  }

  // Check game status
  public boolean isGameOver() {

    return gameOver;

  }

  public boolean hasWon() {

    return won;

  }

  // Used by Game later
  public Cell getCell(int row, int col) {

    return grid[row][col];

  }

}