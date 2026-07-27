package main;

import board.Board;

public class Main {

    public static void main(String[] args) {

        Board board = new Board();

        board.revealCell(0, 0);
        board.revealCell(0, 1);
        board.revealCell(5, 5);
        board.revealCell(9, 9);

        board.render();

    }

}