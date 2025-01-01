import java.util.Arrays;
import java.util.List;

public class state {
    public Element[][] grid;
    public state parent;
    public int cost;

    public state() {
        this.grid = new Element[5][5];
        this.cost = 0;
        this.parent = null;
    }

    public state(Element[][] initialGrid) {
        this.grid = new Element[initialGrid.length][initialGrid[0].length];
        for (int i = 0; i < initialGrid.length; i++) {
            for (int j = 0; j < initialGrid[i].length; j++) {
                this.grid[i][j] = initialGrid[i][j];             }
        }
        this.cost = 0;
        this.parent = null;
    }

    public Element[][] getGrid() {
        return this.grid;
    }

    public void setElement(int row, int col, Element elem) {
        this.grid[row][col] = elem;
    }

    public state getParent() {
        return this.parent;
    }

    public void setParent(state parent) {
        this.parent = parent;
    }

    public void printState(List<Square> squares) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Element ele = grid[i][j];
                if (ele == null) {
                    System.out.print("[Empty] ");
                } else {
                    String displayColor = "";
                    
                    if (isSquare(i, j, squares)) {
                        displayColor = getSquareColor(i, j, squares);
                    } else {
                        if (ele.getAim().equals("not aim")) {
                            displayColor = ele.getColor();
                        } else {
                            displayColor = ele.getAim();
                        }
                    }
                    System.out.print("[" + displayColor + "] ");
                }
            }
            System.out.println();
        }
    }

    private boolean isSquare(int x, int y, List<Square> squares) {
        for (Square square : squares) {
            if (square.getRow() == x && square.getCol() == y) {
                return true;
            }
        }
        return false;
    }

    private String getSquareColor(int x, int y, List<Square> squares) {
        for (Square square : squares) {
            if (square.getRow() == x && square.getCol() == y) {
                return square.getColor();
            }
        }
        return "Unknown";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        state other = (state) obj;
        return Arrays.deepEquals(this.grid, other.grid); // Assuming grid is a 2D array
    }
    
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(this.grid); // Ensure grid is hashed properly
    }
    
    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}

