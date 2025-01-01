public class Square {
    public String color;
    public int row;
    public int col;
    public int targetRow;
    public int targetCol;
    public boolean reachedTarget;  

    public Square(String color, int row, int col, int targetRow, int targetCol) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.targetRow = targetRow;
        this.targetCol = targetCol;
        this.reachedTarget = false;  }

    public String getColor() {
        return color;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getTargetRow() {
        return targetRow;
    }

    public int getTargetCol() {
        return targetCol;
    }

    public boolean isReachedTarget() {
        return reachedTarget;
    }

    public void setReachedTarget(boolean reachedTarget) {
        this.reachedTarget = reachedTarget;
    }

    public boolean hasReachedTarget() {
        return row == targetRow && col == targetCol;
    }
}
