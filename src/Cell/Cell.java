package Cell;


public class Cell {

    private boolean mine;
    private boolean revealed;
    private int adjacentMineCount;

    public Cell() {
        this.mine = false;
        this.revealed = false;
        this.adjacentMineCount = 0;
    }


    public boolean isMine() {
        return mine;
    }


    public boolean isRevealed() {
        return revealed;
    }


    public int getAdjacentMineCount() {
        return adjacentMineCount;
    }


    public void setMine(boolean mine) {
        this.mine = mine;
    }


    public void reveal() {
        this.revealed = true;
    }


    public void setAdjacentMineCount(int count) {
        this.adjacentMineCount = count;
    }

}