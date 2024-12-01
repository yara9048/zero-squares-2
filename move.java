import java.util.Objects;

public class move {
     int row;
     int col;
     String direction;

    public move(int row, int col, String direction) {
        this.row = row;
        this.col = col;
        this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getDirection() {
        return direction;
    }
    @Override
    public String toString() {
        return "move : "+ direction;
    }
    @Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;

    move otherMove = (move) obj;
    return row == otherMove.row &&
           col == otherMove.col &&
           direction.equals(otherMove.direction);
}

@Override
public int hashCode() {
    return Objects.hash(row, col, direction);
}

}
