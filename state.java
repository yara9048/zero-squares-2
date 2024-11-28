import java.util.Arrays;

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

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != null) {
                    System.out.print(grid[i][j].getColor() + " ");
                } else {
                    System.out.print("null ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        state otherState = (state) o;
        return Arrays.deepEquals(this.grid, otherState.grid);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(grid);
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }
}
